/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorsocket;

/**
 *
 * @author Usuario
 */
public class PruebaCalculadora {
        public static void main(String[] args){
            Calculadora c = new Calculadora(45.4,2.5,'/');
            String resultado = c.getResultado();
            System.out.println("RESULTADO OBTENIDO DE LA CLASE CALCULADORA");
            System.out.println("==========================================");
            System.out.println(resultado);
        }
}
