/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1;

import ejercicio2.ValidadorUsuarios.Evento;
import static ejercicio2.ValidadorUsuarios.registrarEvento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.*;

/**
 *
 * @author benmu
 */
public class ValidadorUsuarios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        registrarEvento (Evento.APP_OPEN, ejercicio1.ValidadorUsuarios.class.getName());

        //VARIABLES
        int contador=0;
        
        //String donde introducimos la línea para analizar su validez con el Pattern y Matcher.
        String cadena;        
        
        //Línea del archivo etc-passwd.txt
        FileReader f; 
                
        //BufferedReader para analizar cada línea del archivo etc-passwd.txt
        BufferedReader b = null;

        //Scanner para entrada 
        Scanner sc = new Scanner(System.in);
        
        //Patron y matcher para usuarios 
        Pattern patUsuario = Pattern.compile("^[a-z0-9]{5,10}+:+x+:+[0-1]?[0-9]{1,3}+:+[0-1]*[0-9]{1,3}+:+[a-zA-Z0-9 ]*+:+/home/+[a-z0-9]{5,10}+:/bin/bash");             
         
        Matcher matUsuario; 
        
        //Patron y matcher para usuarios root
        Pattern patRoot = Pattern.compile("root:x:0:0:+[a-zA-Z0-9]*+:/root:/bin/bash");  
        
        Matcher matRoot;
        
        //String donde se establece el nombre del archivo para analizar.
        String nombreArchivo=null;
        
        String[]campo={"usuario", "password", "id_usuario", "id_grupo", "descripción_usuario", "directorio_inicio", "cascarón"};
        
        //Treemap donde se almacena los usuarios con cuentas correcta, y que al imprimir por consola aparecerá en orden, tras pasar la comprobación de patrones.        
        TreeMap<String, HashMap<String, String>> usuarios = new TreeMap<String, HashMap<String, String>>();
                
                
        //ENTRADA DE ARCHIVO PARA ANALIZAR
        //Listamos el contenido de la carpeta donde se encuentra el programa
        String sCarpAct = System.getProperty("user.dir");

        File carpeta = new File(sCarpAct);

        File[] archivosCarpeta = carpeta.listFiles();
        
        if (args.length > 1) { //si hay más de 1 parámetro

            System.out.println("Demasiados archivos, escribe sólo el nombre de uno");

        } else if (args.length == 0) { //si no hay parámetros      

            //Solicitud de archivo de texto
            System.out.println("No has introducido el nombre de ningún archivo. Teclea el archivo etc-passwd.txt "+"\n"
                    + "con información de las cuentas de usuario del SO Linux y asegúrate que se encuentra en el directorio donde se ejecuta el programa");

            //Variable que guarda el archivo introducido por el usuario
            nombreArchivo = sc.nextLine();
            
            while (!nombreArchivo.equals("etc-passwd.txt")){
            
                System.out.println("No has tecleado el archivo correcto "+nombreArchivo+" (Correcto etc-passwd.txt). Inténtalo de nuevo");
                
                nombreArchivo = sc.nextLine();
            
            }
        
        } else {
            
            nombreArchivo = args[0];
        
        }
        
        //ALMACENAMOS EN EL TREEMAP LOS DATOS DE LOS USUARIOS
        try {
            
            //Comprobamos que el archivo se encuentra en el directorio del programa y lo preparamos para analizar cada línea del archivo, para así comprobar las cuentas correctas.
            for (int i=0; i< archivosCarpeta.length; i++) {
                
                if (archivosCarpeta[i].toString().contains(nombreArchivo)) {
                    f = new FileReader(archivosCarpeta[i]); 
                    
                    b = new BufferedReader(f);
                    
                    registrarEvento (Evento.OPEN_FILE_DONE, nombreArchivo);
                }
            
            }
            
            //Si de entre los archivos de la carpeta donde está ejecutándose el programa encontramos "etc-passwd.txt", leemos cada línea, y a cada línea, le aplicamos el patrón
            // (patrón para root o patron para usuario), si el patrón es correcto, separamos los elementos de cada línea y lo almacenamos en el TreeMap.
            while((cadena = b.readLine())!=null) {
                
                matRoot = patRoot.matcher(cadena);
                
                matUsuario = patUsuario.matcher(cadena);
                
                //Para la cuenta de usuarios
                if (matUsuario.find()){
                    
                    String[] elemento=cadena.split(":");
                    
                    usuarios.put(elemento[0], new HashMap<>(){{for (int j=1;j<7;j++) {put(campo[j],elemento[j]);}}});
                    
                    registrarEvento (Evento.VALID_ACCOUNT, elemento[0]);
                
                } else { 
                    if (!cadena.contains("/root")){
                        
                        String[] elemento=cadena.split(":");
                        
                        registrarEvento (Evento.INVALID_ACCOUNT, elemento[0]);
                    }
                
                }
                
                //Para las cuentas root
                if (matRoot.find()&&cadena.contains("/root")){
                    
                    String[] elemento=cadena.split(":");
                    
                    usuarios.put(elemento[0], new HashMap<>(){{for (int j=1;j<7;j++) {put(campo[j],elemento[j]);}}});
                    
                    registrarEvento (Evento.VALID_ACCOUNT, elemento[0]);
                
                }
            
            }
            
            b.close();//Cerramos el Buffereader.
        
        }catch(IOException ex){
            
            registrarEvento (Evento.OPEN_FILE_FAIL, ex.getMessage());
            
            System.out.println("Error de E/S"+ex.getMessage());
        
        }
        
        
        //Si el TreeMap "usuarios" está vacío señalamos que el archivo no se encuentra en el directorio o que no es un archivo de usuarios de linux.Paramos el programa.
        if (usuarios.isEmpty()){
            
            System.out.println("El archivo de usuarios del SO Linux etc-passwd.txt, no se encuentra en el directorio "
                    + "donde está el programa, o no es un archivo etc/passwd de linux correcto. Asegúrate y vuelve a intentarlo");
        
            System.exit(0);
        
        }
        
        //Eperamos unos segundos a que termine de analizar todas las cuentas del archivo
        try {
            
            Thread.sleep(2*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ValidadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //SACAMOS LOS RESULTADOS
        System.out.println("LISTA DE USUARIOS CORRECTOS");
        System.out.println("---------------------------");
        System.out.println("Total de usuarios correctos: "+usuarios.size());

        for (String obj : usuarios.keySet()) {contador++;
        
        System.out.println(contador+": usuario="+obj
         
                +" id_usuario="+usuarios.get(obj).get("id_usuario")
         
                +" id grupo="+usuarios.get(obj).get("id_grupo")
         
                +" directorio de inicio="+usuarios.get(obj).get("directorio_inicio"));
        
        }
        
        //Eperamos unos segundos a que aparezca por pantalla los resultados de las cuentas correctas
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ValidadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Registramos el fin de la aplicación
        registrarEvento (Evento.APP_QUIT, ejercicio1.ValidadorUsuarios.class.getName());
    
    }

}

