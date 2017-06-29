/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import SendEmail.SendEmail;
import SendEmail.SendEmailAttachment;
import controllers.CarteiraCTRL;
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
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class EmailPendenteGUI extends JFrame{
    private JLabel lbCodigo,lbArquivo;
    private JTextField tfCodigo,tfArquivo;
    private JButton btEnviar,btCancelar;
    
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
        
        btEnviar = new JButton("Enviar");
        btEnviar.setBounds(130,80,95,25);
        add(btEnviar);
        
        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(235,80,95,25);
        add(btCancelar);
        
    }

    private void definirEventos() {
        tfArquivo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(tfArquivo.getText().equals("")){
                    tfArquivo.setText(Suporte.abrirArquivo());
                    
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        
        btEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Demanda d = CarteiraCTRL.getEmpresaCodigo("PEDRITA", Integer.parseInt(tfCodigo.getText()));
                SendEmailAttachment.send("danielfmelo21@gmail.com", 
                        "daniel.freitas@eliteconsultores.com.br", 
                        "daniel.freitas@eliteconsultores.com.br", 
                        "natal@12", 
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