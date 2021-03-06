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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class CarteiraDAO {

    private PreparedStatement stm;
    private ResultSet rs;
    private Connection con;

    public CarteiraDAO() {
        try {
            this.con = conexao.abreConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Demanda> listarDemandas(String nome) {
        ArrayList<Demanda> lista = new ArrayList<>();

        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_COLABORADOR_RELACIONAMENTO = '" + nome + "' ORDER BY DOC_COD";

        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                Demanda d = new Demanda();

                d.setCod(rs.getInt("DOC_COD"));
                d.setStatus(rs.getInt("DOC_STATUS"));
                d.setEmpresa(rs.getString("DOC_EMPRESA"));
                d.setRegime(rs.getString("DOC_REGIME"));
                d.setPrioridadeRelacionamento(rs.getInt("DOC_PRIORIDADE_RELACIONAMENTO"));
                d.setColaboradorContabil("-----");

                lista.add(d);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lista;
    }
    
    public Demanda getEmpresaCodigo(String nome, int cod) {
        Demanda d = new Demanda();

        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_COLABORADOR_RELACIONAMENTO = '" + nome + "' AND DOC_COD = "+cod;
        System.out.println(query);
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                
                d.setCod(rs.getInt("DOC_COD"));
                d.setStatus(rs.getInt("DOC_STATUS"));
                d.setEmpresa(rs.getString("DOC_EMPRESA"));
                d.setRegime(rs.getString("DOC_REGIME"));
                d.setEmail(rs.getString("DOC_EMAIL"));
                d.setResponsavel(rs.getString("DOC_RESPONSAVEL"));
                d.setPrioridadeRelacionamento(rs.getInt("DOC_PRIORIDADE_RELACIONAMENTO"));
                d.setColaboradorContabil("-----");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return d;
    }

    public int getPresumidoSimples(String nome) {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_COLABORADOR_RELACIONAMENTO = '" + nome + "' AND (DOC_REGIME = 'SIMPLES' OR DOC_REGIME = 'PRESUMIDO')";
        int cont = 0;
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                cont++;
            }
            return cont;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cont;
    }
    
    public ArrayList<Demanda> listarRegime(String nome,String regime) {
        ArrayList<Demanda> lista = new ArrayList<>();
        
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_COLABORADOR_RELACIONAMENTO = '" + nome + "' AND (DOC_REGIME = '"+regime+"')";
        int cont = 0;
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                Demanda d = new Demanda();
                d.setCod(rs.getInt("DOC_COD"));
                d.setEmpresa(rs.getString("DOC_EMPRESA"));
                d.setEmail(rs.getString("DOC_EMAIL"));
                d.setRegime(rs.getString("DOC_REGIME"));
                d.setResponsavel(rs.getString("DOC_REPONSAVEL"));
                d.setColaboradorRelacionamento(rs.getString("DOC_COLABORADOR_RELACIONAMENTO"));
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getLucroReal(String nome) {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_COLABORADOR_RELACIONAMENTO = '" + nome + "' AND (DOC_REGIME = 'LUCRO REAL')";
        int cont = 0;
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                cont++;
            }
            return cont;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cont;
    }
    
    public ArrayList<Demanda> listarLucroReal(String nome) {
        ArrayList<Demanda> lista = new ArrayList<>();
        
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_COLABORADOR_RELACIONAMENTO = '" + nome + "' AND (DOC_REGIME = 'LUCRO REAL')";
        int cont = 0;
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                Demanda d = new Demanda();
                d.setCod(rs.getInt("DOC_COD"));
                d.setEmpresa(rs.getString("DOC_EMPRESA"));
                d.setEmail(rs.getString("DOC_EMAIL"));
                d.setRegime(rs.getString("DOC_REGIME"));
                d.setResponsavel(rs.getString("DOC_REPONSAVEL"));
                d.setColaboradorRelacionamento(rs.getString("DOC_COLABORADOR_RELACIONAMENTO"));
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public int getRecebido(String nome,int mes,int ano,String regime) {
        String query = "SELECT DISTINCT(RAR_DOC_COD),DOC_EMPRESA \n" +
"	FROM TB_RECEBIMENTO_ARQUIVOS \n" +
"		JOIN TB_DOCUMENTACAO \n" +
"		ON(RAR_DOC_COD = DOC_COD) \n" +
"	WHERE RAR_STATUS = 2 AND \n" +
"		DOC_COLABORADOR_RELACIONAMENTO = '"+nome+"' AND\n" +
"		 RAR_MES = "+mes+" AND \n" +
"		 RAR_ANO = "+ano+" AND \n" +
"		 DOC_REGIME = '"+regime+"'";
        int cont = 0;
        try {
            stm = con.prepareStatement(query);
            
            rs = stm.executeQuery();

            while (rs.next()) {
                cont++;
            }
            return cont;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cont;
    }

    public int getRecebidoLucroReal(String nome) {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_COLABORADOR_RELACIONAMENTO = '" + nome + "' AND (DOC_REGIME = 'LUCRO REAL') AND DOC_STATUS = 2";
        int cont = 0;
        try {
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                cont++;
            }
            return cont;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cont;
    }
    
    public int getStatus(int cod,int mes,int ano) {
        String query = "SELECT RAR_STATUS FROM TB_RECEBIMENTO_ARQUIVOS WHERE RAR_DOC_COD = ? AND RAR_MES = ? AND RAR_ANO = ?";
        int codigo = 0;
        try {
            stm = con.prepareStatement(query);  
            stm.setInt(1, cod);
            stm.setInt(2, mes);
            stm.setInt(3, ano);
            rs = stm.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("RAR_STATUS");
            }
            return codigo;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int getEmailStatus(int cod,int mes,int ano) {
        String query = "SELECT EMA_STATUS FROM TB_EMAILS_ENVIADOS WHERE EMA_DOC_COD = ? AND EMA_MES = ? AND EMA_ANO = ?";
        int codigo = 0;
        try {
            stm = con.prepareStatement(query);  
            stm.setInt(1, cod);
            stm.setInt(2, mes);
            stm.setInt(3, ano);
            rs = stm.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("EMA_STATUS");
            }
            return codigo;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean marcarRecebida(int cod,int mes,int ano) {
        String query = "INSERT TB_RECEBIMENTO_ARQUIVOS VALUES(?,?,?,?)";

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

    public boolean desmarcarRecebida(int cod,int mes,int ano) {
        String query = "UPDATE TB_RECEBIMENTO_ARQUIVOS "
                + "SET RAR_STATUS = 7 "
                + "WHERE "
                + "RAR_DOC_COD = " + cod + " and "
                + "RAR_MES = "+ mes + " and "
                + "RAR_ANO = " + ano;
        System.out.println(query);
        try {
            stm = con.prepareStatement(query);
            return !stm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean validarMarcacao(int cod,int mes,int ano){
        String query = "SELECT RAR_STATUS FROM TB_RECEBIMENTO_ARQUIVOS WHERE RAR_DOC_COD = ? AND RAR_MES = ? AND RAR_ANO = ?";
        int status = 0;
        try {
            stm = con.prepareStatement(query);
            stm.setInt(1, cod);
            stm.setInt(2, mes);
            stm.setInt(3, ano);
            
            stm.executeQuery();
            
            while(rs.next()){
                status = rs.getInt("RAR_STATUS");
            }
            if(status == 3){
                return false;
            }else{
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        Demanda[] d = new Demanda[12];
        return false;
    }
    
    public int getImpAmount(String nome,int mes,int ano){
        String query = "SELECT DISTINCT(RAR_DOC_COD),DOC_EMPRESA \n" +
"	FROM TB_RECEBIMENTO_ARQUIVOS \n" +
"		JOIN TB_DOCUMENTACAO \n" +
"		ON(RAR_DOC_COD = DOC_COD) \n" +
"	WHERE RAR_STATUS = 3 AND \n" +
"		DOC_COLABORADOR_RELACIONAMENTO = '"+nome+"' AND\n" +
"		 RAR_MES = "+mes+" AND \n" +
"		 RAR_ANO = "+ano+"";
        
        try {
            stm = con.prepareStatement(query);
            
            rs = stm.executeQuery();
            int i = 0;
            while(rs.next()){
                i++;
            }
            return i;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return 0;
    }
}
