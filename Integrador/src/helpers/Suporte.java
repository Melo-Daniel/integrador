/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/**
 *
 * @author daniel.freitas
 */
public class Suporte {

    private static Preferences pref = Preferences.userRoot();
    private static String path = pref.get("DEFAULT_PATH", "");

    public static String abrirArquivo() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            // handle exception
        }
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(path));
        int resp = fc.showOpenDialog(fc);
        if (resp == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            fc.setCurrentDirectory(f);
            pref.put("DEFAULT_PATH", f.getAbsolutePath());
            return fc.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }
    }

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
                                    if (mes.length() == 1) {
                                        mes = "0" + mes;
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

    public static File[] listarExtatos(int cod, int mes, int ano) {
        File f = new File("M:");
        String codigo = Suporte.formatCodigo(cod);
        File fs[] = f.listFiles();
        for (File f1 : fs) {
            if (f1.getName().contains(codigo)) {
                for (File f2 : f1.listFiles()) {
                    if (f2.getName().contains(codigo)) {
                        for (File f3 : f2.listFiles()) {
                            if (f3.getName().contains("ARQUIVOS_DIGITAIS")) {
                                for (File f4 : f3.listFiles()) {
                                    if (f4.getName().contains("CONTABIL")) {
                                        for (File f5 : f4.listFiles()) {
                                            if (f5.getName().contains("EXTRATOS")) {
                                                for (File f6 : f5.listFiles()) {
                                                    if (f6.getName().contains("BANCO_DO_BRASIL")
                                                            || f6.getName().contains("CAIXA")
                                                            || f6.getName().contains("ITAU")
                                                            || f6.getName().contains("TRIBANCO")
                                                            || f6.getName().contains("BRADESCO")
                                                            || f6.getName().contains("BANCO_DO_NORDESTE")
                                                            || f6.getName().contains("SANTANDER")) {

                                                        for (File f7 : f6.listFiles()) {
                                                            if (f7.getName().equals(String.valueOf(ano))) {
                                                                for (File f8 : f7.listFiles()) {
                                                                    if (f8.getName().contains(String.valueOf(mes))) {
                                                                        for (File f9 : f8.listFiles()) {
                                                                            System.out.println(f6.getName() + " - " + f7.getName() + " - " + f8.getName() + " - " + f9.getName());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
