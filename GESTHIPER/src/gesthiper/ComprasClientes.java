/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author 72
 */
public class ComprasClientes implements Serializable {
    private String id_cliente;
    public int n_compras;
    private HashMap<String,wrapperProdutos> produtosComprados;
    
    public void insereWrapperProduto(String id_produto, int quantidade){
        
        if(produtosComprados.containsKey(id_produto)){
            wrapperProdutos aux = produtosComprados.get(id_produto);
            aux.quantidade += quantidade;
            aux.n_compras++;
        }
        else{
            produtosComprados.put(id_produto, new wrapperProdutos(id_produto,quantidade));
        }
    }

    public ComprasClientes(String id_cliente) {
        this.id_cliente = id_cliente;
        this.n_compras = 1;
        produtosComprados = new HashMap();
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
            this.n_compras = 0;
        }
        
        
        
    }
    
    
    
    

    @Override
    public String toString() {
        return "ComprasClientes{" + "id_cliente=" + id_cliente + ", n_compras=" + n_compras + ", produtosComprados=" + produtosComprados + '}';
    }


}

