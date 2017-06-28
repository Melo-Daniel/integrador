/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controllers.CarteiraCTRL;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import models.Demanda;

/**
 *
 * @author daniel.freitas
 */
public class inserir {

    public static void main(String[] args) throws FileNotFoundException, IOException {
//        BufferedReader in;
//        in = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\daniel.freitas\\Desktop\\CARTEIRAS.csv"), "iso-8859-1"));
//        String str = "", content = "";
//        while ((str = in.readLine()) != null) {
//            content += str + "\n";
//        }
//        in.close();
//
//        String txt[] = content.split("\n");
//        for (int i = 0; i < txt.length; i++) {
//            String reg[] = txt[i].split(";");
//            System.out.println(reg[0]);
//            Demanda d = new Demanda(Integer.parseInt(reg[0]),1, 1, 1, reg[1], reg[3], "-----", reg[7], reg[9], reg[8]);
//            new QuadroDAO().inserir(d);
//        }

        ArrayList<Demanda> lista = CarteiraCTRL.listarRegime("PEDRITA","LUCRO REAL");
        System.out.println(lista);
        for(Demanda d : lista){
            System.out.println(d.getEmail());
            
        }
        System.out.println(lista.size());
    }
}
