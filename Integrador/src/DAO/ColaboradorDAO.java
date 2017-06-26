/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel.freitas
 */
public class ColaboradorDAO {
    private PreparedStatement stm;
    private ResultSet rs;
    private Connection con;

    public ColaboradorDAO() {
        try {
            this.con = conexao.abreConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean Login(String nome,String senha){
      String query = "SELECT * FROM TB_COLABORADORES WHERE COL_NOME = '"+nome+"' AND COL_SENHA = '"+senha+"'";
      int cont = 0;  
      try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            
            while(rs.next()){
                cont++;
            }
            if(cont > 0){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
