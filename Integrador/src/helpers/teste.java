/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

  import java.security.InvalidKeyException;
  import java.security.NoSuchAlgorithmException;
   
  import javax.crypto.BadPaddingException;
  import javax.crypto.Cipher;
  import javax.crypto.IllegalBlockSizeException;
  import javax.crypto.KeyGenerator;
  import javax.crypto.NoSuchPaddingException;
  import javax.crypto.SecretKey;
   
  public class teste
  {
   
   
         public static void main(String[] argv) {
             byte[] e = Suporte.encriptar("123sdhdfghdfghdfghdfghdfgh");
             System.out.println("Encriptado: " + e);
             System.out.println(Suporte.decriptar(e));
   
         }
         
  }