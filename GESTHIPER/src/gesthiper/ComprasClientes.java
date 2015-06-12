/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 *
 * @author 72
 */
public class ComprasClientes implements Serializable {
    private String id_cliente;
    public int n_compras;
   private int distintos;
   
    private HashMap<String,wrapperProdutos> produtosComprados;
    float total_facturado;

    public int getDistintos() {
        return distintos;
    }

    public void setDistintos(int distintos) {
        this.distintos = distintos;
    }

    public float getTotal_facturado() {
        return total_facturado;
    }

    public void setTotal_facturado(float total_facturado) {
        this.total_facturado = total_facturado;
    }
    
    

    public HashMap<String, wrapperProdutos> getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(HashMap<String, wrapperProdutos> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }
    
    
    
    public void insereWrapperProduto(String id_produto, int quantidade, float valor){
        this.total_facturado +=valor;
        if(produtosComprados.containsKey(id_produto)){
           // wrapperProdutos aux = produtosComprados.get(id_produto);
            produtosComprados.get(id_produto).setQuantidade(produtosComprados.get(id_produto).getQuantidade()+quantidade);
            produtosComprados.get(id_produto).setN_compras(produtosComprados.get(id_produto).getN_compras()+1);
            
           
        }
        else{
            produtosComprados.put(id_produto, new wrapperProdutos(id_produto,quantidade));
            this.distintos++;
        }
    }

    public ComprasClientes(String id_cliente) {
        this.id_cliente = id_cliente;
        this.n_compras = 1;
        this.total_facturado = 0f;
        this.distintos = 0;
        this.produtosComprados = new HashMap();
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getN_compras() {
        return n_compras;
    }

    public void setN_compras(int n_compras) {
        this.n_compras = n_compras;
    }
    
    

    class wrapperProdutos implements Serializable {
        
        public String id_produto;
        public int n_compras;
        public int quantidade;

        public wrapperProdutos() {
        }

        public wrapperProdutos(String id_produto, int quantidade) {
            this.id_produto = id_produto;
            this.quantidade = quantidade;
            this.n_compras = 1;
            
        }

        public String getId_produto() {
            return id_produto;
        }

        public void setId_produto(String id_produto) {
            this.id_produto = id_produto;
        }

        public int getN_compras() {
            return n_compras;
        }

        public void setN_compras(int n_compras) {
            this.n_compras = n_compras;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
        
        
        
    }
    
    class wrapperProdutosComparator implements Comparator<wrapperProdutos>, Serializable {

        @Override
        public int compare(wrapperProdutos c1, wrapperProdutos c2) {

            int res = c2.n_compras - c1.n_compras;
            if (res == 0) {
                res = c2.id_produto.compareTo(c1.id_produto);
            }
            return res;
        }
    }
    
    
    

    @Override
    public String toString() {
        return "ComprasClientes{" + "id_cliente=" + id_cliente + ", n_compras=" + n_compras + ", produtosComprados=" + produtosComprados + '}';
    }


}

