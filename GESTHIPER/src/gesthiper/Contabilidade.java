/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import gesthiper.ComprasProdutos.wrapperClientes;
import gesthiper.Wrappers.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author 72
 */
public class Contabilidade {
     public ArrayList<HashMap<String, ComprasProdutos>> hashComprasProdutos;
   private ArrayList<TreeSet<ComprasProdutos>> setComprasProdutos;
   private TreeSet<ComprasProdutos> treeQuantidade;
  private int comprasMes[];
  private int total_compras;
  private float mes_facturado[];
  private float total_facturado;
  
  public Contabilidade() {
        hashComprasProdutos = new ArrayList<>();
         setComprasProdutos = new ArrayList<>();
         treeQuantidade = new TreeSet<>(new comprasProdutosQuantidadeComparator());
        comprasMes = new int[13];
        mes_facturado = new float[13];
        total_compras = 0;
        total_facturado = 0;
        int i = 0;
        while (i < 13) {
            comprasMes[i] = 0;
            mes_facturado[i] = 0;
            hashComprasProdutos.add(new HashMap());
             setComprasProdutos.add(new TreeSet<>(new comprasProdutosComparator()));
            i++;
        }
    }
   public void insere_compra_produto(Compra novaCompra) {

        int mes = novaCompra.getMes() - 1;
        HashMap<String, ComprasProdutos> aux;
        aux = hashComprasProdutos.get(mes);
        HashMap<String, ComprasProdutos> aux1;
        aux1 = hashComprasProdutos.get(12);
        total_facturado += novaCompra.getPreco()*novaCompra.getQuantidade();
        comprasMes[mes]++;
        if (aux.containsKey(novaCompra.getId_produto())) {
            if(novaCompra.getPromocao() == 'P'){
            aux.get(novaCompra.getId_produto()).setP(aux.get(novaCompra.getId_produto()).getP() + 1);
            }
            else{
                aux.get(novaCompra.getId_produto()).setN(aux.get(novaCompra.getId_produto()).getN() + 1);
            }
            
            aux.get(novaCompra.getId_produto()).setQuantidade(aux.get(novaCompra.getId_produto()).getQuantidade()+ novaCompra.getQuantidade());
            aux.get(novaCompra.getId_produto()).setN_compras(aux.get(novaCompra.getId_produto()).getN_compras() + 1);
            aux.get(novaCompra.getId_produto()).insereWrapperCliente(novaCompra.getId_cliente(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
        } else {
            ComprasProdutos novo = new ComprasProdutos(novaCompra.getId_produto(),1,novaCompra.getQuantidade());
            novo.insereWrapperCliente(novaCompra.getId_cliente(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
            aux.put(novaCompra.getId_produto(), novo);
        }
        if (aux1.containsKey(novaCompra.getId_produto())) {
            aux1.get(novaCompra.getId_produto()).setQuantidade(aux1.get(novaCompra.getId_produto()).getQuantidade()+ novaCompra.getQuantidade());
            aux1.get(novaCompra.getId_produto()).setN_compras(aux1.get(novaCompra.getId_produto()).getN_compras() + 1);
            aux1.get(novaCompra.getId_produto()).insereWrapperCliente(novaCompra.getId_cliente(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
        } else {
            ComprasProdutos novo = new ComprasProdutos(novaCompra.getId_produto(),1 ,novaCompra.getQuantidade());
            novo.insereWrapperCliente(novaCompra.getId_cliente(), novaCompra.getQuantidade(),novaCompra.getPreco()*novaCompra.getQuantidade());
            aux1.put(novaCompra.getId_produto(), novo);
        }
    }
   
   public wrapperQuery6 query6(String id_produto){
       wrapperQuery6 res6;
       int n[]  = new int[12];
       float fn[] = new float[12];
       float fp[] = new float[12];
       int p[] = new int[12];
       
       int i = 0;
       while(i<12){
           if(hashComprasProdutos.get(i).containsKey(id_produto)){
               n[i] = hashComprasProdutos.get(i).get(id_produto).getN();
               p[i] = hashComprasProdutos.get(i).get(id_produto).getP();
               fn[i] = hashComprasProdutos.get(i).get(id_produto).getTotal_facturado();
               
               fp[i] = hashComprasProdutos.get(i).get(id_produto).getTotal_facturado();
             
           }
           else{
               n[i] = 0;
               p[i] = 0;
           }
           i++;
       }
       res6 = new wrapperQuery6(n, p, fn, fp);
       return res6;
   }
   
   public void inicia_hash(String id_produto){
       hashComprasProdutos.get(12).put(id_produto,new ComprasProdutos(id_produto,0,0 ));
   }
   
   public void preenche_tree() {
        for (ComprasProdutos value : hashComprasProdutos.get(12).values()) {

            setComprasProdutos.get(12).add(value);
            treeQuantidade.add(value);
        }

        // System.out.println(setCompras.get(12).toString());
    }
   
   public wrapperQuery1 query_1() {
       wrapperQuery1 res1;
        ArrayList<String> lista = new ArrayList();
        TreeSet compras_aux = setComprasProdutos.get(12);
        Iterator<ComprasProdutos> it = compras_aux.iterator();
        ComprasProdutos cc = it.next();
        while (cc.getN_compras()== 0 && it.hasNext()) {
            
            lista.add(cc.getId_produto());
            cc = it.next();
        }
        Collections.sort(lista);
        res1 = new wrapperQuery1(lista, total_compras);
        return res1;
    }
   
   public wrapperQuery8 query8(int x){
       wrapperQuery8 res8;
        int i = 0;
        ArrayList<String> lista = new ArrayList<>();
        int clientes_distintos[] = new int[x];
        int quantidade[] = new int[x];
        TreeSet compras_aux = treeQuantidade;
        Iterator<ComprasProdutos> it = compras_aux.descendingIterator();
        while(i<x && it.hasNext()){
            ComprasProdutos cc = it.next();
            lista.add(cc.getId_produto());
            clientes_distintos[i] = cc.getClientesComprados().size();
            quantidade[i] = cc.getQuantidade();
            i++;
        }
        res8 = new wrapperQuery8(lista, clientes_distintos,quantidade,compras_aux.size());
        return res8;
    }
   
   public wrapperQuery10 query10(String id_cliente, int x){
        wrapperQuery10 res10;
        List<wrapperClientes> lista = new ArrayList<>();
        for(wrapperClientes c: hashComprasProdutos.get(12).get(id_cliente).getClientesComprados().values()){
            lista.add(c);
        }
        Collections.sort(lista,new Comparator<wrapperClientes>(){
            @Override
            public int compare(wrapperClientes c1, wrapperClientes c2){
                 int res = c2.quantidade - c1.quantidade;
            if (res == 0) {
                res = c1.id_cliente.compareTo(c2.id_cliente);
            }
            return res;
                    }
            });
        
        int i = 0;
        ArrayList<String> aux= new ArrayList<>();
        float facturacao[] = new float[x];
        while(i<x && i <lista.size()){
            aux.add(lista.get(i).id_cliente);
           
             facturacao[i]=lista.get(i).facturacao;
            i++;
        }
      res10 = new wrapperQuery10(aux, facturacao,lista.size());
      return res10;
    }
   
   public wrapperQuery5 query5(String produto) {
       wrapperQuery5 res5;
        int i = 0;
        int n_produtos[] = new int[12];
        int n_compras[] = new int[12];
        float facturado[] = new float[12];
        float t_facturado = 0;
        while (i < 12) {
            HashMap<String,ComprasProdutos> map = hashComprasProdutos.get(i);
            if (map.containsKey(produto)) {
                n_compras[i] = map.get(produto).getN_compras();
                n_produtos[i] = map.get(produto).getClientesComprados().size();
                facturado[i] = map.get(produto).total_facturado;
                t_facturado += map.get(produto).total_facturado;
            }
            i++;
        }
res5 = new wrapperQuery5(n_compras, n_produtos, facturado, t_facturado);
return res5;
    }
   
   class comprasProdutosComparator implements Comparator<ComprasProdutos>, Serializable {

        @Override
        public int compare(ComprasProdutos c1, ComprasProdutos c2) {

            int res = c1.n_compras - c2.n_compras;
            if (res == 0) {
                res = c1.getId_produto().compareTo(c2.getId_produto());
            }
            return res;
        }
    }
   
   class comprasProdutosQuantidadeComparator implements Comparator<ComprasProdutos>, Serializable {

        @Override
        public int compare(ComprasProdutos c1, ComprasProdutos c2) {

            int res = c1.getQuantidade() - c2.getQuantidade();
            if (res == 0) {
                res = c1.getId_produto().compareTo(c2.getId_produto());
            }
            return res;
        }
    }
}
