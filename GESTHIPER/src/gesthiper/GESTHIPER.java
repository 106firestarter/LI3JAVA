/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import gesthiper.Wrappers.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 72
 */


public class GESTHIPER implements Serializable{
    
    private HashMap<String,Cliente> listaClientes;
    private HashMap<String,Produtos> listaProdutos;
    private TreeSet<Compra> TreeCompras;
    private ArrayList<Compra> comprasInvalidas;
    private CatalogoCompras catalogoCompras;
    private Contabilidade contabilidade;
    
    public boolean existe_cliente(String cliente){
        return listaClientes.containsKey(cliente);
    }
    
    public boolean existe_produto(String produto){
        return listaProdutos.containsKey(produto);
    }

     public void carrega_produtos(String string){

         File file = new File(string);
         BufferedReader reader = null;
         
         try {
             reader = new BufferedReader(new FileReader(file));
             String text = null;

    while ((text = reader.readLine()) != null) {
        listaProdutos.put(text, new Produtos(text));
        contabilidade.inicia_hash(text);
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
        if(existe_produto(parts[0])&& existe_cliente(parts[4])){
        this.TreeCompras.add(novaCompra);
        catalogoCompras.insere_compra_cliente(novaCompra);
        contabilidade.insere_compra_produto(novaCompra);
        }
        else{
            comprasInvalidas.add(novaCompra);
        }
        
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
    catalogoCompras.preenche_tree();
    contabilidade.preenche_tree();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(GESTHIPER.class.getName()).log(Level.SEVERE, null, ex);
         }
//System.out.println(catalogoCompras.toString());
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
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
     
    public void carrega_ficheiro(String ficheiro) throws IOException, ClassNotFoundException{
        
        
        ObjectInputStream teste;
            teste = new ObjectInputStream(new FileInputStream(ficheiro));
            GESTHIPER novo = (GESTHIPER) teste.readObject();
        
    }
    
    public void imprime_menu(ArrayList<String> lista,int x){
        int i = x;
        
        while(i<x+10){
            System.out.println(lista.get(i));
            i++;
        }
    }
    
    public void grava_ficheiro(GESTHIPER main, String ficheiro) throws FileNotFoundException, IOException{
        
         FileOutputStream baos = new FileOutputStream(ficheiro);
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(main);
            oos.flush();
            oos.close();
            }
        
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException  {
            int lifeTime = 1;
            Scanner input = new Scanner(System.in);
            String aux;
            String fileClientes = null, fileCompras = null, fileProdutos = null;
            GESTHIPER main = new GESTHIPER();
            main.listaClientes = new HashMap<>();
            main.listaProdutos = new HashMap<>();
            main.comprasInvalidas = new ArrayList();
            main.TreeCompras = new TreeSet(new comprasComparator());
            main.catalogoCompras = new CatalogoCompras();
            main.contabilidade = new Contabilidade();
                System.out.println("//***********************************************************************************************************************************\\");
                System.out.println("//***********************************************************************************************************************************\\");
                System.out.println("//***                                                          GESTHIPER                                                          ***\\");
                System.out.println("//***********************************************************************************************************************************\\");
                System.out.println("//***                                           Deseja carregar os ficheiros default? (s/n)                                       ***\\");
                System.out.println("//***********************************************************************************************************************************\\");
                aux = input.nextLine();
                if( aux.equals("s") ){
                    fileClientes = "FichClientes.txt";
                    fileProdutos = "FichProdutos.txt";
                    fileCompras = "Compras.txt";
                    
                }else if ( aux.equals("n") ){
                    System.out.println("//***********************************************************************************************************************************\\");
                    System.out.println("//***********************************************************************************************************************************\\");
                    System.out.println("//***                                                          GESTHIPER                                                          ***\\");
                    System.out.println("//***********************************************************************************************************************************\\");
                    System.out.println("//***                                         Introduza o nome do ficheiro para os clientes                                       ***\\");
                    System.out.println("//***********************************************************************************************************************************\\");
                    fileClientes = input.nextLine();
                     System.out.println("//***********************************************************************************************************************************\\");
                    System.out.println("//***                                         Introduza o nome do ficheiro para os produtos                                       ***\\");
                    System.out.println("//***********************************************************************************************************************************\\");
                    fileProdutos = input.nextLine();
                     System.out.println("//***********************************************************************************************************************************\\");
                    System.out.println("//***                                          Introduza o nome do ficheiro para as compras                                       ***\\");
                    System.out.println("//***********************************************************************************************************************************\\");
                    fileCompras = input.nextLine();
                }
            while(lifeTime==1){
                
                    
                    main.carrega_produtos(fileProdutos);
                    main.carrega_clientes(fileClientes);
                    main.carrega_compras(fileCompras);
                
                System.out.println("//***********************************************************************************************************************************\\");
                System.out.println("//***********************************************************************************************************************************\\");
                System.out.println("//***                                                          GESTHIPER                                                          ***\\");
                System.out.println("//***********************************************************************************************************************************\\");
                System.out.println("//*** 1- Lista ordenada com os códigos dos produtos nunca comprados e respectivo total.                                           ***\\");
                System.out.println("//*** 2- Lista ordenada com os códigos dos clientes que nunca compraram e seu total.                                              ***\\");
                System.out.println("//*** 3- Dado um mês válido, determinar o número total de compras e o total de clientes distintos que as realizaram.              ***\\");
                System.out.println("//*** 4- Para um cliente, determinar, para cada mês, quantas compras fez, quantos produtos distintos comprou E quanto gastou.     ***\\");
                System.out.println("//*** 5- Para um produto, determinar, mês a mês, quantas vezes foi comprado, por quantos clientes diferentes e o total facturado. ***\\");
                System.out.println("//*** 6- Para um produto, determinar, mês a mês, quantas vezes foi comprado em modo N e em modo P e respectivas facturações.      ***\\");
                System.out.println("//*** 7- Dado o código de um cliente determinar a lista de códigos de produtos que mais comprou (e quantos).                      ***\\");
                System.out.println("//*** 8- Determinar os X produtos mais vendidos em todo o ano e o número total de distintos clientes que o compraram.             ***\\");
                System.out.println("//*** 9- Determinar os X clientes que compraram um maior número de diferentes produtos indicando quantos.                         ***\\");
                System.out.println("//*** 10- Dado o código de um produto, determinar o conjunto dos X clientes que mais o compraram.                                 ***\\");
                System.out.println("//***********************************************************************************************************************************\\");
                System.out.println("//***********************************************************************************************************************************\\");
                
               aux = input.nextLine();
               
               if(aux.equals("1")){
                  wrapperQuery1 res1 = main.contabilidade.query_1();
                  
               }else if (aux.equals("2")){
                   wrapperQuery2 res2 = main.catalogoCompras.query_2();
               
               }else if (aux.equals("3")){
              System.out.println("//***********************************************************************************************************************************\\");
              System.out.println("//***                                           Por favor insira um mês válido.                                                   ***\\");
              System.out.println("//***********************************************************************************************************************************\\");
              aux = input.nextLine();
              wrapperQuery3 res3 = main.catalogoCompras.query3(Integer.parseInt(aux));
              System.out.println(aux);
                   
               }else if (aux.equals("4")){
              System.out.println("//***********************************************************************************************************************************\\");
              System.out.println("//***                                    Por favor indique um código de cliente válido.                                           ***\\");
              System.out.println("//***********************************************************************************************************************************\\");
              
              aux = input.nextLine();   
              wrapperQuery4 res4 = main.catalogoCompras.query4(aux);
                   
               }else if (aux.equals("5")){
              System.out.println("//***********************************************************************************************************************************\\");
              System.out.println("//***                                    Por favor indique um código de produto válido.                                            ***\\");
              System.out.println("//***********************************************************************************************************************************\\");
              aux = input.nextLine();  
              wrapperQuery5 res5 = main.contabilidade.query5(aux);
                   
                   
               }else if (aux.equals("6")){
              System.out.println("//***********************************************************************************************************************************\\");
              System.out.println("//***                                    Por favor indique um código de produto válido.                                            ***\\");
              System.out.println("//***********************************************************************************************************************************\\");
              aux = input.nextLine();   
                   wrapperQuery6 res6 = main.contabilidade.query6(aux);
                   
               }else if (aux.equals("7")){
                   aux = input.nextLine();
                   String aux1 = input.nextLine();   
                   wrapperQuery7 res7 = main.catalogoCompras.query7(aux,Integer.parseInt(aux1) );
                   
                   
               }else if (aux.equals("8")){
                   aux = input.nextLine();
                  wrapperQuery8 res8 = main.contabilidade.query8(Integer.parseInt(aux));
                   
                   
               }else if (aux.equals("9")){
                   aux = input.nextLine();
                  wrapperQuery9 res9 = main.catalogoCompras.query9(Integer.parseInt(aux));
                   
                   
               }else if (aux.equals("10")){
                   
                   aux = input.nextLine();
                 String aux1 = input.nextLine();
                 wrapperQuery10 res10 = main.contabilidade.query10(aux,Integer.parseInt(aux1) );
                   
               }else if (aux.equals("q")){
                   lifeTime = 0;
                   
                   
               }
               
               
                    

            

            
            //main.catalogoCompras.query_2();
            //System.out.println(main.catalogoCompras.mes_facturado[1]);
            //main.catalogoCompras.query7("CK166");
            //main.contabilidade.query8(20);
            //main.catalogoCompras.query9(20);
            //main.contabilidade.query6("XN1084");
            //main.contabilidade.query10("BZ5018", 20);
            //System.out.println("Comprás inválidas:" + main.comprasInvalidas.size());    
            
            
            
            }
        } 
            
            
            
        }
            
    

