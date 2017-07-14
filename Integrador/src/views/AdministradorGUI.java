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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class AdministradorGUI extends JFrame {
    
    private static Preferences pref = Preferences.userRoot();
    
    private static String m = pref.get("mes", "");
    
    Timer t = new Timer();
    private JScrollPane scQuadro;
    private JTable tbQuadro;
    private DefaultTableModel dm;
    private JButton btMarcar, btDesmarcar,btArquivoInvalido;
    private JTextField tfPesquisa,tfAno;
    private JComboBox cbMes;
    private JLabel lbPs,lbAzul,lbVermelho,lbVerde, lbLr, lbRecebidoPs, lbRecebidoLr, lbImportado, lbLogo, lbUsuario;
    private ArrayList<Demanda> lista = AdministradorCTRL.listar();
    Color vermelho = new Color(255, 102, 102);
    Color azul = new Color(128, 229, 255);
    Color verde = new Color(77, 255, 77);
    Color azulescuro = new Color(0, 82, 102);
    Color laranja = new Color(255, 102, 0);
    
    public AdministradorGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            // handle exception
        }
        inicializarComponentes();
        definirEventos();
        listarEmpresas();
        atualizarLista();
        start();
    }

    private void inicializarComponentes() {
        setTitle("Administrador");
        setBounds(0, 0, 700, 640);
        setLayout(null);
        
        lbLogo = new JLabel();
        lbLogo.setBounds(0, 0, 90, 80);
        lbLogo.setIcon(new ImageIcon("C:\\Users\\daniel.freitas\\Documents\\NetBeansProjects\\Conversor de Extratos\\src\\img\\logo.jpg"));
        add(lbLogo);
        
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
        cbMes.setBounds(580, 40, 100, 25);
        cbMes.setSelectedIndex(4);
        add(cbMes);
        
        tfAno = new JTextField("2017");
        tfAno.setBounds(580,70,100,25);
        add(tfAno);
        
        lbPs = new JLabel("Presumido & Simples: "+AdministradorCTRL.getPresumidoSimplesAdm());
        lbPs.setBounds(10, 100, 150, 25);
        add(lbPs);

        lbLr = new JLabel("Lucro Real: "+AdministradorCTRL.getLucroRealAdm());
        lbLr.setBounds(10, 130, 150, 25);
        add(lbLr);

        lbRecebidoPs = new JLabel("Recebido Presumido & Simples: "+AdministradorCTRL.getRecebidoPresumidoSimplesAdm());
        lbRecebidoPs.setBounds(10, 160, 200, 25);
        add(lbRecebidoPs);

        lbRecebidoLr = new JLabel("Recebido Lucro Real: "+AdministradorCTRL.getRecebidoLucroRealAdm());
        lbRecebidoLr.setBounds(10, 190, 200, 25);
        add(lbRecebidoLr);

        lbImportado = new JLabel("" + AdministradorCTRL.getImpAmount(cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText())));
        lbImportado.setBounds(600, 140, 200, 50);
        lbImportado.setFont(new Font("Arial", Font.BOLD, 45));
        lbImportado.setForeground(azulescuro);
        add(lbImportado);
        
        tfPesquisa = new JTextField();
        tfPesquisa.setBounds(10,230,200,25);
        add(tfPesquisa);
        
        btArquivoInvalido = new JButton("Invalidar");
        btArquivoInvalido.setBounds(360, 230, 100, 25);
        add(btArquivoInvalido);
        
        btMarcar = new JButton("Importado");
        btMarcar.setBounds(470, 230, 100, 25);
        add(btMarcar);

        btDesmarcar = new JButton("Desmarcar");
        btDesmarcar.setBounds(580, 230, 100, 25);
        add(btDesmarcar);

        scQuadro = new JScrollPane();
        scQuadro.setBounds(10, 260, 670, 300);

        dm = new DefaultTableModel(new Object[]{
            "Cód", "Empresa", "Regime", "Priori.", "Contábil", "Relacionamento"
        }, 0);

        tbQuadro = new JTable(dm);

        tbQuadro.setRowHeight(25);
        tbQuadro.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbQuadro.getColumnModel().getColumn(1).setPreferredWidth(350);
        tbQuadro.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbQuadro.getColumnModel().getColumn(3).setPreferredWidth(20);
        tbQuadro.getColumnModel().getColumn(3).setPreferredWidth(50);

        scQuadro.setViewportView(tbQuadro);
        add(scQuadro);
        
        lbVermelho = new JLabel("Pendente");
        lbVermelho.setBounds(10, 580, 100, 20);
        lbVermelho.setForeground(vermelho);
        lbVermelho.setFont(new Font("Arial", Font.BOLD, 13));
        add(lbVermelho);

        lbAzul = new JLabel("Recebido");
        lbAzul.setBounds(100, 580, 100, 20);
        lbAzul.setForeground(azul);
        lbAzul.setFont(new Font("Arial", Font.BOLD, 13));
        add(lbAzul);

        lbVerde = new JLabel("Importado");
        lbVerde.setBounds(205, 580, 100, 20);
        lbVerde.setForeground(verde);
        lbVerde.setFont(new Font("Arial", Font.BOLD, 13));
        add(lbVerde);
    }

    private void definirEventos() {
        btMarcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tbQuadro.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                }else{
                    int cod = (int)tbQuadro.getValueAt(row, 0);
                    int mes = cbMes.getSelectedIndex()+1;
                    int ano = Integer.parseInt(tfAno.getText());
                    
                    if(AdministradorCTRL.marcarImportada(cod,mes,ano)){
                        listarEmpresas();
                    }
                }
            }
        });
        
        btArquivoInvalido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tbQuadro.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                }else{
                    int cod = (int)tbQuadro.getValueAt(row, 0);
                    int mes = cbMes.getSelectedIndex()+1;
                    int ano = Integer.parseInt(tfAno.getText());
                    
                    int res = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja marcar esses arquivos como inválidos?");
                    if(res == 0){
                        AdministradorCTRL.marcarArquivoInvalido(cod, mes, ano);
                    }
                }
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
        
        cbMes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                pref.put("mes", (String)cbMes.getSelectedItem());
                listarEmpresas();
            }
        });
    }

    
    public void abrir() {
        AdministradorGUI a = new AdministradorGUI();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        a.setResizable(false);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void start(){
        t.scheduleAtFixedRate(task, 10000, 10000);
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if(tfPesquisa.getText().equals("")){
                listarEmpresas();
            }
        }
    };
    
    public void listarEmpresas() {
        dm.setRowCount(0);
        for (Demanda d : lista) {
            dm.addRow(new Object[]{
                d.getCod(),
                d.getEmpresa(),
                d.getRegime(),
                d.getPrioridadeRelacionamento(),
                d.getColaboradorContabil(),
                d.getColaboradorRelacionamento()
            });
        }
    }
    
    public void pesquisar(String txt) {
        dm.setRowCount(0);
        for (Demanda d : AdministradorCTRL.pesquisa(txt, lista)) {
            dm.addRow(new Object[]{
                d.getCod(),
                d.getEmpresa(),
                d.getRegime(),
                d.getPrioridadeRelacionamento(),
                d.getColaboradorContabil(),
                d.getColaboradorRelacionamento()
            });
        }
    }

    public void atualizarLista() {
        
        tbQuadro.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                } else if (status == 2) {
                    c.setBackground(azul);
                } else if (status == 5) {
                    c.setBackground(laranja);
                }
                else {
                    c.setBackground(vermelho);
                }

                return c;
            }
        });
    }
}
