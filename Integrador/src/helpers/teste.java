/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import controllers.CarteiraCTRL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import models.Demanda;

public class teste {

    public static void main(String[] argv) {
        
        
            System.out.println(447);
            //int i = Suporte.listarExtatos(447, 5, 2017).size();
            int j = Suporte.verificarArquivo(447, "5", 2017).size();
            System.out.println(j);
        

    }

}