
package http_server_basico;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static utilidades.Utilidades.getFechaHoraActualFormateada;

//COMPROBAMOS QUE EL URL OBTENIDO ES CORRECTO CONFORME A UN PATRÓN. EN CASO CONTRARIO SALDRÁ MENSAJE DE ERROR
/**
 *
 * @author benmu
 */
public class HttpHandlerSaludar implements HttpHandler {
    @Override
         public void handle(HttpExchange he) throws IOException {
             
           
                 //OBTENEMOS EL URI, EL URIQUERY Y CREAMOS EL PATRÓN
                 URI recepcionUri = he.getRequestURI();//Obtenemos el URI de la dirección introducida "/saludar?nombre=xxx&apellido=yyy"
                 
                 String uri=recepcionUri.toString();//Pasamos el URI a String
                 
                 String uriQuery=recepcionUri.getQuery();//Obtenemos el Query del URI "nombre=xxx&apellido=yyy"
                 
                 System.out.println("["
                     +getFechaHoraActualFormateada()
                     +"]"+" Atendiendo a petición: "+uri);
                  
                 Pattern pat = Pattern.compile("[?]+[n]+[o]+[m]+[b]+[r]+[e]+=+[a-zA-Z]+&+[a]+[p]+[e]+[l]+[l]+[i]+[d]+[o]+=+[a-zA-Z]");//Obtenemos el patrón del uriQuery          
             
                 Matcher mat = pat.matcher(uri);//Aplicamos el patrón al query.
                 
                 //NOS ASEGURAMOS QUE EL URI INTRODUCIDO ES CORRECTO PARA OBTENER NOMBRE Y APELLIDO
                 while(mat.find()==false){//Mientras el resultado del patrón sea falso se mantendrá el bucle.
                   
                     String respuestaNoValida = "Hola persona no identificada";
                    
                     mat.find();//Cambiamos el boolean para poder comparar correctamente si se aplica una nueva dirección. 
       
                     he.sendResponseHeaders(200, respuestaNoValida.getBytes("Windows-1252").length);//Codificación para tildes y la ñ en windows, pues netbeans tiene codificación UTF8 (puede cambiarse desde propiedades)
                      
                     OutputStream os = he.getResponseBody();
                        
                     os.write(respuestaNoValida.getBytes("Windows-1252"));
                 
                     System.out.println("["
                         +getFechaHoraActualFormateada()
                         +"]"+" Respuesta a la petición: "+uri
                         +" -> "+respuestaNoValida);
                 
                     os.close();  
                 
                 }

                //OBTENEMOS EL NOMBRE Y EL APELLIDO DEL URI 
                 String[] corteDef=null;//Array donde introducimos los resultados de aplicar el split ("&")     
             
                 HashMap<String, String> nomApe = new HashMap<String, String>();//Hashmap donde introduciremos como clave-valor el nombre y el apellido      
             
                 String[] corteOne=uriQuery.split("&");//Uso del primer split
             
                 for (int i=0;i<corteOne.length;i++){//Separamos nombre y apellido con un split y lo introducimos en el map.
                 
                     corteDef=corteOne[i].split("="); 
                 
                     for (int j=0;j<1;j++){ 
                     
                         nomApe.put(corteDef[j],corteDef[j+1]);

                     }

                 }
             
                //ENVÍAMOS LOS RESULTADOS 
                 String respuestaValida = "Hola "+nomApe.get("nombre")+" "+nomApe.get("apellido");    
             
                 he.sendResponseHeaders(200, respuestaValida.getBytes("Windows-1252").length);            
             
                 OutputStream os = he.getResponseBody();       
             
                 os.write(respuestaValida.getBytes("Windows-1252"));
                   
                 System.out.println("["
                     +getFechaHoraActualFormateada()
                     +"]"+" Respuesta a la petición: "
                     +uri+" -> "+respuestaValida);
             
                 os.close(); 
             

         }
}
