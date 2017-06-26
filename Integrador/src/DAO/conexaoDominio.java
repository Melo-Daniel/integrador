/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daniel.freitas
 */
public class conexaoDominio {
    //sybase
    static Connection con = null;
    private static final String usuario = "externo";
    private static final String senha = "123";
    private static final String driver = "com.sybase.jdbc3.jdbc.SybDriver";
    private static final String url = "jdbc:jtds:sybase://Sucesso:5000/DOCUMENTOS";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
        return con;
    }
}
