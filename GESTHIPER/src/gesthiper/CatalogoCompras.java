/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import gesthiper.ComprasClientes.wrapperProdutos;
import gesthiper.Wrappers.*;
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
       private int comprasMes[];
    private int total_compras;
    private ArrayList<TreeSet<ComprasClientes>> setComprasClientes;
    
   private TreeSet<ComprasClientes> treeDistintos;
    public float total_facturado;
    public float mes_facturado[];

    public CatalogoCompras() {
        hashComprasClientes = new ArrayList();
         setComprasClientes = new ArrayList();
         this.treeDistintos = new TreeSet<>(new comprasClientesDistintosComparator());
        comprasMes = new int[13];
        mes_facturado = new float[13];
        total_compras = 0;
        total_facturado = 0;
        int i = 0;
        while (i < 13) {
            comprasMes[i] = 0;
            mes_facturado[i] = 0;
            hashComprasClientes.add(new HashMap());
             setComprasClientes.add(new TreeSet(new comprasClientesComparator()));
            i++;
        }
    }

    public int[] getComprasMes() {
        return comprasMes;
    }

    public void setComprasMes(int[] comprasMes) {
        this.comprasMes = comprasMes;
    }
    
    public wrapperQuery9 query9(int x){
       
        wrapperQuery9 res9;
        int i = 0;
        ArrayList<String> lista = new ArrayList<>();
        int clientes_distintos[] = new int[x];
        TreeSet compras_aux = treeDistintos;
        Iterator<ComprasClientes> it = compras_aux.descendingIterator();
        while(i<x && it.hasNext()){
            ComprasClientes cc = it.next();
            lista.add(cc.getId_cliente());
            clientes_distintos[i] = cc.getDistintos();
            System.out.println(cc.getId_cliente());
            System.out.println(cc.getDistintos());
            i++;
        }
        res9 = new wrapperQuery9(lista, clientes_distintos, compras_aux.size());
        return res9;
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
    
    

    public void preenche_tree() {
        for (ComprasClientes value : hashComprasClientes.get(12).values()) {

            setComprasClientes.get(12).add(value);
            treeDistintos.add(value);
        }

        // System.out.println(setCompras.get(12).toString());
    }

    public wrapperQuery2 query_2() {
        wrapperQuery2 res2;
        ArrayList<String> lista = new ArrayList();
        TreeSet compras_aux = setComprasClientes.get(12);
        Iterator<ComprasClientes> it = compras_aux.iterator();
        ComprasClientes cc = it.next();
        //System.out.println("pila");
        while (cc.getN_compras() == 0 && it.hasNext()) {

            lista.add(cc.getId_cliente());
            cc = it.next();
        }
        Collections.sort(lista);
        //System.out.println(lista);
        res2 = new wrapperQuery2(lista,lista.size());
        return res2;
    }

    public wrapperQuery3 query3(int mes) {
        
        wrapperQuery3 res3;
        int novo_mes = mes - 1;
        HashMap map = hashComprasClientes.get(novo_mes);
        int n_compras = comprasMes[novo_mes];
        int clientes_distintos = map.size();
        res3= new wrapperQuery3(total_compras, clientes_distintos);
        
        return res3;      
    }
    
    

    public wrapperQuery4 query4(String cliente) {
        wrapperQuery4 res4;
        int i = 0;
        int n_produtos[] = new int[12];
        int n_compras[] = new int[12];
        float facturado[] = new float[12];
        float t_facturado = 0;
        while (i < 12) {
            HashMap<String,ComprasClientes> map = hashComprasClientes.get(i);
            if (map.containsKey(cliente)) {
                n_compras[i] = map.get(cliente).getN_compras();
                n_produtos[i] = map.get(cliente).getProdutosComprados().size();
                facturado[i] = map.get(cliente).total_facturado;
                t_facturado += map.get(cliente).total_facturado;
                
            }
            i++;
        }
        
        res4 = new wrapperQuery4(n_compras, n_produtos, facturado, t_facturado);
        return res4;
    }
    
    public wrapperQuery7 query7(String id_cliente,int x){
        
        wrapperQuery7 res7;
        List<wrapperProdutos> lista = new ArrayList<>();
        for(wrapperProdutos c: hashComprasClientes.get(12).get(id_cliente).getProdutosComprados().values()){
            lista.add(c);
        }
        Collections.sort(lista,new Comparator<wrapperProdutos>(){
            @Override
            public int compare(wrapperProdutos c1, wrapperProdutos c2){
                 int res = c2.quantidade - c1.quantidade;
            if (res == 0) {
                res = c1.id_produto.compareTo(c2.id_produto);
            }
            return res;
                    }
            });
      ArrayList<String> res = new ArrayList<>();
      int i = 0;
      int quantos[] = new int[x];
      while(i<x && i<lista.size()){
          res.add(lista.get(i).getId_produto());
          quantos[i]=lista.get(i).getQuantidade();
          i++;
      }
      res7 = new wrapperQuery7(res, quantos);
      return res7;
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
    
     class comprasClientesDistintosComparator implements Comparator<ComprasClientes>, Serializable {

        @Override
        public int compare(ComprasClientes c1, ComprasClientes c2) {

            int res = c1.getDistintos() - c2.getDistintos();
            if (res == 0) {
                res = c1.getId_cliente().compareTo(c2.getId_cliente());
            }
            return res;
        }
    }
     
     

    @Override
    public String toString() {
        return "CatalogoCompras{" + "hashCompras=" + hashComprasClientes + '}';
    }

}
