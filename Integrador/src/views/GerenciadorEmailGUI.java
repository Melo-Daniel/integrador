/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import SendEmail.SendEmail;
import controllers.CarteiraCTRL;
import controllers.ColaboradorCTRL;
import controllers.EmailCTRL;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JButton btEnviar,btEnviarSelecionados;
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
        setBounds(0, 0, 500, 500);
        setLayout(null);

        chTodos = new JCheckBox("Todos");
        chTodos.setBounds(10, 20, 100, 25);
        add(chTodos);

        chLucroReal = new JCheckBox("Lucro Real");
        chLucroReal.setBounds(10, 50, 100, 25);
        add(chLucroReal);

        chPresumido = new JCheckBox("Presumido");
        chPresumido.setBounds(120, 20, 100, 25);
        add(chPresumido);

        chSimples = new JCheckBox("Simples");
        chSimples.setBounds(120, 50, 100, 25);
        add(chSimples);

        chMei = new JCheckBox("Associação");
        chMei.setBounds(10, 80, 100, 25);
        add(chMei);

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
        btEnviar.setBounds(340, 20, 100, 55);
        add(btEnviar);

        scCarteira = new JScrollPane();
        scCarteira.setBounds(10, 110, 470, 300);

        dmCarteira = new DefaultTableModel(new Object[]{
            "Cód", "Empresa", "Regime"
        }, 0);

        tbCarteira = new JTable(dmCarteira);
        atualizarLista();
        tbCarteira.setRowHeight(25);
        tbCarteira.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbCarteira.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbCarteira.getColumnModel().getColumn(2).setPreferredWidth(100);

        scCarteira.setViewportView(tbCarteira);
        add(scCarteira);
        
        btEnviarSelecionados = new JButton("Enviar para empresas selecionadas");
        btEnviarSelecionados.setBounds(10,440,200,25);
        add(btEnviarSelecionados);
    }

    private void definirEventos() {
        btEnviarSelecionados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows[] = tbCarteira.getSelectedRows();
                int mes = cbMes.getSelectedIndex()+1;
                int ano = Integer.parseInt(tfAno.getText());
                int cont = 0;
                for(int i = 0; i < rows.length; i++){
                    int cod = (int)tbCarteira.getValueAt(rows[i], 0);
                    String regime = (String)tbCarteira.getValueAt(rows[i], 2);
                    Demanda d = CarteiraCTRL.getEmpresaCodigo(nome.toUpperCase(), cod);
                    System.out.println(d.getRegime());
                    if(EmailCTRL.enviarEmailSelecionado(mes, ano, d, nome)){
                        cont++;
                        System.out.println(cont + " - " + i);
                    }
                    if(cont > 0){
                        JOptionPane.showMessageDialog(null, "Emails enviados com td o sucesso!");
                    }
                }
            }
        });
        
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
                    if (EmailCTRL.enviarEmail(cbMes.getSelectedIndex() + 1,
                            Integer.parseInt(tfAno.getText()),
                            l,ColaboradorCTRL.getUsuariologado())) {
                        JOptionPane.showMessageDialog(null, "Os emails foram enviados com muito sucesso!");
                        listarCarteira(nome);
                    }else{
                        JOptionPane.showMessageDialog(null, "hmmm, acho q n funfou");
                    }
                }
                
                if (chPresumido.isSelected()) {
                    ArrayList<Demanda> l = CarteiraCTRL.listarRegime(nome,"L. PRESUMIDO");
                    System.out.println(l);
                    
                    if (EmailCTRL.enviarEmail(cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), l,ColaboradorCTRL.getUsuariologado())) {
                        JOptionPane.showMessageDialog(null, "Os emails foram enviados com muito sucesso!");
                        listarCarteira(nome);
                    }else{
                        JOptionPane.showMessageDialog(null, "Os emails não foram disparados, provavelmente já foram enviados");
                    }
                }
                
                if (chSimples.isSelected()) {
                    ArrayList<Demanda> l = CarteiraCTRL.listarRegime(nome,"SIMPLES");
                    System.out.println(l);
                    
                    if (EmailCTRL.enviarEmail(cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), l,ColaboradorCTRL.getUsuariologado())) {
                        JOptionPane.showMessageDialog(null, "Os emails foram enviados com muito sucesso!");
                        listarCarteira(nome);
                    }else{
                        JOptionPane.showMessageDialog(null, "Os emails não foram disparados, provavelmente já foram enviados");
                    }
                }
                if (chMei.isSelected()) {
                    ArrayList<Demanda> l = CarteiraCTRL.listarRegime(nome,"ASSOCIAÇÃO");
                    System.out.println(l);
                    
                    if (EmailCTRL.enviarEmail(cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), l,ColaboradorCTRL.getUsuariologado())) {
                        JOptionPane.showMessageDialog(null, "Os emails foram enviados com muito sucesso!");
                        listarCarteira(nome);
                    }else{
                        JOptionPane.showMessageDialog(null, "Os emails não foram disparados, provavelmente já foram enviados");
                    }
                }
            }
        });
        
        
        
    }

    public void abrir() {
        GerenciadorEmailGUI g = new GerenciadorEmailGUI(nome);
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
        Color azul = new Color(128, 229, 255);
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
                table.setSelectionBackground(Color.lightGray);
                table.setSelectionForeground(Color.blue);
                int status = CarteiraCTRL.getEmailStatus(cod,cbMes.getSelectedIndex()+1,Integer.parseInt(tfAno.getText()));
                if(status == 2){
                    c.setBackground(azul);
                    
                }else{
                    c.setBackground(vermelho);
                }

                return c;
            }
        });
    }
    
}
