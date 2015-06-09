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
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author 72
 */
public class CatalogoCompras implements Serializable{
    private ArrayList<HashMap<String,ComprasClientes>> hashCompras;
    private int comprasMes[];
    private ArrayList<TreeSet<ComprasClientes>> setCompras;

    public CatalogoCompras() {
        hashCompras = new ArrayList();
        setCompras = new ArrayList();
        comprasMes = new int[13];
        int i = 0;
        while(i<13){
            comprasMes[i] = 0;
            hashCompras.add(new HashMap());
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
    
    
    
    public void insere_compra(Compra novaCompra){
        
            int mes = novaCompra.getMes() -1;
            HashMap<String,ComprasClientes> aux;
            aux = hashCompras.get(mes);
            HashMap<String,ComprasClientes> aux1;
            aux1 = hashCompras.get(12);
            comprasMes[mes]++;
            if(aux.containsKey(novaCompra.getId_cliente())){
                aux.get(novaCompra.getId_cliente()).setN_compras(aux.get(novaCompra.getId_cliente()).getN_compras()+1);
                aux.get(novaCompra.getId_cliente()).insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade());
            }
            else{
                ComprasClientes novo = new ComprasClientes(novaCompra.getId_cliente());
                novo.insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade());
                aux.put(novaCompra.getId_cliente(), novo);
            }
            if(aux1.containsKey(novaCompra.getId_cliente())){
                aux1.get(novaCompra.getId_cliente()).setN_compras(aux1.get(novaCompra.getId_cliente()).getN_compras()+1);
                aux1.get(novaCompra.getId_cliente()).insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade());
            }
            else{
                ComprasClientes novo = new ComprasClientes(novaCompra.getId_cliente());
                novo.insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade());
                aux1.put(novaCompra.getId_cliente(), novo);
            }
    }
    
    public void preenche_tree(){
        for (ComprasClientes value : hashCompras.get(12).values()) {
    
            setCompras.get(12).add(value);
}
        
        System.out.println(setCompras.get(12).toString());
    }
    
    public void query_2(){
        SortedSet<String> lista;
    }
    
    class comprasClientesComparator implements Comparator<ComprasClientes>, Serializable{
    
    @Override
    public int compare(ComprasClientes c1, ComprasClientes c2){
        
        int res = c1.n_compras - c2.n_compras;
        if(res == 0){
            res = c1.getId_cliente().compareTo(c2.getId_cliente());
        }
        return res;
    }
}

    @Override
    public String toString() {
        return "CatalogoCompras{" + "hashCompras=" + hashCompras + '}';
    }
    
    
    
    
}
