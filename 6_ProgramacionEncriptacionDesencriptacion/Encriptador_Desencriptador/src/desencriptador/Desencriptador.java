/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package desencriptador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author benmu
 */
public class Desencriptador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //String donde se establece el nombre del archivo para analizar.
        String nombreArchivo=null;
        
        String clave=null;
        
        String archivo=null;
        
        String direccion=null;
        
        Scanner sc = new Scanner(System.in);
        
        String sCarpAct = System.getProperty("user.dir");

        File carpeta = new File(sCarpAct);

        File[] archivosCarpeta = carpeta.listFiles();
        
         FileInputStream fe = null; //fichero de entrada
         
         FileOutputStream fs = null; //fichero de salida
         
         int bytesLeidos;
         
         boolean encontrado=false;
        
        
         //OBTENCIÓN INTRODUCCIÓN DEL ARCHIVO A DESENCRIPTAR

             
             do{
                 System.out.println("Escribe 'encriptado.txt' para desencriptar el archivo encriptado");

                 nombreArchivo = sc.nextLine();
             
             } while (!nombreArchivo.equals("encriptado.txt"));
             
             
             for (int i=0; i< archivosCarpeta.length; i++){
                String partes[]=archivosCarpeta[i].toString().split("\\\\"); 
              
                archivo=partes[partes.length-1];
                
                if(archivo.equals(nombreArchivo))
                {
                    direccion=archivosCarpeta[i].toString();
                    System.out.println("Archivo encontrado");
                    encontrado=true;  
                } 
                
             }
             
             if(encontrado==false) {
                
                 System.out.println("Archivo 'encriptado.txt' no encontrado (No ha sido creado o se ha borrado)");
             
                 System.exit(0);
             
             }

         //OBTENCIÓN INTRODUCCIÓN DE LA CLAVE DE DESENCRIPTACIÓN
         do{
             
             System.out.println("Introduce la contraseña de desencriptación del archivo (Misma utilizada para encriptar)");
             
             clave = sc.nextLine();
         
         } while (clave.equals(""));
         
         //PROCESO DE DESENCRIPTACIÓN
         try {
             
             byte[] keyByte = clave.getBytes("UTF-8");
             
             MessageDigest md=MessageDigest.getInstance("SHA-1");
             
             keyByte=md.digest(keyByte);
             
             keyByte=Arrays.copyOf(keyByte, 16);
             
             SecretKeySpec keySpec = new SecretKeySpec(keyByte ,"AES");
             
             Cipher cifrador = Cipher.getInstance("Rijndael/ECB/PKCS5Padding");
            
             cifrador.init(Cipher.DECRYPT_MODE, keySpec);
             
             System.out.println("Descifrando con AES el fichero: " + archivo
            + ", y se ha dejado el resultado en " + System.getProperty("user.dir") + "\\\\desencriptado.txt");

            byte[] buffer = new byte[1000]; 
            
            byte[] bufferClaro;
    
            fe = new FileInputStream(direccion); 
    
            fs = new FileOutputStream(System.getProperty("user.dir") + "\\\\desencriptado.txt"); 
    
            bytesLeidos = fe.read(buffer, 0, 1000);
    
            while (bytesLeidos != -1) {
                
                bufferClaro = cifrador.update(buffer, 0, bytesLeidos);
      
                fs.write(bufferClaro); 
      
                bytesLeidos = fe.read(buffer, 0, 1000);
            
            }
            
            bufferClaro = cifrador.doFinal(); 
            
            fs.write(bufferClaro); 

         
         }catch (NoSuchAlgorithmException a)  {System.out.println(a.getMessage());
         
         } catch (NoSuchPaddingException b) {System.out.println(b.getMessage());
         
         } catch (InvalidKeyException c) {System.out.println(c.getMessage());
         
         } catch (IOException d) {System.out.println(d.getMessage());
         
         } catch (IllegalBlockSizeException e) {System.out.println(e.getMessage());
         
         } catch (BadPaddingException f) {System.out.println("Contraseña introducida incorrecta. Archivo no desencriptado");
         
         } catch (Exception h) {System.out.println(h.getMessage());
         
         
         }finally{
             
            try {
                
                fe.close();
                
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(Desencriptador.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        
    }

}
