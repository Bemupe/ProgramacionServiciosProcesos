/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorsocket;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author Usuario
 */
public class MainServidor {

    public static final int LIMITE_CONEXIONES = 3;
    //public volatile static Servidor serv;
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {

        int puertoServidor=6000;
        if(args.length!=1){
            System.out.println("Error de parámetros,tomaremos los parámetros por defecto");
        }else{
            try{
                puertoServidor = Integer.parseInt(args[0]);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //Declaro un array "hilosServidor" con 3 hilos
        Servidor hilosServidor[] = new Servidor[LIMITE_CONEXIONES];

      
        //Declaro el socket del servidor
        ServerSocket servidor;
        
        int numCliente = 0;

        try {
            // Punto de conexión del servidor (socket en el puerto indicado en la máquina donde se ejecute el proceso)
            //Instancia el socket del servidor
            //...
            servidor=new ServerSocket(puertoServidor);
            Socket cliente; 
            
            // Mensaje de arranque de la aplicación
            System.out.println("SERVIDOR CONCURRENTE");
            System.out.println("--------------------");
            System.out.println("Servidor de Benjamin Mulero Pedrosa");
            System.out.println("Servidor iniciado.");
            //Debo especificar en el mensaje posterior el número de puerto
            System.out.printf("Escuchando por el puerto "+puertoServidor+"\n");
            System.out.println("Esperando conexión con cliente.");

            
            while (numCliente < hilosServidor.length) {
                
         
                // Quedamos a la espera ("escuchando") de que se realice una conexión con el socket de servidor.
                // En el momento en que eso suceda, se aceptará. Mientras tanto, la ejecución queda aquíe 
                // bloqueada en espera a que se reciba esa petición por parte de un cliente.
                cliente=servidor.accept();
                           
                System.out.println("Conexión establecida con cliente.");
 
                // Creamos un nuevo hilo de ejecución para servir a este nuevo cliente conectado y lo almaceno en el array de hilos      
                       
                hilosServidor[numCliente]= new Servidor(cliente, numCliente);
  
                // Lanzamos la ejecución de ese nuevo hilo
                 new Servidor(cliente, numCliente).start();
                ++numCliente;   
            } // y seguimos "escuchando" a otras posibles peticiones de cliente
            servidor.accept();
            
            // Finaliza la interacción con posibles clientes. Cerramos el socket de servidor
            servidor.close();

        } catch (SocketException ex) {
            System.out.printf("Error de socket: %s\n", ex.getMessage());
        } catch (IOException ex) {
            System.out.printf("Error de E/S: %s\n", ex.getMessage());
        }

        // Esperamos a que todos los hilos del servidor finalicen su ejecución
        for (Thread hilos : hilosServidor)
            hilos.join();
        
        
        System.out.println("Fin de ejecución del servidor.");
    }

}
