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
    Timer t = new Timer();
    private JScrollPane scQuadro;
    private JTable tbQuadro;
    private DefaultTableModel dm;
    private JButton btMarcar, btDesmarcar;
    private JTextField tfPesquisa,tfAno;
    private JComboBox cbMes;
    private JLabel lbPs, lbLr, lbRecebidoPs, lbRecebidoLr, lbImportado, lbLogo, lbUsuario;
    private ArrayList<Demanda> lista = AdministradorCTRL.listar();
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
        setBounds(0, 0, 700, 600);
        setLayout(null);
        
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

        lbImportado = new JLabel("Importados: "+AdministradorCTRL.getImportadoGeral());
        lbImportado.setBounds(580, 100, 200, 25);
        add(lbImportado);
        
        tfPesquisa = new JTextField();
        tfPesquisa.setBounds(10,230,200,25);
        add(tfPesquisa);
        
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
                    if(AdministradorCTRL.marcarImportada(cod,5,2017)){
                        listarEmpresas();
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
                listarEmpresas();
            }
        });
    }

    
    public static void main(String[] args) {
        AdministradorGUI a = new AdministradorGUI();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        a.setResizable(false);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void start(){
        t.scheduleAtFixedRate(task, 1000, 5000);
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
        Color vermelho = new Color(255, 204, 204);
        Color amarelo = new Color(255, 255, 128);
        Color verde = new Color(179, 255, 217);
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
                    c.setBackground(amarelo);
                } else {
                    c.setBackground(vermelho);
                }

                return c;
            }
        });
    }
}