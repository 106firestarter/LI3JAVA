/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 72
 */


public class GESTHIPER {
    
    public static HashMap<String,Cliente> listaClientes;
    public static HashMap<String,Produtos> listaProdutos;

     public static void carrega_produtos(String string){

         File file = new File(string);
         BufferedReader reader = null;
         
         try {
             reader = new BufferedReader(new FileReader(file));
             String text = null;

    while ((text = reader.readLine()) != null) {
        listaProdutos.put(text, new Produtos(text));
        //System.out.println(text);
    }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         }


}
     
     public static void carrega_clientes(String string){

         File file = new File(string);
         BufferedReader reader = null;
         
         try {
             reader = new BufferedReader(new FileReader(file));
             String text = null;

    while ((text = reader.readLine()) != null) {
        //System.out.println(text);
        listaClientes.put(text, new Cliente(text));
    }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         }


}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaClientes = new HashMap<>();
        listaProdutos = new HashMap<>();
        String produtos = "FichProdutos.txt";
        carrega_produtos(produtos);
        String clientes = "FichClientes.txt";
        carrega_clientes(clientes);
        
    }
    
}
