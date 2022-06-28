package urlconnection;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author benmu
 */
public class Cliente_http_URLConnection {
    public static void main(String[] args) throws IOException, NullPointerException, MalformedURLException {
        //Distintos URL de prueba
        String urlString="http://www.iesaguadulce.es/centro/index.php/oferta-formativa/formacion-profesional-a-distancia/dam-modalidad-distancia";
        //String urlString="http://www.iesaguadulce.es/centro/templates/dd_toysshop_34/images/logo_ies_aguadulce.png";
        //String urlString="http://www.iesaguadulce.es/centro/images/Documentos_oficiales/PlanesDeCentro/202021/planes2021_22/ProyectoFormacinProfesionalaDistanciav1_01.pdf"; 
        //String urlString="httx://help.me";
        //String urlString="http://www.google.es";
        //String urlString="http://www.hijk22.com/";
        
        //Variables para guardar en caso de HTML
        int leido;
        byte[] bufByte;
        String ruta = "c:\\salida\\";
        String nombre = "salida.html";
        File archivo = new File(ruta, nombre);
        HashMap <String,String>cabecera=new HashMap<String,String>();
        
 
        System.out.println("CLIENTE HTTP");
        System.out.println("------------");
        System.out.println("Cliente del alumno: Benjamín Mulero Pedrosa");
        System.out.println("Conectándose a la URL: "+urlString+"\n");
         
        
        try{
        
            //Creamos objeto URL
            URL url = new URL(urlString);
            //Usamos la URL para conectarnos a la dirección.
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            //Obtenemos la cabecera del url y lo introducimos un Map (String,List<String>)
            Map headers = conexion.getHeaderFields();
            //Obtenemos los campos de cada campo de la cabecera y los introducimos en un conjunt de Strings
            Set<String> keys = headers.keySet();
            //Introducimos con un for-each cada campo con su cabecera correspondiente en un Hasmap (cabecera), para poder trabajar mejor con la cabecera dle URL.
            for( String key : keys ){
                String val = conexion.getHeaderField(key);
                cabecera.put(key, val);
            }

            //Si el URL es tipo "text/html".
            //------------------------------
            if (conexion.getContentType().contains("text/html")) {
                
                String[] tipoCont=cabecera.get("Content-Type").split(";");
                
                System.out.println("Tipo de contenido: "+tipoCont[0]);
                
                System.out.println("Respuesta: "+cabecera.get(null));
                
                System.out.println("Fecha última modificación: "+cabecera.get("Last-Modified"));//Posible uso tambiÃ©n "conexion.getLastModifed()"
                
                String[] cookie=cabecera.get("Set-Cookie").split(";");
                
                System.out.println("Valor de la cookie: "+cookie[0]);
                
                String[] idioma=cabecera.get("Content-Type").split(";");//Posible uso también "conexion.getContentType()"
                
                String[] sub=idioma[1].split("=");
                
                System.out.println("Idioma: "+sub[1]);
                
                
                //GUARDAMOS EN UN ARCHIVO EL HTML 
                
                //Creamos un path con la ruta del directorio que creamos
                Path path = Paths.get(ruta);
                
                //Si el path no existe, creamos el directorio, si existe, borramos el archivo que se encuentra en el diretorio para crear uno nuevo.
                if (!Files.exists(path)) {
                    
                    Files.createDirectory(path);
                
                }else{archivo.delete();}
                
                //Creamos el archivo donde se guardará el HTML
                archivo.createNewFile();
                
                //Preparamos las variables para los datos HTML enviados por el URL
                InputStream imputString=conexion.getInputStream();
                
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(imputString));
                
                //CharBuffer charBuffer = CharBuffer.allocate(512);
                char[] bufChar = new char [512];
                
                //Introducimos en cada línea del HTML en el archivo salida.html
                while ((leido = bufferedReader.read(bufChar)) > 0) {
                    
                    FileWriter fw = new FileWriter(archivo , true); 
                    
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    bw.write(new String(bufChar, 0, leido));
                    
                    bw.close();
                
                }
                System.out.println();
                System.out.println("Sálida HTML obtenida correctamente");
                
            
            }
            //Si url es tipo "image"
            //---------------------
            if (conexion.getContentType().contains("image")) {
                String[] tipoCont=cabecera.get("Content-Type").split(";");
                
                System.out.println("Tipo de contenido: "+tipoCont[0]);
                
                System.out.println("Respuesta: "+cabecera.get(null));
                
                System.out.println("Fecha última modificación: "+cabecera.get("Last-Modified"));
                
 
                System.out.println("Tamaño de la imagen: "+cabecera.get("Content-Length")+" "+"Bytes");//Posible uso también "conexion.getContentLength()"
                
                
                String[] imagen=cabecera.get("Content-Type").split("/");
                
                System.out.println("Tipo de imagen: "+imagen[1]);
            }
            
            //Si URL es de otros tipos que no sean los anteriores
            //---------------------------------------------------
            if (!conexion.getContentType().contains("image")&&!conexion.getContentType().contains("text/html")) {
                String[] tipoCont=cabecera.get("Content-Type").split(";");
                
                System.out.println("Tipo de contenido: "+tipoCont[0]);
                
                System.out.println("Respuesta: "+cabecera.get(null));
                
                System.out.println("No se trata de ningún archivo especificado");

            }
        //Excepciones en el caso URL mal formado, excepción de tiempo de ejecución  porque la cabecera no existe, otras excepciones.
        } catch (MalformedURLException ex){
            
            System.out.println("Error.URL no válida: "+ex.getMessage());
        
        } catch (NullPointerException a){
            
            System.out.println("Error de E/S: "+urlString);//"Error al buscar la cabecera: "
        
        } catch (IOException b) {
            
            System.out.printf("Error de E/S: %s\n", b.getMessage());
        
        }  finally {
      //termina la aplicación
      System.exit(0);
    
        }
    
    }

}
