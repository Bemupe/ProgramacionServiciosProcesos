
package http_server_concurrente;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static utilidades.Utilidades.esPrimo;
import static utilidades.Utilidades.getFechaHoraActualFormateada;

/**
 *
 * @author benmu
 */
public class HttpHandlerEsPrimo implements HttpHandler{
    
    @Override
         public void handle(HttpExchange he) throws IOException {
             
             String respuestaValida=null;
             
             //COMPROBAMOS QUE EL URL OBTENIDO ES CORRECTO CONFORME A UN PATRÓN. EN CASO CONTRARIO SALDRÁ MENSAJE DE ERROR
             URI recepcionUri = he.getRequestURI();
             
             String uri=recepcionUri.toString();
             
             String uriQuery=recepcionUri.getQuery();
             
             System.out.println("["
                     +getFechaHoraActualFormateada()
                     +"]"+" Atendiendo a petición: "+uri);
             
             Pattern pat = Pattern.compile("[?]+[n]+[u]+[m]+[e]+[r]+[o]+=+[0-9]");
             
             Matcher mat = pat.matcher(uri);
             
             
             
             while(mat.find()==false){
                 
                 String respuestaNoVali = "Petición no válida";
                 
                 mat.find();
                 
                 he.sendResponseHeaders(200, respuestaNoVali.getBytes("Windows-1252").length);
                 
                 OutputStream os = he.getResponseBody();
                 
                 os.write(respuestaNoVali.getBytes("Windows-1252"));
                 
                 System.out.println("["
                         +getFechaHoraActualFormateada()
                         +"]"+" Respuesta a la petición: "+uri
                         +" -> "+respuestaNoVali);
                 
                 os.close();  
                 
             }   
                          
             //OBTENEMOS EL NÚMERO DEL URIQUERY
             String corte[]=uriQuery.split("=");
             
              
             //ENVIAMOS LOS RESULTADOS 
             Long numero=Long.parseLong(corte[1]);//Parseamos el número string introducido en "corte", a variable "long", para que se pueda usar con el método "número primo".
                 
             if (esPrimo(numero)==true){//Si el número es primo, introducimos respuesta positiva en un string y la envíamos 
                     
                 respuestaValida  ="El número "+corte[1]+" si es primo";//
                     
                 he.sendResponseHeaders(200, respuestaValida.getBytes("Windows-1252").length);//Establecemos la codificación "Windows-1252" para que aparezcan los acentos y la ñ.
                 
                 OutputStream os = he.getResponseBody();
                 
                 os.write(respuestaValida.getBytes("Windows-1252"));
                 
                 os.close();
             
             }else {//Si el número no es primo, introducimos la respuesta negativa en un string y la envíamos.
                 
                 respuestaValida  ="El número "+corte[1]+" no es primo";
                     
                 he.sendResponseHeaders(200, respuestaValida.getBytes("Windows-1252").length);
                     
                 OutputStream os = he.getResponseBody();
                 
                 os.write(respuestaValida.getBytes("Windows-1252"));
                 
                 os.close();
             
             };
             
             System.out.println("["+getFechaHoraActualFormateada()+"]"+" Respuesta a la petición: "
                          +uri+" -> "+respuestaValida);
         
         } 
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
      
                  
    
                  
                    
                  
                  
                  
                  
                  
                  
                  

                  

                  

                  
                  
                  
         }
    


    

