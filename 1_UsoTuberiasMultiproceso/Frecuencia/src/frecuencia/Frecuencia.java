/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frecuencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;


/**
 *
 * @author benmu
 */
public class Frecuencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Se ejecuta el projecto desde "Run project".
        FrecuenciaApli P = new FrecuenciaApli();
        P.setVisible(true);
        
    }
    
}
