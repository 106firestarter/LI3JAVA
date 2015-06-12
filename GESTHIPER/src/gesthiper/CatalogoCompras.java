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
import java.util.*;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author 72
 */
public class CatalogoCompras implements Serializable {

    private ArrayList<HashMap<String, ComprasClientes>> hashComprasClientes;
     public ArrayList<HashMap<String, ComprasProdutos>> hashComprasProdutos;
    private int comprasMes[];
    private int total_compras;
    private ArrayList<TreeSet<ComprasClientes>> setCompras;
    public float total_facturado;
    public float mes_facturado[];

    public CatalogoCompras() {
        hashComprasClientes = new ArrayList();
        hashComprasProdutos = new ArrayList();
        setCompras = new ArrayList();
        comprasMes = new int[13];
        mes_facturado = new float[13];
        total_compras = 0;
        total_facturado = 0;
        int i = 0;
        while (i < 13) {
            comprasMes[i] = 0;
            mes_facturado[i] = 0;
            hashComprasClientes.add(new HashMap());
            hashComprasProdutos.add(new HashMap());
            setCompras.add(new TreeSet(new comprasClientesComparator()));
            i++;
        }
    }

    public int[] getComprasMes() {
        return comprasMes;
    }

    public void setComprasMes(int[] comprasMes) {
        this.comprasMes = comprasMes;
    }

    public void insere_compra_cliente(Compra novaCompra) {

        int mes = novaCompra.getMes() - 1;
        HashMap<String, ComprasClientes> aux;
        aux = hashComprasClientes.get(mes);
        HashMap<String, ComprasClientes> aux1;
        aux1 = hashComprasClientes.get(12);
        mes_facturado[mes] += novaCompra.getPreco()*novaCompra.getQuantidade();
        total_facturado += novaCompra.getPreco()*novaCompra.getQuantidade();
        comprasMes[mes]++;
        total_compras++;
        if (aux.containsKey(novaCompra.getId_cliente())) {
            aux.get(novaCompra.getId_cliente()).setN_compras(aux.get(novaCompra.getId_cliente()).getN_compras() + 1);
            aux.get(novaCompra.getId_cliente()).insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
        } else {
            ComprasClientes novo = new ComprasClientes(novaCompra.getId_cliente());
            novo.insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
            aux.put(novaCompra.getId_cliente(), novo);
        }
        if (aux1.containsKey(novaCompra.getId_cliente())) {
            aux1.get(novaCompra.getId_cliente()).setN_compras(aux1.get(novaCompra.getId_cliente()).getN_compras() + 1);
            aux1.get(novaCompra.getId_cliente()).insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
        } else {
            ComprasClientes novo = new ComprasClientes(novaCompra.getId_cliente());
            novo.insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
            aux1.put(novaCompra.getId_cliente(), novo);
        }
    }
    
    public void insere_compra_produto(Compra novaCompra) {

        int mes = novaCompra.getMes() - 1;
        HashMap<String, ComprasProdutos> aux;
        aux = hashComprasProdutos.get(mes);
        HashMap<String, ComprasProdutos> aux1;
        aux1 = hashComprasProdutos.get(12);
       // total_facturado += novaCompra.getPreco()*novaCompra.getQuantidade();
        comprasMes[mes]++;
        if (aux.containsKey(novaCompra.getId_cliente())) {
            aux.get(novaCompra.getId_produto()).setN_compras(aux.get(novaCompra.getId_produto()).getN_compras() + 1);
            aux.get(novaCompra.getId_produto()).insereWrapperCliente(novaCompra.getId_produto(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
        } else {
            ComprasProdutos novo = new ComprasProdutos(novaCompra.getId_produto());
            novo.insereWrapperCliente(novaCompra.getId_cliente(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
            aux.put(novaCompra.getId_produto(), novo);
        }
        if (aux1.containsKey(novaCompra.getId_cliente())) {
            aux1.get(novaCompra.getId_produto()).setN_compras(aux1.get(novaCompra.getId_produto()).getN_compras() + 1);
            aux1.get(novaCompra.getId_produto()).insereWrapperCliente(novaCompra.getId_produto(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
        } else {
            ComprasProdutos novo = new ComprasProdutos(novaCompra.getId_produto());
            novo.insereWrapperCliente(novaCompra.getId_cliente(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
            aux1.put(novaCompra.getId_produto(), novo);
        }
    }

    public void preenche_tree() {
        for (ComprasClientes value : hashComprasClientes.get(12).values()) {

            setCompras.get(12).add(value);
        }

        // System.out.println(setCompras.get(12).toString());
    }

    public ArrayList<String> query_2() {
        ArrayList<String> lista = new ArrayList();
        TreeSet compras_aux = setCompras.get(12);
        Iterator<ComprasClientes> it = compras_aux.iterator();
        ComprasClientes cc = it.next();
        //System.out.println("pila");
        while (cc.getN_compras() == 0 && it.hasNext()) {

            lista.add(cc.getId_cliente());
            cc = it.next();
        }
        Collections.sort(lista);
        //System.out.println(lista);
        return lista;
    }

    public void query3(int mes) {

        int novo_mes = mes - 1;
        HashMap map = hashComprasClientes.get(novo_mes);
        int n_compras = comprasMes[novo_mes];
        int clientes_distintos = map.size();
        System.out.println(n_compras);
                System.out.println(clientes_distintos);
    }

    public void query4(String cliente) {

        int i = 0;
        int n_produtos;
        int n_compras;
        float facturado;
        float t_facturado = 0;
        while (i < 12) {
            HashMap<String,ComprasClientes> map = hashComprasClientes.get(i);
            if (map.containsKey(cliente)) {
                n_compras = map.get(cliente).getN_compras();
                n_produtos = map.get(cliente).getProdutosComprados().size();
                facturado = map.get(cliente).total_facturado;
                t_facturado += map.get(cliente).total_facturado;
            }
        }

    }

    class comprasClientesComparator implements Comparator<ComprasClientes>, Serializable {

        @Override
        public int compare(ComprasClientes c1, ComprasClientes c2) {

            int res = c2.n_compras - c1.n_compras;
            if (res == 0) {
                res = c2.getId_cliente().compareTo(c1.getId_cliente());
            }
            return res;
        }
    }

    @Override
    public String toString() {
        return "CatalogoCompras{" + "hashCompras=" + hashComprasClientes + '}';
    }

}
