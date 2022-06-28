package elementos;
import static elementos.GestionElementos.colaElementos;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author profe
 */
public class ProductorElementos implements Runnable {
    private String nombre;
    private int tiempo_pro_element;
    private int cant_element;
    ColaElementos cola_pro=new ColaElementos();
    private int modo_fun;
    
    
    public ProductorElementos (String nombre,int tiempo_pro_element, 
     int cant_element){
    this.nombre=nombre;
    this.tiempo_pro_element=tiempo_pro_element;
    this.cant_element=cant_element;
    this.cola_pro=cola_pro;
    this.modo_fun=modo_fun=0;
   }
      
 
    public synchronized void run() {
        int contador=cant_element;
        
        do
        {   //Introducimos los cararacters si el número de elementos señalados es mayor de 26
            if (cant_element>=26){for (char car='A'; car<='Z'; car++)
                GestionElementos.colaElementos.addElemento(car);
            for (char car='A'; car<='Z'; car++) cola_pro.addElemento(car);
            for (char car='A'; car<='Z'; car++) 
                //Modo de funcionamiento
                if (GestionElementos.modo==0)
                {
                    try
                    {
                        System.out.println("Productor "+ProductorElementos.this.nombre+" generando y añadiendo elemento "+car);
                    }catch (Exception e)
                    {
                        System.out.println("Ocurrió un error: "+e.getMessage());
                    }
                }
            //Espera de producción
            try
            {
                wait(tiempo_pro_element);
            }catch (InterruptedException exc)
            {
                System.out.println("Hilo principal interrumpido.");
            }
            cant_element=cant_element-26;contador=contador-26;
            }
            //Introducimos los caracteres si el número de elementos señalados es menor que 26.
            if (cant_element<26) 
            {
                for (char car='A'; car<'A'+(cant_element); car++)
                {
                    cola_pro.addElemento(car);
                    GestionElementos.colaElementos.addElemento(car);
                    //Modo de funcionamiento
                    if (GestionElementos.modo==0)
                    {
                        try
                        {
                            System.out.println("Productor "+ProductorElementos.this.nombre+" generando y añadiendo elemento "+car);
                        }catch (Exception e)
                        {
                            System.out.println("Ocurrió un error: "+e.getMessage());
                        }
                    }
                    //Espera de producción
                    try
                    {
                        wait(tiempo_pro_element);
                    }catch (InterruptedException exc)
                    {
                        System.out.println("Hilo principal interrumpido.");
                    }
                    contador=contador-1;
                }
            }  
        }while(contador!=0);
        System.out.println("Fin del productor "+nombre+" . Elementos producidos: "+cola_pro+".");
        System.out.println("Elementos disponibles en la cola:  "+GestionElementos.colaElementos);
    }
    
}

