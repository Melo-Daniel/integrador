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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Quadro;
import models.Demanda;
/**
 *
 * @author daniel.freitas
 */
public class QuadroDAO {
    private  PreparedStatement stm;
    private  ResultSet rs;
    private static Connection con;

    public QuadroDAO() {
        try {
            this.con = conexao.abreConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Quadro> listaQuadro(){
        ArrayList<Quadro> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM TB_CARTEIRAS";
        try {
            stm = con.prepareStatement(sql);
            
            rs = stm.executeQuery();
            
            while(rs.next()){
                System.out.println(rs.getString("CAR_NOME"));
                
            }
            
        } catch (SQLException ex) {
            
        }
        
        return lista;
    }
    public boolean inserir(Demanda d){
        
        String sql = "INSERT TB_DOCUMENTACAO VALUES ("+d.getCod()+","
                + "'"+d.getEmpresa()+"',"
                + "'"+d.getRegime()+"',"
                + ""+d.getPrioridadeContabil()+","
                + ""+d.getPrioridadeRelacionamento()+","
                + "'"+d.getColaboradorRelacionamento()+"',"
                + ""+d.getStatus()+","
                + "'"+d.getEmail()+"',"
                + "'"+d.getResponsavel()+"'"
                + ")";
                
        try{
            stm = con.prepareStatement(sql);
            return !stm.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
