
package http_server_basico;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static utilidades.Utilidades.getFechaHoraActualFormateada;

/**
 *@author benmu
 * Servidor usando las clases HttpServer y HttpHandler 
 */
public class Servidor_http_basico {
    public static void main(String[] args) throws Exception, InterruptedException, IOException {
        
        
        //VARIABLES
        final int PUERTO_DEFAULT = 80;//El puerto por defecto de http es el 80, pero se ha establecido el 8000 siguiendo el ejercicio.
        String numeroString=null;
        int numeroPuerto=0;
        boolean binario=true;//Variable para salir del bucle
        Scanner teclado = new Scanner(System.in);//Scanner
        InetSocketAddress socketAddress= new InetSocketAddress(0);//InetSocket
        HttpServer servidorHttp=null;//Objeto Servidor http 
        ServerSocket ssOne;
        System.out.println("SERVIDOR HTTP");   
       
        System.out.println("-------------");
        //SELECCIÓN DE PUERTO
        //Para que el servidor sea más funcional, pueda utilizarse varias veces a la vez y lleve acabo su cometido, 
        //he establecido una opción para introducir un puerto de como máximo 5 dígitos, en el caso de estar ocupado,
        //se utilizará el puerto por defecto para servidores http 80 (PUERTO_DEFAULT), y si este también estuviese ocupado,
        //el puerto se establecería de forma aleatoria (un puerto libre aleatorio) con el uso de un  un bind entre
        //un ServerSocket y un InetSocketAddress.
        //Ej. 
                //ssOne = new ServerSocket();
                
                //socketAddress=new InetSocketAddress(0);
                
                //ssOne.bind(socketAddress);
                
                //servidorHttp = HttpServer.create(socketAddress, 0);
       
        do{
            
            try{
              
                do{
                   
                    binario=false;
                   
                    System.out.println("Teclea un puerto de escucha para el servidor, de no más de 5 dígitos:");
                    
                    numeroPuerto=teclado.nextInt();
                    
                    numeroString = Integer.toString(numeroPuerto);
                
                }while(numeroString.length()>5);
                
                socketAddress=new InetSocketAddress(numeroPuerto);
                
                servidorHttp = HttpServer.create(socketAddress, 0);
                
                System.out.println("["+getFechaHoraActualFormateada()+"]"+" Servidor iniciado en el puerto: "+servidorHttp.getAddress().getPort());
            
            }catch(InputMismatchException e){
                
                System.err.printf ("Error de lectura: no es un número entero válido."+"\n");
                
                teclado.nextLine(); 
                
                binario=true;
            
            } catch (SocketException ex) {
                
                try{
                    
                    socketAddress=new InetSocketAddress(PUERTO_DEFAULT);
                    
                    servidorHttp = HttpServer.create(socketAddress, 0);
                    
                    System.out.printf("Error de socket: %s\n", ex.getMessage());
                    
                    System.out.println("["+getFechaHoraActualFormateada()+"]"+" Servidor iniciado en el puerto por defecto para servidores Http, puerto "+servidorHttp.getAddress().getPort());
                
                }catch (SocketException ed) {
                    
                    System.out.printf("Error de socket: %s\n", ed.getMessage());
                    
                    ssOne = new ServerSocket();
                    
                    socketAddress=new InetSocketAddress(0);
                    
                    ssOne.bind(socketAddress);
                    
                    servidorHttp = HttpServer.create(socketAddress, 0);              
                    
                    System.out.println("["+getFechaHoraActualFormateada()+"]"+" Servidor Port 80 ocupado, se iniciará servidor en puerto aleatorio: "+servidorHttp.getAddress().getPort());
 
                }
            
            } 
           
            //INICIO DEL SERVIDOR
            servidorHttp.createContext("/saludar", new HttpHandlerSaludar());
            
            servidorHttp.start();
        
        }while(binario);

    }
 
}
