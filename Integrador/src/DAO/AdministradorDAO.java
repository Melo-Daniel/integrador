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
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class AdministradorDAO {
        private PreparedStatement stm;
    private ResultSet rs;
    private Connection con;

    public AdministradorDAO() {
        try {
            this.con = conexao.abreConexao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
        public ArrayList<Demanda> listar() {
        ArrayList<Demanda> lista = new ArrayList<>();

        String query = "SELECT * FROM TB_DOCUMENTACAO";

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
                d.setColaboradorContabil(rs.getString("DOC_COLABORADOR_CONTABIL"));
                d.setColaboradorRelacionamento(rs.getString("DOC_COLABORADOR_RELACIONAMENTO"));
                lista.add(d);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lista;
    }
    public boolean marcarImportada(int cod,int mes,int ano) {
        String query = "UPDATE TB_RECEBIMENTO_ARQUIVOS SET RAR_STATUS = 3 WHERE RAR_DOC_COD = ? AND RAR_MES = ? AND RAR_ANO = ?";

        try {
            stm = con.prepareStatement(query);
            
            stm.setInt(1, cod);
            stm.setInt(2, mes);
            stm.setInt(3, ano);
            
            return !stm.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int getPresumidoSimplesAdm() {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_REGIME = 'SIMPLES' OR DOC_REGIME = 'PRESUMIDO'";
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

    public int getLucroRealAdm() {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_REGIME = 'LUCRO REAL'";
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
    
        public int getRecebidoPresumidoSimplesAdm() {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE (DOC_REGIME = 'SIMPLES' OR DOC_REGIME = 'PRESUMIDO') AND DOC_STATUS = 2";
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

    public int getRecebidoLucroRealAdm() {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_REGIME = 'LUCRO REAL' AND DOC_STATUS = 2";
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
    
    public int getImportadoGeral() {
        String query = "SELECT * FROM TB_DOCUMENTACAO WHERE DOC_STATUS = 3";
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
}
