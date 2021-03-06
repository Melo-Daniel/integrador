/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */
package SendEmail;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendEmail {

    public static void send(String to, String sub, String msg, final String user, final String pass) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.eliteconsultores.com.br");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            //copy
            //message.addRecipient(Message.RecipientType.BCC, new InternetAddress(
                    //"coordenadores@eliteconsultores.com.br"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Ops! Aparentemente aconteceu um erro.\n"
                    + "Tente novamente, se o erro persistir entre em contato com o setor de TI\n"
                    + "Ou seja, Elton! :)");
            throw new RuntimeException(e);
        }

    }
}
