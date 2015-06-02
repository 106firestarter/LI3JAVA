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
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 72
 */


public class GESTHIPER {
    
    private HashMap<String,Cliente> listaClientes;
    private HashMap<String,Produtos> listaProdutos;
    private TreeSet<Compra> TreeCompras;
    private CatalogoCompras catalogoCompras;

     public void carrega_produtos(String string){

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
     public void parse_compra(String compra){
         
         String parts[] = compra.split(" ");
         Compra novaCompra;
        novaCompra = new Compra(parts[4],parts[0],Integer.parseInt(parts[2]),Float.parseFloat(parts[1]),parts[3].charAt(0),Integer.parseInt(parts[5]));
        //System.out.println(novaCompra.toString()); 
        this.TreeCompras.add(novaCompra);
        catalogoCompras.insere_compra(novaCompra);
        
     }
     public void carrega_compras(String string){

         File file = new File(string);
         BufferedReader reader = null;
         
         try {
             reader = new BufferedReader(new FileReader(file));
             String text = null;

    while ((text = reader.readLine()) != null) {
        //System.out.println(text);
        parse_compra(text);
    }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         }
System.out.println(catalogoCompras.toString());
}
     
     public void carrega_clientes(String string){

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
        GESTHIPER main = new GESTHIPER();
        main.listaClientes = new HashMap<>();
        main.listaProdutos = new HashMap<>();
        main.TreeCompras = new TreeSet<>(new comprasComparator());
        main.catalogoCompras = new CatalogoCompras();
        String produtos = "FichProdutos.txt";
        main.carrega_produtos(produtos);
        String clientes = "FichClientes.txt";
        main.carrega_clientes(clientes);
        String compras = "Compras.txt";
        main.carrega_compras(compras);
            }
    
}
