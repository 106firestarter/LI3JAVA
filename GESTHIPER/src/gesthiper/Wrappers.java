/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.util.ArrayList;

/**
 *
 * @author 72
 */
public class Wrappers {
    
    public static class wrapperQuery1{
        private ArrayList<String> produtos;
        private int total;

        public wrapperQuery1(ArrayList<String> produtos, int total) {
            this.produtos = produtos;
            this.total = total;
        }
        
        
    }
    public static class wrapperQuery2{
        private ArrayList<String> clientes;
        private int total;

        public wrapperQuery2(ArrayList<String> clientes, int total) {
            this.clientes = clientes;
            this.total = total;
        }
    }
    
    public static class wrapperQuery3{
        private int total_compras;
        private int clientes_distintos;

        public wrapperQuery3(int total_compras, int clientes_distintos) {
            this.total_compras = total_compras;
            this.clientes_distintos = clientes_distintos;
        }
        
              
    }
    public static class wrapperQuery4{
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
          
              
    }
    public static class wrapperQuery5{
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
        
    }
    
     public static class wrapperQuery6{
        private int total_compras[];
        private int clientes_distintos[];        
        private float facturado_p[];
        private float facturado_n[];

        public wrapperQuery6(int[] total_compras, int[] clientes_distintos, float[] facturado_p, float[] facturado_n) {
            this.total_compras = total_compras;
            this.clientes_distintos = clientes_distintos;
            this.facturado_p = facturado_p;
            this.facturado_n = facturado_n;
        }
        
        
     }
     
     public static class wrapperQuery7{
         ArrayList <String> listaprodutos;
         int quantos[];

        public wrapperQuery7(ArrayList<String> listaprodutos, int[] quantos) {
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
        }
         
         
     }
     
     public static class wrapperQuery8{
         ArrayList <String> listaprodutos;
         int quantos[];

        public wrapperQuery8(ArrayList<String> listaprodutos, int[] quantos) {
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
        }
         
     }
    
      public static class wrapperQuery9{
         ArrayList <String> listaprodutos;
         int quantos[];

        public wrapperQuery9(ArrayList<String> listaprodutos, int[] quantos) {
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
        }
         
     }
      
      public static class wrapperQuery10{
         ArrayList <String> listaprodutos;
        float quantos[];

        public wrapperQuery10(ArrayList<String> listaprodutos, float[] quantos) {
            this.listaprodutos = listaprodutos;
            this.quantos = quantos;
        }
         
     }
    
}
