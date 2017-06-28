/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import SendEmail.SendEmail;
import controllers.CarteiraCTRL;
import controllers.EmailCTRL;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class GerenciadorEmailGUI extends JFrame {

    private JComboBox cbRegimes, cbMes;
    private JButton btEnviar;
    private JLabel lb;
    private JTextField tfAno;
    private JCheckBox chLucroReal, chSimples, chPresumido, chMei, chIsento, chTodos;
    private JRadioButton rbTodos, rbPendentes;
    private ButtonGroup bgStatus;
    private JScrollPane scCarteira;
    private JTable tbCarteira;
    private DefaultTableModel dmCarteira;
    private ArrayList<Demanda> lista = CarteiraCTRL.listarCarteita(nome);
    private static String nome;

    public GerenciadorEmailGUI(String nome) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            // handle exception
        }
        inicializarComponentes();
        definirEventos();
        this.nome = nome;
        listarCarteira(nome);
        atualizarLista();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciar Emails " + nome);
        setBounds(0, 0, 500, 600);
        setLayout(null);

        chTodos = new JCheckBox("Todos");
        chTodos.setBounds(10, 20, 100, 25);
        add(chTodos);

        chLucroReal = new JCheckBox("Lucro Real");
        chLucroReal.setBounds(10, 50, 100, 25);
        add(chLucroReal);

        chPresumido = new JCheckBox("Presunido");
        chPresumido.setBounds(10, 80, 100, 25);
        add(chPresumido);

        chSimples = new JCheckBox("Simples");
        chSimples.setBounds(10, 110, 100, 25);
        add(chSimples);

        chMei = new JCheckBox("Mei");
        chMei.setBounds(10, 140, 100, 25);
        add(chMei);

        chIsento = new JCheckBox("Isento");
        chIsento.setBounds(10, 170, 100, 25);
        add(chIsento);

        rbTodos = new JRadioButton("Todos");
        rbTodos.setBounds(120, 20, 100, 25);
        add(rbTodos);

        rbPendentes = new JRadioButton("Pendentes");
        rbPendentes.setBounds(120, 50, 100, 25);
        add(rbPendentes);

        bgStatus = new ButtonGroup();
        bgStatus.add(rbPendentes);
        bgStatus.add(rbTodos);

        Object[] meses = {
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio",
            "Junho",
            "Julho",
            "Agosto",
            "Setembro",
            "Outubro",
            "Novembro",
            "Dezembro"};

        cbMes = new JComboBox(meses);
        cbMes.setBounds(230, 20, 100, 25);
        cbMes.setSelectedIndex(4);
        add(cbMes);

        tfAno = new JTextField("2017");
        tfAno.setBounds(230, 50, 100, 25);
        add(tfAno);

        btEnviar = new JButton("Enviar");
        btEnviar.setBounds(340, 20, 100, 25);
        add(btEnviar);

        scCarteira = new JScrollPane();
        scCarteira.setBounds(10, 260, 470, 300);

        dmCarteira = new DefaultTableModel(new Object[]{
            "Cód", "Empresa", "Regime"
        }, 0);

        tbCarteira = new JTable(dmCarteira);

        tbCarteira.setRowHeight(25);
        tbCarteira.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbCarteira.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbCarteira.getColumnModel().getColumn(2).setPreferredWidth(100);

        scCarteira.setViewportView(tbCarteira);
        add(scCarteira);
    }

    private void definirEventos() {
        cbMes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                listarCarteira(nome);
            }
        });
        
        chTodos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                marcarTodos();
            }
        });

        btEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chLucroReal.isSelected()) {
                    ArrayList<Demanda> l = CarteiraCTRL.listarLucroReal(nome);
                    System.out.println(l);
                    if (EmailCTRL.enviarEmail(cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), l)) {
                        JOptionPane.showMessageDialog(null, "Tudo certo");
                    }else{
                        JOptionPane.showMessageDialog(null, "hmmm, acho q n funfou");
                    }
                }
                
                if (chPresumido.isSelected()) {
                    ArrayList<Demanda> l = CarteiraCTRL.listarRegime(nome,"L. PRESUMIDO");
                    System.out.println(l);
                    
                    if (EmailCTRL.enviarEmail(cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), l)) {
                        JOptionPane.showMessageDialog(null, "Tudo certo");
                    }else{
                        JOptionPane.showMessageDialog(null, "Os emails não foram disparados, provavelmente já foram enviados");
                    }
                }
                
                if (chSimples.isSelected()) {
                    ArrayList<Demanda> l = CarteiraCTRL.listarRegime(nome,"SIMPLES");
                    System.out.println(l);
                    
                    if (EmailCTRL.enviarEmail(cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), l)) {
                        JOptionPane.showMessageDialog(null, "Os emails foram enviados com muito sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null, "Os emails não foram disparados, provavelmente já foram enviados");
                    }
                }
            }
        });
    }

    public void abrir() {
        GerenciadorEmailGUI g = new GerenciadorEmailGUI("PEDRITA");
        g.setVisible(true);
        g.setLocationRelativeTo(null);
        g.setResizable(false);
    }

    public void marcarTodos() {
        if (chTodos.isSelected()) {
            chIsento.setSelected(true);
            chLucroReal.setSelected(true);
            chSimples.setSelected(true);
            chPresumido.setSelected(true);
            chMei.setSelected(true);
        } else {
            chIsento.setSelected(false);
            chLucroReal.setSelected(false);
            chSimples.setSelected(false);
            chPresumido.setSelected(false);
            chMei.setSelected(false);
        }
    }

    public void listarCarteira(String nome) {
        dmCarteira.setRowCount(0);
        for (Demanda d : lista) {
            dmCarteira.addRow(new Object[]{
                d.getCod(),
                d.getEmpresa(),
                d.getRegime(),
                d.getPrioridadeRelacionamento(),
                d.getColaboradorContabil()
            });
        }
    }
    public void atualizarLista() {
        
        Color vermelho = new Color(255, 204, 204);
        Color amarelo = new Color(255, 255, 128);
        Color verde = new Color(179, 255, 217);
        tbCarteira.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                table.getColumnModel().getColumn(0).setCellRenderer(this);
                table.getColumnModel().getColumn(1).setCellRenderer(this);
                table.getColumnModel().getColumn(2).setCellRenderer(this);
                int cod = (int) table.getModel().getValueAt(row, 0);

                int status = CarteiraCTRL.getEmailStatus(cod,cbMes.getSelectedIndex()+1,Integer.parseInt(tfAno.getText()));
                if(status == 2){
                    c.setBackground(amarelo);
                }else{
                    c.setBackground(vermelho);
                }

                return c;
            }
        });
    }
}
