/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.AdministradorDAO;
import static controllers.CarteiraCTRL.c;
import java.util.ArrayList;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class AdministradorCTRL {

    static AdministradorDAO c = new AdministradorDAO();

    public static ArrayList<Demanda> listar() {
        return c.listar();
    }

    public static int getPresumidoSimplesAdm() {
        return c.getPresumidoSimplesAdm();
    }

    public static int getLucroRealAdm() {
        return c.getLucroRealAdm();
    }

    public static int getRecebidoPresumidoSimplesAdm() {
        return c.getRecebidoPresumidoSimplesAdm();
    }

    public static int getRecebidoLucroRealAdm() {
        return c.getRecebidoLucroRealAdm();
    }

    public static int getImportadoGeral() {
        return c.getImportadoGeral();
    }
    
    public static boolean marcarImportada(int cod,int mes,int ano){
        return c.marcarImportada(cod,mes,ano);
    }
    
    public static ArrayList<Demanda> pesquisa(String txt,ArrayList<Demanda> list){
        ArrayList<Demanda> lista = new ArrayList<>();
        for(Demanda d : list){
            if(d.getEmpresa().toLowerCase().contains(txt.toLowerCase()) ||
                    String.valueOf(d.getCod()).contains(txt)){
                lista.add(d);
            }
        }
        return lista;
    }
    
    public static int getImpAmount(int mes, int ano) {
        return c.getImpAmount(mes, ano);
    }
    
    public static boolean marcarArquivoInvalido(int cod, int mes, int ano){
        return c.marcarArquivoInvalido(cod, mes, ano);
    }
}
