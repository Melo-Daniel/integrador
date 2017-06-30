/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.ColaboradorDAO;
import models.Colaborador;

/**
 *
 * @author daniel.freitas
 */
public class ColaboradorCTRL {
    
    private static String usuariologado = "";

    public static String getUsuariologado() {
        return usuariologado;
    }

    public static void setUsuariologado(String usuariologado) {
        ColaboradorCTRL.usuariologado = usuariologado;
    }
    
    private static ColaboradorDAO c = new ColaboradorDAO();
    
    public static boolean Logar(String nome,String senha){
        return c.Login(nome, senha);
    }
    public static Colaborador getColaborador(String nome){
        return c.getColaborador(nome);
    }
    
    public static boolean atualizarDados(String senha,String email,String emailsenha){
        return c.atualizarDados(senha, email, emailsenha);
    }
}
