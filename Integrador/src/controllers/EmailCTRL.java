/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.CarteiraDAO;
import DAO.EmailDAO;
import SendEmail.SendEmail;
import java.util.ArrayList;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class EmailCTRL {
    static EmailDAO e = new EmailDAO();
    CarteiraDAO c = new CarteiraDAO();
    
    public static boolean enviarEmail(int mes, int ano, ArrayList<Demanda> list ){
        int i = 0;
        for(Demanda d : list){
            System.out.println(d.getResponsavel());
            SendEmail.send(d.getEmail(), 
                    "teste", 
                    "Estamos fazendo testes.", 
                    "daniel.melo42@outlook.com", 
                    "Dado201094");
            e.marcarEnviado(d.getCod(), mes, ano);
            i++;
            System.out.println(i +" de "+ list.size());
        }
        if(i == list.size()){
            return true;
        }
        return false;
    }
}
