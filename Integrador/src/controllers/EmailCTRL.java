/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.CarteiraDAO;
import DAO.EmailDAO;
import SendEmail.SendEmail;
import helpers.LucroRealMSG;
import helpers.PresumidoSimplesMSG;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Colaborador;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class EmailCTRL {

    static EmailDAO e = new EmailDAO();
    CarteiraDAO c = new CarteiraDAO();

    public static boolean enviarEmail(int mes, int ano, ArrayList<Demanda> list,String nome) {
        int i = 0;
        Colaborador c = ColaboradorCTRL.getColaborador(nome);
        for (Demanda d : list) {
            System.out.println(d.getResponsavel());
            if (validarEnvio(d.getCod(), mes, ano)) {
                String msg = "";
                if(d.getRegime().equals("LUCRO REAL")){
                    msg = new LucroRealMSG(d.getResponsavel()).gerarMsg();
                }else{
                    msg = new PresumidoSimplesMSG(d.getResponsavel()).gerarMsg();
                }
                
                SendEmail.send(d.getEmail(),
                        "Documentos - "+ d.getEmpresa(),
                        msg,
                        c.getEmail(),
                        c.getSenhaEmail());
                e.marcarEnviado(d.getCod(), mes, ano);
                i++;
                System.out.println(i + " de " + list.size());
            }else{
                System.out.println("Email da competencia ja enviado");
            }

        }
        if (i > 0) {
            return true;
        }
        return false;
    }
    
    public static boolean enviarEmailSelecionado(int mes, int ano, Demanda d,String nome) {
        int i = 0;
        Colaborador c = ColaboradorCTRL.getColaborador(nome);
        
            System.out.println(d.getResponsavel());
            if (validarEnvio(d.getCod(), mes, ano)) {
                String msg = "";
                if(d.getRegime().equals("LUCRO REAL")){
                    msg = new LucroRealMSG(d.getResponsavel()).gerarMsg();
                }else{
                    msg = new PresumidoSimplesMSG(d.getResponsavel()).gerarMsg();
                }
                
                SendEmail.send("daniel.melo42@outlook.com",
                        "Documentos - "+ d.getEmpresa(),
                        msg,
                        c.getEmail(),
                        c.getSenhaEmail());
                e.marcarEnviado(d.getCod(), mes, ano);
                i++;
                
            }else{
                JOptionPane.showMessageDialog(null, "Email desta competência já foi enviado!\n"
                        + "Para evitar o envio excessivo de emails, interrompemos o envio.");
            }

        
        if (i > 0) {
            return true;
        }
        return false;
    }

    public static boolean validarEnvio(int cod, int mes, int ano) {
        return e.validarEnvio(cod, mes, ano);
    }
}
