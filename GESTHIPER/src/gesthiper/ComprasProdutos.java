/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author 72
 */
public class ComprasProdutos implements Serializable {
    private String id_produto;
    public int n_compras;
    private int quantidade;
    private int distintos;
    private int p;
    private int n;
    private HashMap<String,wrapperClientes> clientesComprados;
    float total_facturado;

    public int getDistintos() {
        return distintos;
    }

    public void setDistintos(int distintos) {
        this.distintos = distintos;
    }

    
    public HashMap<String, wrapperClientes> getClientesComprados() {
        return clientesComprados;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
            aux.facturacao+=valor;
        }
        else{
            clientesComprados.put(id_produto, new wrapperClientes(id_produto,quantidade,valor));
            this.distintos++;
           
        }
    }
    
    

    public ComprasProdutos(String id_produto, int n_compras, int quantidade) {
        this.id_produto = id_produto;
        p = 0;
        n = 0;
        this.distintos = 0;
        this.quantidade = quantidade;
        this.n_compras = n_compras;
        this.total_facturado = 0f;
        clientesComprados = new HashMap<>();
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public float getTotal_facturado() {
        return total_facturado;
    }

    public void setTotal_facturado(float total_facturado) {
        this.total_facturado = total_facturado;
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
        public float facturacao;

        public wrapperClientes() {
        }

        public wrapperClientes(String id_cliente, int quantidade, float facturacao) {
            this.id_cliente = id_cliente;
            this.quantidade = quantidade;
            this.facturacao = facturacao;
            this.n_compras = 0;
        }
        
        
        
    }
    
    
    @Override
    public int hashCode() {
 return Arrays.hashCode( new Object[] { this.distintos,this.id_produto,this.n_compras,this.clientesComprados,this.total_facturado,this.n,this.p,this.quantidade } );
 }
    

    @Override
    public String toString() {
        return "ComprasProdutos{" + "id_produto=" + id_produto + ", n_compras=" + n_compras + ", produtosComprados=" + clientesComprados + '}';
    }


}

