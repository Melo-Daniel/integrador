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
public class EmailDAO {
        private PreparedStatement stm;
    private ResultSet rs;
    private Connection con;

    public EmailDAO() {
        try {
            this.con = conexao.abreConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean marcarEnviado(int cod,int mes, int ano){
        String query = "INSERT TB_EMAILS_ENVIADOS VALUES (?,?,?,?)";
            try {
                stm = con.prepareStatement(query);
                
                stm.setInt(1, cod);
                stm.setInt(2, mes);
                stm.setInt(3, ano);
                stm.setInt(4, 2);
                
                return !stm.execute();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        return false;
    }
    
    public boolean validarEnvio(int cod,int mes, int ano){
        String query = "SELECT EMA_ID FROM TB_EMAILS_ENVIADOS WHERE EMA_DOC_COD = ? AND EMA_MES = ? AND EMA_ANO = ? AND EMA_STATUS = 2";
        
            try {
                stm = con.prepareStatement(query);
                stm.setInt(1, cod);
                stm.setInt(2, mes);
                stm.setInt(3, ano);
                
                rs = stm.executeQuery();
                int i = 0;
                while(rs.next()){
                    i++;
                }
                if(i == 0){
                    return true;
                }else{
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
    }
}
