/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */
package integrador;

import SendEmail.SendEmail;
import DAO.conexaoDominio;
import com.sybase.jdbc3.a.a.c;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/**
 *
 * @author daniel.freitas
 */
public class teste {

    static Connection c = null;

    public teste() throws SQLException {
        
    }

    public static void main(String[] args) throws SQLException, EmailException {
        SendEmail.send("danielfmelo21@gmail.com", 
                "ola", 
                "teste de email, ta tudo correndo super tranquilo", 
                "daniel.melo42@outlook.com", 
                "Dado201094");
    }
}
