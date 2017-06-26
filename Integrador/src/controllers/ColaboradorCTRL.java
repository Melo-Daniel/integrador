/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.ColaboradorDAO;

/**
 *
 * @author daniel.freitas
 */
public class ColaboradorCTRL {
    
    private static ColaboradorDAO c = new ColaboradorDAO();
    
    public static boolean Logar(String nome,String senha){
        return c.Login(nome, senha);
    }
}
