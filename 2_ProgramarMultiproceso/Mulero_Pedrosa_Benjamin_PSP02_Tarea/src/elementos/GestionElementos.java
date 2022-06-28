package elementos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author profe
 */
public class GestionElementos {

    // Nombre del archivo de texto con la configuración de hilos
    private static final String ARCHIVO = "C:\\Mulero_Pedrosa_Benjamin_PSP02_Tarea\\hilos.cfg";

    //VARIABLES PUBLICAS PARA ACCEDER A ELLAS
    public volatile static ColaElementos colaElementos = new ColaElementos();// Cola de elementos compartida por productor y consumidores
    public volatile static ProductorElementos productor;
    public volatile static int modo;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Elección del modelo de funcionamiento.
        boolean boole=false;
        Scanner teclado = new Scanner(System.in);
        do
        {
            try
            {
                do
                {
                    boole=false;
                    System.out.printf ("Introduce modo de funcionamiento. 1=Modo silencioso 0=Modo depuración: ");
                    modo=teclado.nextInt();
                }
                while((modo<0)||(modo>1));
            }
            catch(InputMismatchException e)
            {
                System.err.printf ("Error de lectura: no es una opción válida."+"\n");
                teclado.nextLine(); 
                boole=true;
            }
        }
        while(boole);
        //Analizamos los posibles argumentos pasados al proceso
        if (args.length > 0) {
            try {
                modo = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                 //Formato de número incorrecto. Argumento no válido (no se tiene en cuenta)
            }
        }

        System.out.println("\n"+"HILOS PRODUCTORES Y CONSUMIDORES");
        System.out.println("--------------------------------");
        System.out.println("Leyendo configuración de hilos del archivo de texto...");

        // Lectura del archivo de configuración de hilos
        try 
        {
            int contador=0;
            Properties configuracion=new Properties();
            configuracion.load(new FileReader(ARCHIVO));
            
            try
            {
                for ( int a=1 ;a<=(Integer.valueOf(configuracion.getProperty("pro")));a++)
                {
                    String[] prod = configuracion.getProperty("Pro"+a).split("::");
                    System.out.println("Hilo "+prod[0]+": nombre "+prod[1]+", tiempo="+prod[2]+", número de elementos="+prod[3]);
                    contador=contador+1;
                }
            }catch (Exception e)
            {
                System.out.println("Error: Archivo de configuración de hilos (hilos.cfg) mal configurado o no tiene productores");
                System.out.println("Ej: pro=1"+"\n"+
                               "    Pro1=Productor::P1::20::28"+"\n"+
                               "    con=2"+"\n"+
                               "    Con1=Consumidor::C1::100::6"+"\n"+
                               "    Con2=Consumidor::C2::120::9"+"\n");
            }
            
            try
            {
                for (int b=1;b<=(Integer.valueOf(configuracion.getProperty("con")));b++)
                {
                    String[] consu = configuracion.getProperty("Con"+b).split("::"); 
                    System.out.println("Hilo "+consu[0]+": nombre "+consu[1]+", tiempo="+consu[2]+", número de elementos="+consu[3]);
                    contador=contador+1;
                }
            }catch (Exception e)
            {
                System.out.println("Error: Archivo de configuración de hilos (hilos.cfg) mal configurado o no tiene consumidores.");
                System.out.println("Ej: pro=1"+"\n"+
                               "    Pro1=Productor::P1::20::28"+"\n"+
                               "    con=2"+"\n"+
                               "    Con1=Consumidor::C1::100::6"+"\n"+
                               "    Con2=Consumidor::C2::120::9"+"\n");
            }
            System.out.println("\n"+"Cargada configuración de hilos.");
            System.out.println("Cantidad de hilos:"+contador);
            System.out.println("\n"+"\n"+"Ejecución de hilos concurrentes");
            System.out.println("-------------------------------");
 
            try
            {
        // Lanzamos los hilos creados a partir del contenido leído del archivo
                for (int i=1;i<=(Integer.valueOf(configuracion.getProperty("pro")));i++)
                {
                    String[] prod = configuracion.getProperty("Pro"+i).split("::");    
                   productor= new ProductorElementos((prod[1]),(Integer.valueOf(prod[2])),(Integer.valueOf(prod[3])));
                   new Thread(productor).start();
                }
            }catch(Exception e)
            {
                
            }
            try
            {
                for (int i=1;i<=(Integer.valueOf(configuracion.getProperty("con")));i++)
                {
                    String[] consu = configuracion.getProperty("Con"+i).split("::");
                    Thread consumidor = new ConsumidorElementos((consu[1]),(Integer.valueOf(consu[2])),(Integer.valueOf(consu[3])));
                    consumidor.start();
                }
            }catch(Exception e)
            {
               
            }
        }catch (IOException e)
        {
            System.out.println("Ocurrió un error: "+e.getMessage());
        }
    }
}
