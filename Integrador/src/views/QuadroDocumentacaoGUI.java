/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.AdministradorCTRL;
import controllers.CarteiraCTRL;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
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
public class QuadroDocumentacaoGUI extends JFrame {
    private Timer t = new Timer();
    private ArrayList<Demanda> lista = CarteiraCTRL.listarCarteita(nome);
    private JScrollPane scQuadro, scCarteira;
    private JTable tbQuadro, tbCarteira;
    private DefaultTableModel dm, dmCarteira;
    private static String nome;
    private JLabel lbPs, lbLr, lbRecebidoPs, lbRecebidoLr, lbImportado, lbLogo, lbUsuario;
    private JButton btMarcar, btDesmarcar;
    private JComboBox cbMes;
    private JTextField tfAno,tfPesquisa;

    public QuadroDocumentacaoGUI(String nome) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            // handle exception
        }
        inicializarComponentes();
        definirEventos();
        this.nome = nome;
        listarCarteira(nome);
        start();
    }

    private void inicializarComponentes() {
        setTitle("Quadro de Documentação");
        setBounds(0, 0, 600, 600);
        setLayout(null);

        lbLogo = new JLabel();
        lbLogo.setBounds(0, 0, 90, 80);
        lbLogo.setIcon(new ImageIcon("C:\\Users\\daniel.freitas\\Documents\\NetBeansProjects\\Conversor de Extratos\\src\\img\\logo.jpg"));
        add(lbLogo);

        lbUsuario = new JLabel("Bem vinda " + nome);
        lbUsuario.setBounds(480, 10, 100, 25);
        add(lbUsuario);
        
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
        cbMes.setBounds(480, 40, 100, 25);
        cbMes.setSelectedIndex(4);
        add(cbMes);
        
        tfAno = new JTextField("2017");
        tfAno.setBounds(480,70,100,25);
        add(tfAno);
        
        lbPs = new JLabel("Presumido & Simples: " + CarteiraCTRL.getPresumidoSimples(nome));
        lbPs.setBounds(10, 100, 150, 25);
        add(lbPs);

        lbLr = new JLabel("Lucro Real: " + CarteiraCTRL.getLucroReal(nome));
        lbLr.setBounds(10, 130, 150, 25);
        add(lbLr);

        lbRecebidoPs = new JLabel("Recebido Presumido & Simples: "+CarteiraCTRL.getRecebidoPresumidoSimples(nome));
        lbRecebidoPs.setBounds(10, 160, 200, 25);
        add(lbRecebidoPs);

        lbRecebidoLr = new JLabel("Recebido Lucro Real: "+CarteiraCTRL.getRecebidoLucroReal(nome));
        lbRecebidoLr.setBounds(10, 190, 200, 25);
        add(lbRecebidoLr);

        lbImportado = new JLabel("Importados: ");
        lbImportado.setBounds(480, 100, 200, 25);
        add(lbImportado);
        
        tfPesquisa = new JTextField();
        tfPesquisa.setBounds(10,230,200,25);
        add(tfPesquisa);

        btMarcar = new JButton("Rebebido");
        btMarcar.setBounds(370, 230, 100, 25);
        add(btMarcar);

        btDesmarcar = new JButton("Desmarcar");
        btDesmarcar.setBounds(480, 230, 100, 25);
        add(btDesmarcar);

        scCarteira = new JScrollPane();
        scCarteira.setBounds(10, 260, 570, 300);

        dmCarteira = new DefaultTableModel(new Object[]{
            "Cód", "Empresa", "Regime", "Priori.", "Colaborador Cont."
        }, 0);

        tbCarteira = new JTable(dmCarteira);

        tbCarteira.setRowHeight(25);
        tbCarteira.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbCarteira.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbCarteira.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbCarteira.getColumnModel().getColumn(3).setPreferredWidth(20);
        tbCarteira.getColumnModel().getColumn(3).setPreferredWidth(50);

        scCarteira.setViewportView(tbCarteira);
        add(scCarteira);
    }

    private void definirEventos() {
        atualizarLista();
        btMarcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tbCarteira.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                } else {
                    int cod = (int) tbCarteira.getValueAt(row, 0);
                    CarteiraCTRL.marcarRecebido(cod,5,2017);
                    listarCarteira(nome);
                }

            }
        });
        
        btDesmarcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tbCarteira.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                } else {
                    int cod = (int) tbCarteira.getValueAt(row, 0);
                    CarteiraCTRL.desmarcarRecebido(cod);
                    atualizarLista();
                }
            }
        });
        
        cbMes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                listarCarteira(nome);
            }
        });
        
        tfPesquisa.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pesquisar(tfPesquisa.getText());
            }
        });
    }
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if(tfPesquisa.getText().equals("")){
                listarCarteira(nome);
            }
            
        }
    };
    
    public void start(){
        t.scheduleAtFixedRate(task, 1000, 5000);
    }
    public static void abrir() {
        QuadroDocumentacaoGUI q = new QuadroDocumentacaoGUI("JOARLLA");
        q.setVisible(true);
        q.setLocationRelativeTo(null);
        q.setResizable(false);
        //q.setExtendedState(JFrame.MAXIMIZED_BOTH);
        q.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
                table.getColumnModel().getColumn(3).setCellRenderer(this);
                int cod = (int) table.getModel().getValueAt(row, 0);

                int status = CarteiraCTRL.getStatus(cod,cbMes.getSelectedIndex()+1,Integer.parseInt(tfAno.getText()));
                if (status == 3) {
                    c.setBackground(verde);
                } else if(status == 2){
                    c.setBackground(amarelo);
                }else{
                    c.setBackground(vermelho);
                }

                return c;
            }
        });
    }
    
    public void pesquisar(String txt) {
        dmCarteira.setRowCount(0);
        for (Demanda d : CarteiraCTRL.pesquisa(txt, lista)) {
            dmCarteira.addRow(new Object[]{
                d.getCod(),
                d.getEmpresa(),
                d.getRegime(),
                d.getPrioridadeRelacionamento(),
                d.getColaboradorContabil(),
                d.getColaboradorRelacionamento()
            });
        }
    }
}
