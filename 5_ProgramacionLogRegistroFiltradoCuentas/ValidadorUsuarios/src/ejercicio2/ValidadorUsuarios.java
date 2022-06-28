/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2;

import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author benmu
 */
public class ValidadorUsuarios {
    
   
    
    public enum Evento {
        OPEN_FILE_DONE, OPEN_FILE_FAIL, VALID_ACCOUNT, INVALID_ACCOUNT, 
        APP_OPEN, APP_QUIT
    }

    public static void registrarEvento (Evento evento, String archivo) throws IOException { 

    
         try {
             
             Logger loger = Logger.getLogger("ejercicio2.ValidadorUsuarios");
             
             Handler consoleHandler = new ConsoleHandler(); //Estableceremos un manejador de errores
     
             /*Colocaremos la ruta donde se guardará el log. Dado que no se colocó ruta se grabará en el directorio del ejercicio. La propiedad true indica que siempre se escribirá sobre 
             el mismo archivo y no generará una copia hasta que este archivo sea igual a 5 mb de tamaño*/
             Handler fileHandler = new FileHandler("filtrador.log", true);
             
             SimpleFormatter simpleFormatter = new SimpleFormatter();// Se establece únicamente para generar un formato para la presentación de los errores o el trace
      
             fileHandler.setFormatter(simpleFormatter);
             
             loger.addHandler(fileHandler);
             
             consoleHandler.setLevel(Level.ALL); //Se incia el manejador de errores
             
             fileHandler.setLevel(Level.ALL); //Se inicia el proceso de registro del logger. Ambos comando se colocan el nivel de registro de errores en el log con "Level.ALL"
            
             //Switch que según el registro de evento selecionado "registrarEvento (Evento.APP_QUIT, ejercicio1.ValidadorUsuarios.class.getName());" saltará y se imprimirá en consola en el archivo "filtrador.log"            
             switch (evento) {
                 case APP_OPEN:
                 //Registra evento de aplicación iniciada
                    loger.log(Level.INFO, "INFORMACIÓN: Aplicación iniciada"+"\n");
                    break;
        
                 case APP_QUIT:
                    // Registrar evento aplicación finalizada
                    loger.log(Level.INFO, "INFORMACIÓN: Aplicación finalizada");
                    break;
        
                 case OPEN_FILE_DONE:
                    // Registrar evento de archivo de cuentas abierto
                    loger.log(Level.INFO, "INFORMACIÓN: Archivo de cuentas abierto "+archivo+"\n");
                    break;
        
                 case OPEN_FILE_FAIL:
                    // Registrar evento error al abrir archivo de cuentas
                    loger.log(Level.SEVERE, "INFORMACIÓN: Error de E/S al intentar abrir archivo de cuentas  "+archivo+"\n");
                    break;

                 case INVALID_ACCOUNT:
                    //Registra evento de cuenta de usuario no válida
                    loger.log(Level.WARNING, "ADVERTENCIA: Cuenta de usuario no válida  "+archivo+"\n");
                    break;

                 case VALID_ACCOUNT:
                    //Registra evento de cuenta de usuario válida
                    loger.log(Level.INFO, "INFORMACIÓN: Cuenta de usuario válida  "+archivo+"\n");   
                    break;
             
             }
             
             fileHandler.close(); //Cerramos el archivo fileHandler
         
         } catch (SecurityException a) {
             
             a.printStackTrace();
         
         } catch (IOException b) {
             
             b.printStackTrace();
         }
    
    }

}
