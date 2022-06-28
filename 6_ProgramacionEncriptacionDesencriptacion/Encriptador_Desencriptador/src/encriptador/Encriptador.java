/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package encriptador;

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
public class Encriptador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
        
        String nombreArchivo=null;//String donde se establece el nombre del archivo para analizar.
        
        String clave=null;//Variable donde se introduce la contraseña
        
        String archivo=null;//Nombre del archivo obtenido del split
        
        String direccion=null;//Dirección donde se encuentran los archivos, obtenido de System.getProperty("user.dir")
        
        Scanner sc = new Scanner(System.in);//Scaner para introducir la clave y el nombre del archivo a encriptar
        
        String sCarpAct =System.getProperty("user.dir") ;

        File carpeta = new File(sCarpAct);

        File[] archivosCarpeta = carpeta.listFiles();
        
        FileInputStream fe = null; //fichero de entrada
         
        FileOutputStream fs = null; //fichero de salida
         
        int bytesLeidos;
         
        boolean encontrado=false;//Boolean para salir del do-while de obtención del archivo a encriptar.

         
        //OBTENCIÓN INTRODUCCIÓN DEL ARCHIVO A ENCRIPTAR
        
        do{
            
            do{
                 
                System.out.println("Introduce el archivo a encriptar de extensión txt y que se encuentre en el directorio del proyecto");

                nombreArchivo = sc.nextLine();
            
            } while (!nombreArchivo.endsWith(".txt"));
            
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
           
        }while (encontrado==false);
     
         
         //OBTENCIÓN, INTRODUCCIÓN DE LA CLAVE DE ENCRIPTACIÓN
         do{
             
             System.out.println("Introduce la contraseña de encriptación del archivo");
             
             clave = sc.nextLine();
         
         } while (clave.equals(""));
         
        
        //PROCESO DE ENCRIPTACIÓN
         try {
             
             byte[] keyByte = clave.getBytes("UTF-8");//Introducimos la clave string a byte, estableciendolo en UTF-8 en caso de caracteres especiales.
             
             MessageDigest md=MessageDigest.getInstance("SHA-1");//Instanciamos MessageDigest para resumir el mensaje usando el algoritmo SHA-1 
             
             keyByte=md.digest(keyByte);//Resumimos el contenido de la contraseña utilizando MessageDigest
             
             keyByte=Arrays.copyOf(keyByte, 16);//Una vez resumido usamos Array.copyOf()". De esta forma, copia la matriz especificada(keyByte), rellenando con falso (si es necesario) para que la copia tenga la longitud especificada (16bytes/128bits) para crear la clave para encriptar.
             
             SecretKeySpec keySpec = new SecretKeySpec(keyByte ,"AES");// Inicializa un objeto clave con la contraseña algoritmo AES obteniendo la clave de 128 bits
             
             Cipher cifrador = Cipher.getInstance("Rijndael/ECB/PKCS5Padding");//Se Crea el objeto Cipher para cifrar, utilizando el algoritmo Rijndael(AES)
            
             cifrador.init(Cipher.ENCRYPT_MODE, keySpec);//Se inicializa el cifrador en modo CIFRADO o ENCRIPTACIÓN usando la clave
             
             System.out.println("Se ha cifrado con AES el fichero: " + archivo
            + ", y se ha dejado el resultado en " + System.getProperty("user.dir") + "\\\\encriptado.txt");//Sacamos por pantalla lo que se esta realizando.

            byte[] buffer = new byte[1000]; //Declaramos un array de bytes “buffer” de máximo 1000 byte para utilizarlo para la encriptación.
          
            byte[] bufferCifrado;
    
            fe = new FileInputStream(direccion); ////Creamos un objeto de fichero de entrada con la dirección donde se encuentra el archivo “archivo_A_encriptar.txt”
 
            fs = new FileOutputStream(System.getProperty("user.dir") + "\\\\encriptado.txt"); //Creamos fichero de salida según donde se encuentra el directorio del proyecto y en el archivo “encriptado.txt”, según marca el ejercicio.
  
            bytesLeidos = fe.read(buffer, 0, 1000);//Se lee el fichero y pasa los fragmentos leidos al cifrador
    
            while (bytesLeidos != -1) {//Mientras no se llegue al final del fichero
                
                bufferCifrado = cifrador.update(buffer, 0, bytesLeidos);//Pasa texto claro al cifrador y lo cifra, asignándolo a bufferCifrado
      
                fs.write(bufferCifrado); //Graba el texto cifrado en fichero
      
                bytesLeidos = fe.read(buffer, 0, 1000);
            
            }
            
            bufferCifrado = cifrador.doFinal(); //Completa el cifrado
            
            fs.write(bufferCifrado); ////Graba el final del texto cifrado
         
         }catch (NoSuchAlgorithmException a)  {System.out.println(a.getMessage());
         
         } catch (NoSuchPaddingException b) {System.out.println(b.getMessage());
         
         } catch (InvalidKeyException c) {System.out.println(c.getMessage());
         
         } catch (IOException d) {System.out.println(d.getMessage());
         
         } catch (IllegalBlockSizeException e) {System.out.println(e.getMessage());
         
         } catch (BadPaddingException f) {System.out.println(f.getMessage());
         
         } catch (Exception h) {System.out.println(h.getMessage());
         
        
         }finally{
             
            try {
                
                //Cierra ficheros
                fe.close();
                
                fs.close();
            } catch (IOException ex) {
                Logger.getLogger(Encriptador.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        
}
}
