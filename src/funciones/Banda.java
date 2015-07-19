/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

/**
 *
 * @author Enrique
 */
public class Banda {
    private String nombre; //1
    private String id;
 
    public Banda(String nombre, String id){ //2
        this.nombre=nombre;
        this.id=id;
    }
 
    public String getId(){ //3
        return id;
    }
    
    
 
    @Override
    public String toString(){ //4
        return nombre;
    }
}
