/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorsocket;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author Usuario
 */
public class Servidor extends Thread {

    private Socket socketCliente;

    // Flujo de salida a través del cual enviaremos información al proceso cliente 
    // conectado a través del socket
    private DataOutputStream flujoEscrituraCliente;

    // Flujo de entrada a través del cual recibiremos información desde el proceso cliente
    private DataInputStream flujoEntradaCliente;

    private int numCliente;

    public Servidor(Socket socketCliente, int numCliente) throws IOException {
        this.socketCliente = socketCliente;
        // Flujo de salida a través del cual enviaremos información al proceso cliente 
        // conectado a través del socket
        this.flujoEscrituraCliente = new DataOutputStream(socketCliente.getOutputStream());

        // Flujo de entrada a través del cual recibiremos información desde el proceso cliente
        this.flujoEntradaCliente = new DataInputStream(socketCliente.getInputStream());

        this.numCliente = numCliente;
        
        
    }

    @Override
    public void run() {
        // Creamos las variables para las operaciones de la calculadora    
        double n1 = 0, n2 = 0;
        char op = ' ';
        String resultado, peticionCliente = null;
        Calculadora miCalculadora = null;
        StringTokenizer token = null;
        int estado = 0;

        System.out.printf("Iniciado hilo servidor %d.\n", this.numCliente);

        try {

            // En espera de la recepción de peticiones por parte del cliente    
            peticionCliente = flujoEntradaCliente.readUTF();
           
            if (!peticionCliente.isEmpty()) {
                System.out.println("Recicienbo del CLIENTE: \n\t" + peticionCliente);
                if (peticionCliente.equals("FIN") == false) {
                    token = new StringTokenizer(peticionCliente, ";");
                    try {
                        n1 = Double.parseDouble(token.nextToken());
                        n2 = Double.parseDouble(token.nextToken());
                        op = token.nextToken().charAt(0);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    miCalculadora = new Calculadora(n1, n2, op);      
                } 
            } 
            
            // Devuelvo al cliente el resultado de la operación
            resultado = miCalculadora.getResultado();
            flujoEscrituraCliente.writeUTF(resultado);
            
            // Cerramos la comunicación con el cliente
            socketCliente.close();
             System.out.println("Enviada respuesta");

        } catch (SocketException ex) {
            System.out.printf("Error de socket: %s\n", ex.getMessage());
        } catch (IOException ex) {
            System.out.printf("Error de E/S: %s\n", ex.getMessage());
        }

         System.out.printf(
                "Hilo servidor %d: Fin de la conexión con el cliente.\n", this.numCliente);

    }
}
