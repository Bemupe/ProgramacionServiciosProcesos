/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package http_server_concurrente;

import com.sun.net.httpserver.HttpServer;
import http_server_basico.HttpHandlerSaludar;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import static utilidades.Utilidades.getFechaHoraActualFormateada;

/**
 *
 * @author benmu
 */
public class ServidorHttp_concurrente {
    
    public static void main(String[] args) throws Exception, InterruptedException, IOException {
        //VARIABLES
        final int PUERTO_DEFAULT = 80;//El puerto por defecto de http es el 80, pero se ha establecido el 8000 siguiendo el ejercicio.
        String numeroString=null;
        int numeroPuerto=0;
        boolean binario=true;//Variable para salir del bucle
        Scanner teclado = new Scanner(System.in);//Scanner
        InetSocketAddress socketAddress= new InetSocketAddress(0);//InetSocket
        HttpServer servidorHttp = null;//Objeto Servidor http 
        ServerSocket ssOne;
         System.out.println("SERVIDOR HTTP CONCURRENTE");
        
        System.out.println("--------------------------");
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
           
            //INICIO DEL SERVIDOR, CONTEXTOS Y GETIONS DE HILOS
            servidorHttp.createContext("/saludar", new HttpHandlerSaludar());
            servidorHttp.createContext("/primo", new HttpHandlerEsPrimo());
            servidorHttp.setExecutor(Executors.newCachedThreadPool());
            servidorHttp.start();
        
        }while(binario);

    }
 
}
        


    

