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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import models.Colaborador;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class EmailPendenteGUI extends JFrame {

    private JLabel lbCodigo, lbArquivo, lbImg;
    private JTextField tfCodigo, tfArquivo, tfImg;
    private JButton btEnviar, btCancelar, btAbrir, btAbrirImg;
    private JTextField taEmail;

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
        setBounds(0, 0, 445, 340);
        setLayout(null);

        lbCodigo = new JLabel("Código da Empresa");
        lbCodigo.setBounds(10, 20, 150, 25);
        add(lbCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(10, 45, 100, 25);
        add(tfCodigo);

        lbArquivo = new JLabel("Planilha");
        lbArquivo.setBounds(130, 20, 150, 25);
        add(lbArquivo);

        tfArquivo = new JTextField();
        tfArquivo.setBounds(130, 45, 200, 25);
        add(tfArquivo);

        btAbrir = new JButton("Abrir");
        btAbrir.setBounds(335, 45, 95, 25);
        add(btAbrir);

        lbImg = new JLabel("Imagem");
        lbImg.setBounds(130, 80, 150, 25);
        add(lbImg);

        tfImg = new JTextField();
        tfImg.setBounds(130, 105, 200, 25);
        add(tfImg);

        btAbrirImg = new JButton("Abrir");
        btAbrirImg.setBounds(335, 105, 95, 25);
        add(btAbrirImg);

        taEmail = new JTextField("Para uma melhor qualidade e agilidade das duas informações, solicitamos o envio da documentação contábil pendente de acordo com a planilha abaixo:");
        taEmail.setBounds(10, 160, 420, 100);
        add(taEmail);

        btEnviar = new JButton("Enviar");
        btEnviar.setBounds(335, 270, 95, 25);
        add(btEnviar);

    }

    private void definirEventos() {
        btAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfArquivo.setText(Suporte.abrirArquivo());
            }
        });
        btAbrirImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfImg.setText(Suporte.abrirArquivo());
            }
        });

        btEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int v = 0;
                if (tfCodigo.getText().equals("")
                        || tfArquivo.getText().equals("")
                        || tfImg.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos os campos são obigratórios!");
                }else if(!tfImg.getText().contains(".png") ||
                        !tfImg.getText().contains(".jpeg") ||
                        !tfImg.getText().contains(".jpg") ||
                        !tfImg.getText().contains(".jpg2") ||
                        !tfImg.getText().contains(".mng")){
                    JOptionPane.showMessageDialog(null, "O arquivo não possui uma extenção válida!");
                }else {
                    Colaborador c = ColaboradorCTRL.getColaborador(ColaboradorCTRL.getUsuariologado());
                    try{
                        Demanda d = CarteiraCTRL.getEmpresaCodigo(ColaboradorCTRL.getUsuariologado(), Integer.parseInt(tfCodigo.getText()));
                    }catch(NumberFormatException ex){
                        v++;
                        JOptionPane.showMessageDialog(null, "Código inválido!");
                    }
                    
                    if(v == 0){
                        SendEmailAttachment.send("danielfmelo21@gmail.com",
                            c.getEmail(),
                            c.getEmail(),
                            c.getSenhaEmail(),
                            tfArquivo.getText(),
                            tfImg.getText(),
                            taEmail.getText());
                    JOptionPane.showMessageDialog(null, "Email enviado com sucesso!");
                    }
                }
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
