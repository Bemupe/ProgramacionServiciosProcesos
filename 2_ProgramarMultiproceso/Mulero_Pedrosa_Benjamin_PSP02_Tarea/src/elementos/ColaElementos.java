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
public class ColaElementos {
    private Queue<Character>cola;//Creamos con la interfaz "Queue", un objeto que contendrá "caracteres", denominado "cola". Para crear instancias de "cola", es necesario unas clases concretas para implemetar la funcionalidad de la interfaz dado que no se puede crear una instancia. 
    
    
    /**
    *Utilizamos la clase LinkedList para implementar la interfaz de cola. Crea una cola de elementos LinkedList de caracteres.
    * @return
    */
    public ColaElementos()
    {
        cola= new LinkedList<Character>();}

    
    /**
    *Añade un elemento de tipo caracter al principio de la cola seleccionada.
    * @return
    */
    public synchronized void addElemento (char elemento)
     {
         synchronized(cola)
         {
             cola.add(elemento);
             notify();
         }
     }
    
    
    /**
    *Se obtiene el primer caracter de la cola y se elimina de esta.
    * @return
    */
    public synchronized char getElemento() 
    {
        if (cola.isEmpty())
            try 
            {
                wait(300);
               
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(ColaElementos.class.getName()).log(Level.SEVERE, null, ex);
            }

        return GestionElementos.colaElementos.poll();
    }
    
    
    /**
    *Se obtiene o devuelve el tamaño de la cola(número de caracteres)
    * @return
    */
    public int size()
    {
        return cola.size();
    } 
    
    
    /**
     *Se obtiene o devuelve, el conjunto de caracteres que contiene el objeto (la cola) sobre la que se aplica. Ej. [a,b,c,d].
     * @return
     */
    public  synchronized String toString()
    {
      return cola.toString();
    } 
    
    
    /**
    *Se obtiene o devuelve el primer caracter de la cola y se elimina de esa cola.
    * @return
    */
    public char poll() 
    {
       
       return cola.poll();
    }
}
