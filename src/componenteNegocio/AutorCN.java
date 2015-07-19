/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.AutorDAO;
import componenteEntidad.Autor;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class AutorCN {
    private static AutorCN instancia;

    public static AutorCN getInstancia() {
        if(instancia==null)
            instancia=new AutorCN();
        return instancia;
    }

    public void insertar(String idautor,String nombre) throws SQLException {
        AutorDAO.getInstancia().insertar(idautor,nombre);
    }

    public Autor buscarAutor(String idautor) throws SQLException{
        return AutorDAO.getInstancia().buscarAutor(idautor);
    }

    public void actualizar(String idautor, String nombre) throws SQLException {
        AutorDAO.getInstancia().actualizar(idautor,nombre);
    }

   public ArrayList<Autor> mostrarAutor() throws SQLException {
         return AutorDAO.getInstancia().mostrarAutor();
   }
   
   public ArrayList<Autor> buscarPorIdAutor(String idautor) throws SQLException {
         return AutorDAO.getInstancia().buscarPorIdAutor(idautor);
    }
   
   public String generaCodigo() throws SQLException{
       return AutorDAO.getInstancia().generaCodigo();
   } 
   
   public void llenar_datacombo(javax.swing.JComboBox dc)throws SQLException{
       AutorDAO.getInstancia().llenar_datacombo(dc);
   }
   
   public ArrayList<Autor> buscarPorNombre(String nombre) throws SQLException {
         return AutorDAO.getInstancia().buscarPorNombre(nombre);
    }
}
