package clientesocket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class MainCliente extends Thread {

    // Nombre del archivo de texto con las operaciones a realizar
    private static final String NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT = "C:\\Mulero_Pedrosa_Benjamin_PSP03_Tarea\\operaciones.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostServidor="127.0.0.1";//localhost
        int puertoServidor=6000;
        if(args.length!=2){
            System.out.println("Error de parámetros,tomaremos los parámetros por defecto");
        }else{
            hostServidor = args[0];
            try{
                puertoServidor = Integer.parseInt(args[1]);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //Creamos la estructura que almacena las operaciones a realizar
        ArrayList<String> operaciones = new ArrayList<>();

        // Creamos la estructura vacía para ir llenándola línea a línea (hilo a hilo)
        List<Thread> listaHilos = new LinkedList<>();

        System.out.println("HILOS CLIENTES");
        System.out.println("--------------");
        System.out.println("Cliente de Benjamin Mulero Pedrosa");
        System.out.println("Leyendo configuración de hilos (operaciones) del archivo de texto...");

        boolean lecturaCorrecta = false;

        try (FileReader fr = new FileReader(NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT);
                BufferedReader entrada = new BufferedReader(fr);) {

            // Patrón que contiene la estructura de una línea de texto que representa a un producto
            Pattern patronHilo = Pattern.compile("([^:]+);([^:]+);([^:]+)");

            String linea = entrada.readLine(); //se lee la primera línea del archivo
            while (linea != null) {  //mientras no se llegue al final del archivo

                Matcher m = patronHilo.matcher(linea);
                if (m.matches()) { // Si la línea cumple las condiciones de estructura
                    
                    // Se genera un hilo clinete por cada operación
                    operaciones.add(linea);
                    System.out.println(linea);
                    //Añado el hilo a la lista de hilo
                    //...
                    listaHilos.add(new Cliente(linea, hostServidor, puertoServidor));
                    
                   
                }
                linea = entrada.readLine(); // Se lee la siguiente línea del archivo
            }
            lecturaCorrecta = true;

        } catch (FileNotFoundException e) {
            System.out.println("Error: archivo " + NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT + "·no encontrado.");
        } catch (IOException e) {
            System.out.println("Error: fallo en el acceso al archivo: " + e.getMessage());
        }

        // Lanzamos los hilos creados a partir de la configuración del archivo
        if (lecturaCorrecta) {
                
            System.out.println("\nCargada configuración de clientes.");
            System.out.printf("Cantidad de clientes: %d\n", listaHilos.size());
            System.out.println();
            System.out.println("Ejecución de clientes concurrentes");
            System.out.println("-------------------------------");
            
            //RECORRO LOS HILOS CLIENTE Y LOS LANZO PARA SU EJECUCIÓN
            //...
             for (int i=0;i<listaHilos.size();++i) {
                    listaHilos.get(i).start();
                    
                    }
            //ME ASEGURO QUE TODOS LOS HILOS CLIENTE HAN TERMINADO ANTES DE FINALIZAR LA EJECUCIÓN DEL MAIN
            //...
            for (Thread hilos : listaHilos)
                try 
                {
                   hilos.join();
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
            System.out.println("\nTodos los clientes han finalizado su ejecución.");
            System.out.println("Fin del programa principal.");
        } 
    }
}
