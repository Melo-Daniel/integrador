/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.CarteiraDAO;
import java.util.ArrayList;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class CarteiraCTRL {

    static CarteiraDAO c = new CarteiraDAO();

    public static ArrayList<Demanda> listarCarteita(String nome) {
        return c.listarDemandas(nome);
    }
    
    public static ArrayList<Demanda> listarLucroReal(String nome) {
        return c.listarLucroReal(nome);
    }
    
    public static ArrayList<Demanda> listarRegime(String nome,String regime) {
        return c.listarRegime(nome,regime);
    }

    public static int getPresumidoSimples(String nome) {
        return c.getPresumidoSimples(nome);
    }

    public static int getLucroReal(String nome) {
        return c.getLucroReal(nome);
    }

    public static int getRecebidoPresumidoSimples(String nome) {
        return c.getRecebidoPresumidoSimples(nome);
    }

    public static int getRecebidoLucroReal(String nome) {
        return c.getRecebidoLucroReal(nome);
    }

    public static int getStatus(int cod,int mes,int ano) {
        return c.getStatus(cod,mes,ano);
    }

    public static boolean marcarRecebido(int cod,int mes,int ano) {
        return c.marcarRecebida(cod, mes, ano);
    }

    public static boolean desmarcarRecebido(int cod) {
        return c.desmarcarRecebida(cod);
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
}
