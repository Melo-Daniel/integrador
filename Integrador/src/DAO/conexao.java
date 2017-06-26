/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author daniel.freitas
 */
public class conexao {
    // Criando as variáveis de conexão e de statement

    private static Connection con;
    private Statement stmt;
    String query = "select name , id from sysobjects";
    // Verificando se o driver JDBC está instalado e pode ser utilizado

   private static String URL ="jdbc:sqlserver://SRVTI\\SQLEXPRESS:1433;" +  
"databaseName=DOCUMENTOS;";//se não for acessar localmente mude localhost pelo nome do servidor  
    private static String usuario = "sa";//esse usuário é um sysadmin ele tem todos os poderes, é bom que se crie um login e usuário a parte  
    private static String password = "12Janine3$";  
   private static String DRIVER ="com.microsoft.sqlserver.jdbc.SQLServerDriver" ;//Esse é o nome do driver, que na internet você vai encontrar de varias maneiras, mas só esse resolveu meus problemas  
  
  
   public static Connection abreConexao() throws SQLException{  
      try {  
  
        Class.forName(DRIVER );  
        Connection con = DriverManager.getConnection(URL,usuario,password);  
        return con;  
  
      } catch (ClassNotFoundException e) {  
               throw new SQLException(e.getMessage());  
  
      }  
   } 

}