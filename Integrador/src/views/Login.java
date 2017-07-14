/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ColaboradorCTRL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author daniel.freitas
 */
public class Login extends JFrame {
    
    private static Preferences pref = Preferences.userRoot();
    
    private static String nome = pref.get("usuario", "");
    private static String senha = pref.get("senha", "");
    
    private JLabel lbNome, lbSenha;
    private JPasswordField pfSenha;
    private JTextField tfNome;
    private JButton btEntrar, btCancelar;

    public Login() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            // handle exception
        }
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setTitle("Login");
        setBounds(0, 0, 220, 160);
        setLayout(null);

        lbNome = new JLabel("Nome");
        lbNome.setBounds(10, 10, 100, 25);
        add(lbNome);

        tfNome = new JTextField(nome);
        tfNome.setBounds(60, 10, 100, 25);
        add(tfNome);

        lbSenha = new JLabel("Senha");
        lbSenha.setBounds(10, 50, 100, 25);
        add(lbSenha);

        pfSenha = new JPasswordField();
        pfSenha.setBounds(60, 50, 100, 25);
        add(pfSenha);

        btEntrar = new JButton("Entrar");
        btEntrar.setBounds(10, 90, 90, 25);
        add(btEntrar);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(110, 90, 90, 25);
        add(btCancelar);

    }

    private void definirEventos() {
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        btEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfNome.getText().equals("adm123") && pfSenha.getText().equals("adm123")) {
                    pref.put("usuario", tfNome.getText());
                    
                    new AdministradorGUI().abrir();
                    dispose();
                } else {
                    if (ColaboradorCTRL.Logar(tfNome.getText().toUpperCase(), pfSenha.getText())) {
                        pref.put("usuario", tfNome.getText());
                        new QuadroDocumentacaoGUI(tfNome.getText()).abrir();

                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Nome e/ou sennha incorreto(s)", "Erro ao Logar", 0);
                    }
                }
            }
        });
    }

    public void abrir() {
        Login l = new Login();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
        l.setResizable(false);

        l.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void cancelar() {
        tfNome.setText("");
        pfSenha.setText("");
    }
}
