/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.File;

/**
 *
 * @author daniel.freitas
 */
public class Suporte {

    public static String formatCodigo(int cod) {

        String codigo = String.valueOf(cod);
        int i = 0;

        while (codigo.length() < 4) {
            codigo = "0" + codigo;
            i++;
        }

        return codigo;
    }

    public static boolean verificarArquivo(int cod, String mes) {
        int cont = 0;
        File f = new File("M:");
        File fs[] = f.listFiles();
        String codigo = (Suporte.formatCodigo(cod));
        for (File file : fs) {
            File emp = null;
            File matriz = null;
            if (file.getName().contains(codigo)) {

                File grupo[] = file.listFiles();
                for (File f1 : grupo) {
                    if (f1.getName().contains(codigo)) {
                        matriz = f1;
                    }
                }

                File pastas[] = matriz.listFiles();
                for (File f1 : pastas) {
                    if (f1.getName().contains("FINANCEIRO")) {
                        File financeiro = f1;
                        for (File f2 : financeiro.listFiles()) {
                            if (f2.getName().contains("2017")) {
                                File ano = f2;
                                for (File f3 : ano.listFiles()) {
                                    File m = null;
                                    if(mes.length() == 1){
                                        mes = "0"+mes;
                                    }
                                    if (f3.getName().contains(mes)) {
                                        m = f3;
                                        for (File f4 : m.listFiles()) {
                                            cont++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        if (cont > 0) {
            return true;
        } else {
            return false;
        }
    }
}
