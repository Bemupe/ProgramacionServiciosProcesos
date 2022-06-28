/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanzador;

/**
 *
 * @author benmu
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public void ejecutar(String ruta)
    {
        ProcessBuilder pb;
        try 
        {
            pb = new ProcessBuilder(ruta);
            pb.start();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        LanzadorApli P = new LanzadorApli();
        P.setVisible(true);
    }
  







}
