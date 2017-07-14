/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
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

    public static ArrayList<File> verificarArquivo(int cod, String mes, int year) {
        int cont = 0;
        ArrayList<File> lp = new ArrayList<>();
        File f = new File("M:");
        File fs[] = f.listFiles();
        String codigo = (Suporte.formatCodigo(cod));
        for (File file : fs) {

            File matriz = null;
            if (file.getName().contains(codigo) && file.isDirectory()) {

                File grupo[] = file.listFiles();
                for (File f1 : grupo) {
                    if (f1.getName().contains(codigo)) {
                        matriz = f1;
                    }
                }

                File pastas[] = matriz.listFiles();
                for (File f1 : pastas) {
                    if (f1.getName().contains("FINANCEIRO") && f1.isDirectory()) {
                        File financeiro = f1;
                        for (File f2 : financeiro.listFiles()) {
                            if (f2.getName().contains(String.valueOf(year)) && f2.isDirectory()) {
                                File ano = f2;
                                for (File f3 : ano.listFiles()) {
                                    File m = null;
                                    if (mes.length() == 1) {
                                        mes = "0" + mes;
                                    }

                                    if (f3.getName().contains(mes) && f3.isDirectory()) {
                                        m = f3;
                                        for (File f4 : m.listFiles()) {

                                            lp.add(f4);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return lp;
    }

    public static ArrayList<File> listarExtatos(int cod, int mes, int ano) {
        ArrayList<File> arquivos = new ArrayList<>();
        File f = new File("M:");
        String codigo = Suporte.formatCodigo(cod);
        File fs[] = f.listFiles();
        for (File f1 : fs) {
            if (f1.getName().contains(codigo) && f1.isDirectory()) {
                for (File f2 : f1.listFiles()) {
                    if (f2.getName().contains(codigo) && f2.isDirectory()) {
                        for (File f3 : f2.listFiles()) {
                            if (f3.getName().contains("ARQUIVOS_DIGITAIS") && f3.isDirectory()) {
                                for (File f4 : f3.listFiles()) {
                                    if (f4.getName().contains("CONTABIL") && f4.isDirectory()) {
                                        for (File f5 : f4.listFiles()) {
                                            if (f5.getName().contains("EXTRATOS") && f5.isDirectory()) {
                                                for (File f6 : f5.listFiles()) {

                                                    if (f6.getName().contains("BANCO_DO_BRASIL")
                                                            || f6.getName().contains("CAIXA")
                                                            || f6.getName().contains("ITAU")
                                                            || f6.getName().contains("TRIBANCO")
                                                            || f6.getName().contains("BRADESCO")
                                                            || f6.getName().contains("BANCO_DO_NORDESTE")
                                                            || f6.getName().contains("SANTANDER") && f6.isDirectory()) {

                                                        try {
                                                            for (File f7 : f6.listFiles()) {

                                                                if (f7.getName().equals(String.valueOf(ano)) && f7.isDirectory()) {
                                                                    for (File f8 : f7.listFiles()) {

                                                                        if (f8.getName().contains(String.valueOf(mes)) && f8.isDirectory()) {

                                                                            for (File f9 : f8.listFiles()) {

                                                                                arquivos.add(f9);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } catch (NullPointerException ex) {
                                                            return null;
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
        return arquivos;
    }

    public static String ajustarCaminho(String str) {
        String st = str.replace("\\", ";");
        String txt[] = st.split(";");
        String tf = "";
        for (int i = 0; i < txt.length; i++) {
            tf = (txt[7] + " - " + txt[8] + " - " + txt[9] + " - " + txt[10]);
        }

        return tf;
    }

    public static byte[] encriptar(String txt) {

        try {

            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey chaveDES = keygenerator.generateKey();

            Cipher cifraDES;

            // Cria a cifra 
            cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Inicializa a cifra para o processo de encriptação
            cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);

            // Texto puro
            byte[] textoPuro = txt.getBytes();

            // Texto encriptado
            byte[] textoEncriptado = cifraDES.doFinal(textoPuro);

            return textoEncriptado;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decriptar(byte[] txt) {

        try {

            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey chaveDES = keygenerator.generateKey();

            Cipher cifraDES;

            // Cria a cifra 
            cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Inicializa a cifra para o processo de encriptação
            cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);

            // Texto puro
            byte[] textoPuro = "123".getBytes();

            // Texto encriptado
            // Inicializa a cifra também para o processo de decriptação
            cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);

            // Decriptografa o texto
            byte[] textoDecriptografado = cifraDES.doFinal(txt);
            return textoDecriptografado;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
