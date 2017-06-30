/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.AdministradorCTRL;
import controllers.CarteiraCTRL;
import controllers.ColaboradorCTRL;
import helpers.Suporte;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

    private JMenuBar mbPrincipal;
    private JMenu mnArquivos, mnAjuda, mnSair;
    private JMenuItem miPerfil, miEmail, miPendente;

    private Timer t = new Timer();
    private ArrayList<Demanda> lista = CarteiraCTRL.listarCarteita(nome);
    private JScrollPane scQuadro, scCarteira;
    private JTable tbQuadro, tbCarteira;
    private DefaultTableModel dm, dmCarteira;
    private static String nome;
    private JLabel lbPs, lbLr, lbRecebidoPs, lbRecebidoLr, lbImportado, lbLogo, lbUsuario, lbAzul, lbVermelho, lbVerde;
    private JButton btMarcar, btDesmarcar;
    private JComboBox cbMes;
    private JTextField tfAno, tfPesquisa;
    private Color azulescuro = new Color(0, 82, 102);
    Color vermelho = new Color(255, 102, 102);
    Color azul = new Color(128, 229, 255);
    Color verde = new Color(77, 255, 77);

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
        ColaboradorCTRL.setUsuariologado(nome);
    }

    private void inicializarComponentes() {
        setTitle("Quadro de Documentação");
        setBounds(0, 0, 595, 670);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        mbPrincipal = new JMenuBar();
        setJMenuBar(mbPrincipal);

        mnArquivos = new JMenu("Email");
        mbPrincipal.add(mnArquivos);

        mnAjuda = new JMenu("Ajuda");
        mbPrincipal.add(mnAjuda);

        miPerfil = new JMenuItem("Perfil");
        mnArquivos.add(miPerfil);

        miEmail = new JMenuItem("Geral");
        mnArquivos.add(miEmail);

        miPendente = new JMenuItem("Pendente");
        mnArquivos.add(miPendente);

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
        tfAno.setBounds(480, 70, 100, 25);
        add(tfAno);

        lbPs = new JLabel("Presumido & Simples: " + CarteiraCTRL.getPresumidoSimples(nome));
        lbPs.setBounds(10, 100, 150, 25);
        add(lbPs);

        lbLr = new JLabel("Lucro Real: " + CarteiraCTRL.getLucroReal(nome));
        lbLr.setBounds(10, 130, 150, 25);
        add(lbLr);
        int rec = CarteiraCTRL.getRecebido(nome, cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), "SIMPLES") + CarteiraCTRL.getRecebido(nome, cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), "L. PRESUMIDO");

        lbRecebidoPs = new JLabel("Recebido Presumido & Simples: " + rec);
        lbRecebidoPs.setBounds(10, 160, 200, 25);
        add(lbRecebidoPs);

        lbRecebidoLr = new JLabel("Recebido Lucro Real: " + CarteiraCTRL.getRecebido(nome, cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()), "LUCRO REAL"));
        lbRecebidoLr.setBounds(10, 190, 200, 25);
        add(lbRecebidoLr);

        lbImportado = new JLabel("" + CarteiraCTRL.getImpAmount(nome, cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText())));
        lbImportado.setBounds(520, 140, 200, 50);
        lbImportado.setFont(new Font("Arial", Font.BOLD, 45));
        lbImportado.setForeground(azulescuro);
        add(lbImportado);

        tfPesquisa = new JTextField();
        tfPesquisa.setBounds(10, 230, 200, 25);
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
        atualizarLista();
        btMarcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tbCarteira.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                } else {
                    int cod = (int) tbCarteira.getValueAt(row, 0);
                    int resp = 0;
                    ArrayList<File> l = Suporte.listarExtatos(cod, cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()));

                    String corpo = "";
                    if (l != null && Suporte.verificarArquivo(cod, String.valueOf(cbMes.getSelectedIndex() + 1))) {
                        for (File f : l) {

                            corpo += (Suporte.ajustarCaminho(f.getAbsolutePath())) + "\n";
                        }
                        resp = JOptionPane.showConfirmDialog(null, "Estes são os arquivos nas pastas referentes a competência do mês " + (cbMes.getSelectedIndex() + 1) + "\n"
                                + corpo);

                    } else {
                        resp = JOptionPane.showConfirmDialog(null, "Não existem arquivos nas pastas do mês selecionado!\n"
                                + " Tem certeza que deseja marcar os documentos como recebidos?");
                    }

                    if (resp == 0) {
                        CarteiraCTRL.marcarRecebido(cod, cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()));
                        listarCarteira(nome);
                        atualizarResultados();
                    }
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

        miEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciadorEmailGUI g = new GerenciadorEmailGUI(nome);
                g.abrir();
            }
        });

        miPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerfilGUI(nome).abrir(nome);
            }
        });

        miPendente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmailPendenteGUI().abrir();
            }
        });

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (tfPesquisa.getText().equals("")) {
                listarCarteira(nome);
                atualizarResultados();
            }

        }
    };

    public void start() {
        t.scheduleAtFixedRate(task, 10000, 10000);
    }

    public static void abrir() {
        QuadroDocumentacaoGUI q = new QuadroDocumentacaoGUI(nome);
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

                int status = CarteiraCTRL.getStatus(cod, cbMes.getSelectedIndex() + 1, Integer.parseInt(tfAno.getText()));
                if (status == 3) {
                    c.setBackground(verde);
                } else if (status == 2) {
                    c.setBackground(azul);
                } else {
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

    public void atualizarResultados() {
        int mes = cbMes.getSelectedIndex() + 1;
        int ano = Integer.parseInt(tfAno.getText());

        int ps = CarteiraCTRL.getRecebido(nome, mes, ano, "SIMPLES") + CarteiraCTRL.getRecebido(nome, mes, ano, "L. PRESUMIDO");
        lbImportado.setText(String.valueOf(CarteiraCTRL.getImpAmount(nome, mes, ano)));
        lbRecebidoLr.setText("Recebido Lucro Real: " + (CarteiraCTRL.getRecebido(nome, mes, ano, "LUCRO REAL")));
        lbRecebidoPs.setText("Recebido Presumido & Simples: " + (ps));
    }
}
