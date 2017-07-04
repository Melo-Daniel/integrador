/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import SendEmail.SendEmail;
import SendEmail.SendEmailAttachment;
import controllers.CarteiraCTRL;
import controllers.ColaboradorCTRL;
import helpers.Suporte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import models.Colaborador;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class EmailPendenteGUI extends JFrame{
    private JLabel lbCodigo,lbArquivo;
    private JTextField tfCodigo,tfArquivo;
    private JButton btEnviar,btCancelar,btAbrir;
    
    public EmailPendenteGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            // handle exception
        }
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setTitle("Arquivos Pendentes");
        setBounds(0,0,345,140);
        setLayout(null);
        
        lbCodigo = new JLabel("Código da Empresa");
        lbCodigo.setBounds(10,20,150,25);
        add(lbCodigo);
        
        tfCodigo = new JTextField();
        tfCodigo.setBounds(10,45,100,25);
        add(tfCodigo);
        
        lbArquivo = new JLabel("Arquivo");
        lbArquivo.setBounds(130,20,150,25);
        add(lbArquivo);
        
        tfArquivo = new JTextField();
        tfArquivo.setBounds(130,45,200,25);
        add(tfArquivo);
        
        btAbrir = new JButton("Abrir");
        btAbrir.setBounds(130,80,95,25);
        add(btAbrir);
        
        btEnviar = new JButton("Enviar");
        btEnviar.setBounds(235,80,95,25);
        add(btEnviar);
        
    }

    private void definirEventos() {
        btAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfArquivo.setText(Suporte.abrirArquivo());
            }
        });
        
        btEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Colaborador c = ColaboradorCTRL.getColaborador(ColaboradorCTRL.getUsuariologado());
                Demanda d = CarteiraCTRL.getEmpresaCodigo(ColaboradorCTRL.getUsuariologado(), Integer.parseInt(tfCodigo.getText()));
                
                SendEmailAttachment.send(d.getEmail(), 
                        c.getEmail(), 
                        c.getEmail(),
                        c.getSenhaEmail(), 
                        tfArquivo.getText());
                JOptionPane.showMessageDialog(null, "Email enviado com sucesso!");
            }
        });
    } 
    
    public void abrir() {
        EmailPendenteGUI e = new EmailPendenteGUI();
        e.setVisible(true);
        e.setResizable(false);
        e.setLocationRelativeTo(null);
    }
    
}
