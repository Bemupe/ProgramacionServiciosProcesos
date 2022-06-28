/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocket;

import java.io.*;
import java.net.*;
/**
 *
 * @author Usuario
 */
public class Cliente extends Thread{
    
    private String cadenaOperacion;
    private String hostServidor;
    private int puertoServidor;
    
    public Cliente(String cadenaOperacion, String hostServidor, int puertoServidor){
        this.cadenaOperacion=cadenaOperacion;
        this.hostServidor=hostServidor;
        this.puertoServidor=puertoServidor;
    }
    
    @Override
    public void run(){
        
        System.out.println("PROGRAMA CLIENTE INICIADO ...");
      
        try
        {
            //CREO EL SOCKET CON EL PUERTO Y EL HOST DEL SERVIDOR
            Socket cliente = new Socket( hostServidor, puertoServidor );
            
            //CREO FLUJO DE SALIDA AL SERVIDOR
            DataOutputStream salida=new DataOutputStream (cliente.getOutputStream());
            
            //ENVÍO AL SERVIDOR UNA CADENA CON LA OPERACIÓN QUE DEBE REALIZAR
            salida.writeUTF(cadenaOperacion);
            
            //CREO FLUJO DE ENTRADA AL SERVIDOR
            DataInputStream entrada = new DataInputStream (cliente.getInputStream());
            
            //RECIBO EL MENSAJE CON LA CADENA DE RESPUESTA DEL SERVIDOR
            String mensaje=entrada.readUTF();
            System.out.println("Recibiendo del servidor "+mensaje);
      
            //CERRAR STREAMS Y SOCKETS
            cliente.close();
            salida.close();
            entrada.close();

        } catch( IOException e ) {
            System.out.println( e.getMessage() );
        }

 
        
        
       
        
        
       
        
        
        
                
        
         
    }
}
