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
public class ComprasProdutos implements Serializable {
    private String id_produto;
    public int n_compras;
    private HashMap<String,wrapperClientes> clientesComprados;
    float total_facturado;

    public HashMap<String, wrapperClientes> getClientesComprados() {
        return clientesComprados;
    }

    public void setClientesComprados(HashMap<String, wrapperClientes> produtosComprados) {
        this.clientesComprados = produtosComprados;
    }
    
    
    
    public void insereWrapperCliente(String id_produto, int quantidade, float valor){
        this.total_facturado +=valor;
        if(clientesComprados.containsKey(id_produto)){
            wrapperClientes aux = clientesComprados.get(id_produto);
            aux.quantidade += quantidade;
            aux.n_compras++;
        }
        else{
            clientesComprados.put(id_produto, new wrapperClientes(id_produto,quantidade));
        }
    }

    public ComprasProdutos(String id_produto) {
        this.id_produto = id_produto;
        this.n_compras = 1;
        this.total_facturado = 0f;
        clientesComprados = new HashMap();
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
    
    

    class wrapperClientes implements Serializable {
        
        public String id_cliente;
        public int n_compras;
        public int quantidade;

        public wrapperClientes() {
        }

        public wrapperClientes(String id_cliente, int quantidade) {
            this.id_cliente = id_cliente;
            this.quantidade = quantidade;
            this.n_compras = 0;
        }
        
        
        
    }
    
    
    
    

    @Override
    public String toString() {
        return "ComprasProdutos{" + "id_produto=" + id_produto + ", n_compras=" + n_compras + ", produtosComprados=" + clientesComprados + '}';
    }


}

