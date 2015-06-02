/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 72
 */
public class CatalogoCompras {
    private ArrayList<HashMap<String,ComprasClientes>> hashCompras;

    public CatalogoCompras() {
        hashCompras = new ArrayList();
        int i = 0;
        while(i<12){
            hashCompras.add(new HashMap());
            i++;
        }
    }
    
    public void insere_compra(Compra novaCompra){
        
            int mes = novaCompra.getMes() -1;
            HashMap<String,ComprasClientes> aux;
            aux = hashCompras.get(mes);
            if(aux.containsKey(novaCompra.getId_cliente())){
                aux.get(novaCompra.getId_cliente()).setN_compras(aux.get(novaCompra.getId_cliente()).getN_compras()+1);
                aux.get(novaCompra.getId_cliente()).insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade());
            }
            else{
                ComprasClientes novo = new ComprasClientes(novaCompra.getId_cliente());
                novo.insereWrapperProduto(novaCompra.getId_produto(), novaCompra.getQuantidade());
                aux.put(novaCompra.getId_cliente(), novo);
            }
    }

    @Override
    public String toString() {
        return "CatalogoCompras{" + "hashCompras=" + hashCompras + '}';
    }
    
    
    
    
}
