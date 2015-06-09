/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author 72
 */
public class Compra implements Serializable{
    
    private String id_cliente;
    private String id_produto;
    private int quantidade;
    private float preco;
    private char promocao;
    private int mes;

    public Compra(String id_cliente, String id_produto, int quantidade, float preco, char promocao, int mes) {
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.promocao = promocao;
        this.mes = mes;
    }

    public Compra() {
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_produto() {
        return id_produto;
    }

    public void setId_produto(String id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public char getPromocao() {
        return promocao;
    }

    public void setPromocao(char promocao) {
        this.promocao = promocao;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Compra{" + "id_cliente=" + id_cliente + ", id_produto=" + id_produto + ", quantidade=" + quantidade + ", preco=" + preco + ", promocao=" + promocao + ", mes=" + mes + '}';
    }
    
    
}

class comprasComparator implements Comparator<Compra>, Serializable{
    
    @Override
    public int compare(Compra c1, Compra c2){
        
        int res = c1.getId_cliente().compareTo(c2.getId_cliente());
        
        
        return res;
    }
}
