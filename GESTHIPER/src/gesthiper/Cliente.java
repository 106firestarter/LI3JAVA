/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author 72
 */
public class Cliente implements Serializable{
    
    private String id;

    public Cliente(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
 return Arrays.hashCode( new Object[] { this.id } );
 }
    

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + '}';
    }
    
    
}
