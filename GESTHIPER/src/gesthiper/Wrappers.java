/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 72
 */
public class Wrappers implements Serializable{
    
    public static class wrapperQuery1 implements Serializable{
        private ArrayList<String> produtos;
        private int total;

        public wrapperQuery1(ArrayList<String> produtos, int total) {
            this.produtos = produtos;
            this.total = total;
        }
        
        public String getProduto(int i){
            return this.produtos.get(i);
        }
        
        public int getTotal(){
            return this.produtos.size();
        }
        
        
    }
    public static class wrapperQuery2 implements Serializable{
        private ArrayList<String> clientes;
        private int total;

        public wrapperQuery2(ArrayList<String> clientes, int total) {
            this.clientes = clientes;
            this.total = total;
        }
        
        public String getCliente(int i){
            return this.clientes.get(i);
        }
        
        public int getTotal(){
            return this.clientes.size();
        }
    }
    
    public static class wrapperQuery3 implements Serializable{
        private int total_compras;
        private int clientes_distintos;

        public int getTotal_compras() {
            return total_compras;
        }

        public int getClientes_distintos() {
            return clientes_distintos;
        }
        
        

        public wrapperQuery3(int total_compras, int clientes_distintos) {
            this.total_compras = total_compras;
            this.clientes_distintos = clientes_distintos;
        }
        
              
    }
    public static class wrapperQuery4 implements Serializable{
        private int total_compras[];
        private int produtos_distintos[];
        private float facturado_mes[];
        private float total_facturado;

        public wrapperQuery4(int[] total_compras, int[] produtos_distintos, float[] facturado_mes, float total_facturado) {
            this.total_compras = total_compras;
            this.produtos_distintos = produtos_distintos;
            this.facturado_mes = facturado_mes;
            this.total_facturado = total_facturado;
        }
        
        public int getTotalCompras(int i){
            return this.total_compras[i];
        }
        
        public int getProdutos_distintos(int i){
            return this.produtos_distintos[i];
        }
        
        public float getFacturadoMes(int i){
            return this.facturado_mes[i];
        }
        
        public float getTotalFacturado(){
            return this.total_facturado;
        }
          
              
    }
    public static class wrapperQuery5 implements Serializable{
        private int total_compras[];
        private int clientes_distintos[];
        private float facturado_mes[];
        private float total_facturado;

        public wrapperQuery5(int[] total_compras, int[] clientes_distintos, float[] facturado_mes, float total_facturado) {
            this.total_compras = total_compras;
            this.clientes_distintos = clientes_distintos;
            this.facturado_mes = facturado_mes;
            this.total_facturado = total_facturado;
        }
        
        public int getTotalCompras(int i){
            return this.total_compras[i];
        }
        
        public int getProdutos_distintos(int i){
            return this.clientes_distintos[i];
        }
        
        public float getFacturadoMes(int i){
            return this.facturado_mes[i];
        }
        
        public float getTotalFacturado(){
            return this.total_facturado;
        }
        
    }
    
     public static class wrapperQuery6 implements Serializable{
        private int total_n[];
        private int total_p[];        
        private float facturado_p[];
        private float facturado_n[];

        public wrapperQuery6(int[] total_compras, int[] clientes_distintos, float[] facturado_p, float[] facturado_n) {
            this.total_n = total_compras;
            this.total_p = clientes_distintos;
            this.facturado_p = facturado_p;
            this.facturado_n = facturado_n;
        }
        public int getTotalN(int i){
            return this.total_n[i];
        }
        
        public int getTotalP(int i){
            return this.total_p[i];
        }
        
        public float getFacturadoP(int i){
            return this.facturado_p[i];
        }
        
        public float getFacturadoN(int i){
            return this.facturado_n[i];
        }
        
     }
     
     public static class wrapperQuery7 implements Serializable{
         ArrayList <String> listaprodutos;
         int quantos[];

        public wrapperQuery7(ArrayList<String> listaprodutos, int[] quantos) {
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
        }
         public String getProduto(int i){
             return this.listaprodutos.get(i);
         }
         public int getQuantos(int i){
             return this.quantos[i];
         }
         
         public int getTotal(){
             return this.listaprodutos.size();
         }
     }
     
     public static class wrapperQuery8 implements Serializable{
         ArrayList <String> listaprodutos;
         int quantos[];
         int quantidade[];
         int total;

        public wrapperQuery8(ArrayList<String> listaprodutos, int[] quantos ,int [] quantidade, int total) {
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
            this.quantidade = quantidade;
            this.total = total;
        }
        
         public String getProduto(int i){
             return this.listaprodutos.get(i);
         }
         public int getQuantos(int i){
             return this.quantos[i];
         }
         
         public int getQuantidade(int i){
             return this.quantidade[i];
         }
         
         public int getTotal(){
             return this.listaprodutos.size();
         }
         
           public int getTotalT(){
             return this.total;
         }
     }
    
      public static class wrapperQuery9 implements Serializable{
         ArrayList <String> listaprodutos;
         int quantos[];
         int total;

        public wrapperQuery9(ArrayList<String> listaprodutos, int[] quantos,int total) {
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
            this.total = total;
        }
        
        public String getProduto(int i){
             return this.listaprodutos.get(i);
         }
         public int getQuantos(int i){
             return this.quantos[i];
         }
         
         public int getTotal(){
             return this.listaprodutos.size();
         }
         
         public int getTotalT(){
             return this.total;
         }
         
     }
      
      public static class wrapperQuery10 implements Serializable{
         ArrayList <String> listaprodutos;
         int total;
        float quantos[];

        public wrapperQuery10(ArrayList<String> listaprodutos, float[] quantos, int total) {
            this.total = total;
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
        }
          public String getProduto(int i){
             return this.listaprodutos.get(i);
         }
         public float getQuantos(int i){
             return this.quantos[i];
         }
         
         public int getTotal(){
             return this.listaprodutos.size();
         }
         
         public int getTotalT(){
             return this.total;
         }
         
     }
    
}
