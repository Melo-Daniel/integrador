/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SendEmail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.JOptionPane;

public class SendEmailAttachment {

    public static void send(String to,
            String from,
            String username,
            final String password,
            final String path,
            final String img,
            String txt) {
        

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.eliteconsultores.com.br";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            //copy
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(
                    "daniel.melo42@outlook.com"));
            // Set To: header field of the header.

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(MimeUtility.encodeText("SOLICITAÇÃO DE DOCUMENTAÇÃO CONTÁBIL", "utf-8", "B"));
            
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            //primaeira parte html
            
            System.out.println(txt);
            String htmlText = "<H2 style='color:#1a1aff'>Bom dia!</H2>"
                    + ""
                    + "<h4 style='color:#1a1aff'>Prezado Cliente,</h4>"
                    + "<h4 style='color:#1a1aff'>"
                    + txt
                    + "</h4><br>"
                    + "<img src=\"cid:image\">"
                    + "<br><br>"
                    + "<h4 style='color:#1a1aff'>Atenciosamente"
                    + "<br>"
                    + "Central de Relacionamento"
                    + "<br>"
                    + "ELITE Consultores do Brasil"
                    + "<br>"
                    + "Tel: 84 2020 7000/9 9112 0850"
                    + "<br>"
                    + "www.eliteconsultores.com.br</h4>"
                    + "</html>";

            messageBodyPart.setContent(htmlText, "text/html; charset=UTF-8");
            messageBodyPart.setHeader("", username);

            multipart.addBodyPart(messageBodyPart);
            // Now set the actual message
            //messageBodyPart.setText("This is message body");

            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(img);

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // put everything together
            message.setContent(multipart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = path;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
