package generador;

import java.security.SecureRandom;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benmu
 */

public class GeneradorApli extends javax.swing.JFrame {
    ArrayList array=new ArrayList(10);//Creo un array de 10 posiciones que me servirá para ir capturando las cadenas y que aparezcan posteriormente como conjunto de cadenas separadas en Jlist.
    DefaultListModel modelo=new DefaultListModel();//Se crea un modelo de lista para el Jlist que nos servirá para incluir la lista de cadenas.

    /**
     * Creates new form generador
     */
    public GeneradorApli() {
        initComponents();
        lista.setModel(modelo);//Establezco el modelo creado anteriormente en la Jlist.
        this.setLocationRelativeTo(null);//Centro el interfaz, para que al ejecutarlo me aparezca en el centro de la pantalla.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     
    
    public void verlista(){//Creo una clase para poder sacar en el jlist, la lista de las cadenas creadas almacenadas en el array.
        for(int k=0;k<array.size();k++){
            modelo.addElement(array.get(k));
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cuadroNumero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        etiqueta = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("GENERADOR DE CADENA DE TEXTOS DE 10 CARACTERES");

        jLabel2.setText("Señala el número de cadenas (De 0 a 10):");

        cuadroNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuadroNumeroKeyTyped(evt);
            }
        });

        jLabel3.setText("RESULTADO:");
        jLabel3.setToolTipText("");

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        lista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lista);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cuadroNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(ok)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(107, 107, 107))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cuadroNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ok))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(134, 134, 134)
                        .addComponent(etiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
        
        validarNumero();
        
        //GENERADOR//
        
        //Variables constantes//
        String caract_minus = "abcdefghijklmnopqrstuvwxyz";//Establezco una variable string con el alfabeto en minúsculas
        String caract_mayus = caract_minus.toUpperCase();//Establezco una variable string con el alfabeto anterior en mayúsculas
        
        // Variables de entrada//
        SecureRandom random = new SecureRandom();//Creo una variable que creará números aleatorios
        int numero= Integer.parseInt(cuadroNumero.getText());//Establezco una variable int para introducir el número de cadenas deseadas
        
        // Variables de salida//
        String cadenaAzar = caract_minus + caract_mayus;//Creo una variable string que sumará uno de los caracteres del alfabeto en mayúsculas y otro en minúsculas
        
        //----------------------------------------------
        //                 Procesamiento
        //----------------------------------------------
        if (numero<=10){//Condición si el número introducido es menor o igual a 10, se ejecutará todo el resto
            for(int a = 0; a < numero; a++){//Establezco un primero for, que creará el número de cadenas, utilizando un stringBuilder y otro for para unir la cadena de las dos variables
                StringBuilder sb = new StringBuilder(numero);
                for (int i = 0; i < 10; i++){//For que creará la cadena de 10 caracteres
                    int rndCharAt = random.nextInt(cadenaAzar.length());
                    char rndChar = cadenaAzar.charAt(rndCharAt);
                    sb.append(rndChar); 
                }
                String cad=sb.toString();//Paso la variable stringbuilder (sb) a string (cad) para poder añadirla al array, cada cadena generada. Posterioremente la sacaré con verlista()
                array.add(cad);//Introduzo el string cad (con la cadena generada) en el array.
                System.out.print(cad);//Saco por pantalla el archivo string cad para que pueda ser utilizado por la tubería con el programa frecuencia
            }
            cuadroNumero.setEnabled(false);//Desactivo el cuadro de texto donde se señala los procesos una vez obtenido las cadenas
            ok.setEnabled(false);//Desactivo el boton ok que genera los procesos
        }

        //----------------------------------------------
        //                 Resultados
        //----------------------------------------------
        verlista();//Ejecuto la clase verlista para poder incluir el conjunto de cadenas en jlist.
        
    }//GEN-LAST:event_okActionPerformed

    private void cuadroNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuadroNumeroKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();//Establezco las restricciones a la caja de texto donde se introduce el número de cadenas.
        if (c<'0' || c>'9')evt.consume();

        if (cuadroNumero.getText().length()>=2)
        {
            evt.consume();
        }
    }//GEN-LAST:event_cuadroNumeroKeyTyped
    private boolean validarNumero ()//Establezco una clase para validar el número que se introducirá en la caja de procesos, de tal forma que aparecerán determinados paneles si no cumple los requisitos.
    {
         
        String num=cuadroNumero.getText();
        if (num==null||"".equals(num))
        {
            JOptionPane.showMessageDialog(this, "El número de cadenas no puede estar vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        int numero= Integer.parseInt(cuadroNumero.getText());
            if (numero>10) 
        {
            JOptionPane.showMessageDialog(this, "Error. El número de cadenas tiene que ser de 0 a 10", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
            return true;
    }
    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cuadroNumero;
    private javax.swing.JLabel etiqueta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lista;
    private javax.swing.JButton ok;
    // End of variables declaration//GEN-END:variables
}
