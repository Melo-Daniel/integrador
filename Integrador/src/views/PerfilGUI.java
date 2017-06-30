/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ColaboradorCTRL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import models.Colaborador;

/**
 *
 * @author daniel.freitas
 */
public class PerfilGUI extends JFrame{
    
    private JLabel lbNome,lbEmail,lbConfirmarEmail,lbSenhaEmail,lbConfirmarSenhaEmail,lbSenhaSistema,lbConfirmarSenhaSistema;
    private JTextField tfEmail,tfConfirmarEmail;
    private JPasswordField tfSenhaEmail,tfConfirmarSenhaEmail,tfSenhaSistema,tfConfirmarSenhaSistema;
    private JButton btSalvar,btCancelar;
    private Colaborador c = null;
    
    public PerfilGUI(String nome) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            // handle exception
        }
        c = ColaboradorCTRL.getColaborador(nome);
        inicializarComponentes();
        definirEventos();
        
        
        System.out.println("asdfasdf "+ c.getNome());
    }

    private void inicializarComponentes() {
        setTitle("Perfil");
        setBounds(0,0,225,500);
        setLayout(null);
        
        lbNome = new JLabel("Nome: "+ c.getNome());
        lbNome.setBounds(10,20,200,25);
        add(lbNome);
        
        lbEmail = new JLabel("Email:");
        lbEmail.setBounds(10,55,200,25);
        add(lbEmail);
        
        tfEmail = new JTextField(c.getEmail());
        tfEmail.setBounds(10,80,200,25);
        add(tfEmail);
        
        lbConfirmarEmail = new JLabel("Confirmar Email:");
        lbConfirmarEmail.setBounds(10,115,200,25);
        add(lbConfirmarEmail);
        
        tfConfirmarEmail = new JTextField(c.getEmail());
        tfConfirmarEmail.setBounds(10,140,200,25);
        add(tfConfirmarEmail);
        
        lbSenhaEmail = new JLabel("Senha do Email:");
        lbSenhaEmail.setBounds(10,175,200,25);
        add(lbSenhaEmail);
        
        tfSenhaEmail = new JPasswordField(c.getSenhaEmail());
        tfSenhaEmail.setBounds(10,200,200,25);
        add(tfSenhaEmail);
        
        lbConfirmarSenhaEmail = new JLabel("Confirmar Senha do Email:");
        lbConfirmarSenhaEmail.setBounds(10,235,200,25);
        add(lbConfirmarSenhaEmail);
        
        tfConfirmarSenhaEmail = new JPasswordField(c.getSenhaEmail());
        tfConfirmarSenhaEmail.setBounds(10,260,200,25);
        add(tfConfirmarSenhaEmail);
        
        lbSenhaSistema = new JLabel("Senha do Sistema:");
        lbSenhaSistema.setBounds(10,295,200,25);
        add(lbSenhaSistema);
        
        tfSenhaSistema = new JPasswordField(c.getSenhaSistema());
        tfSenhaSistema.setBounds(10,320,200,25);
        add(tfSenhaSistema);
        
        lbConfirmarSenhaSistema = new JLabel("Senha do Sistema:");
        lbConfirmarSenhaSistema.setBounds(10,355,200,25);
        add(lbConfirmarSenhaSistema);
        
        tfConfirmarSenhaSistema = new JPasswordField(c.getSenhaSistema());
        tfConfirmarSenhaSistema.setBounds(10,380,200,25);
        add(tfConfirmarSenhaSistema);
        
        btSalvar = new JButton("Salvar");
        btSalvar.setBounds(10,435,95,25);
        add(btSalvar);
        
        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(115,435,95,25);
        add(btCancelar);
    }

    private void definirEventos() {
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfEmail.getText().equals(tfConfirmarEmail.getText()) &&
                        tfSenhaSistema.getText().equals(tfConfirmarSenhaSistema.getText())&&
                        tfSenhaEmail.getText().equals(tfConfirmarSenhaEmail.getText())){
                    ColaboradorCTRL.atualizarDados(tfSenhaSistema.getText(), tfEmail.getText(), tfSenhaEmail.getText());
                    JOptionPane.showMessageDialog(null, "As informações foram atualizadas!");
                }else{
                    JOptionPane.showMessageDialog(null, "As informações não foram confirmadas corretamente!");
                }
                
            }
        });
        
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    
    public void abrir(String nome) {
        PerfilGUI p = new PerfilGUI(nome);
        p.setVisible(true);
        p.setResizable(false);
        p.setLocationRelativeTo(null);
        
    }
    
}
