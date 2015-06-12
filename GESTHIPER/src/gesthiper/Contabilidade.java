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
import java.util.TreeSet;

/**
 *
 * @author 72
 */
public class Contabilidade {
     public ArrayList<HashMap<String, ComprasProdutos>> hashComprasProdutos;
   private ArrayList<TreeSet<ComprasProdutos>> setComprasProdutos;
  private int comprasMes[];
  private int total_compras;
  private float mes_facturado[];
  private float total_facturado;
  
  public Contabilidade() {
        hashComprasProdutos = new ArrayList();
         setComprasProdutos = new ArrayList();
        comprasMes = new int[13];
        mes_facturado = new float[13];
        total_compras = 0;
        total_facturado = 0;
        int i = 0;
        while (i < 13) {
            comprasMes[i] = 0;
            mes_facturado[i] = 0;
            hashComprasProdutos.add(new HashMap());
             setComprasProdutos.add(new TreeSet(new comprasProdutosComparator()));
            i++;
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
        for (ComprasProdutos value : hashComprasProdutos.get(12).values()) {

            setComprasProdutos.get(12).add(value);
        }

        // System.out.println(setCompras.get(12).toString());
    }
   
   class comprasProdutosComparator implements Comparator<ComprasProdutos>, Serializable {

        @Override
        public int compare(ComprasProdutos c1, ComprasProdutos c2) {

            int res = c2.n_compras - c1.n_compras;
            if (res == 0) {
                res = c2.getId_produto().compareTo(c1.getId_produto());
            }
            return res;
        }
    }
}
