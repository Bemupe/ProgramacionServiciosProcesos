/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author profe
 */
public class ConsumidorElementos extends Thread{
    String nombre;
    int tiempo_con_element;
    int cant_element;
    ColaElementos cola_con=new ColaElementos();
    int modo_fun;
    
    public ConsumidorElementos (String nombre, int tiempo_con_element, int cant_element){
        this.nombre=nombre;
        this.tiempo_con_element=tiempo_con_element;
        this.cant_element=cant_element; 
        this.modo_fun=modo_fun;
    }
    
    public synchronized  void run() 
    {
        int contador=cant_element;
        try{
        do
        {   //Consumimos los caracteres producidos, que permanecen en "colaElementos" y los ilntroducimos en cola_con.
            char dato = GestionElementos.colaElementos.getElemento();
            cola_con.addElemento(dato);
            contador=contador-1;
            //Modo de funcionamiento
            if (GestionElementos.modo==0)
            {
                try
                {
                    System.out.println("Consumidor "+ConsumidorElementos.this.nombre+" consumiendo elemento "+dato+". Consumidos hasta ahora: "+cola_con);
                }catch (Exception e)
                {
                    System.out.println("Error, no hay elementos en cola o no hay productores de elementos: "+e.getMessage());
                }
            }
            //Espera de consumición
            try
            {
                wait(tiempo_con_element);
            }catch (InterruptedException exc)
            {
                System.out.println("Hilo principal interrumpido.");
            }
        } while(contador!=0);
        }catch (Exception e)
                {
                    System.out.println("Error, no hay elementos en cola o no hay productores de elementos: "+e.getMessage());
                }
        System.out.println("Fin del consumidor "+nombre+". Elementos consumidos: "+cola_con+"." );
        System.out.println("Elementos disponibles en la cola:  "+GestionElementos.colaElementos);
    }
}