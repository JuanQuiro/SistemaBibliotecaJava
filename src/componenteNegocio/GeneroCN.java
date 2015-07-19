/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.GeneroDAO;
import componenteEntidad.Genero;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class GeneroCN {
    private static GeneroCN instancia;

    public static GeneroCN getInstancia() {
        if(instancia==null)
            instancia=new GeneroCN();
        return instancia;
    }

    public void insertar(String idgenero,String descr) throws SQLException {
        GeneroDAO.getInstancia().insertar(idgenero,descr);
    }

    public Genero buscarGenero(String idgenero) throws SQLException{
        return GeneroDAO.getInstancia().buscarGenero(idgenero);
    }

    public void actualizar(String idgenero, String descr) throws SQLException {
        GeneroDAO.getInstancia().actualizar(idgenero,descr);
    }

   public ArrayList<Genero> mostrarGenero() throws SQLException {
         return GeneroDAO.getInstancia().mostrarGenero();
   }
   
   public ArrayList<Genero> buscarPorIdGenero(String idgenero) throws SQLException {
         return GeneroDAO.getInstancia().buscarPorIdGenero(idgenero);
    }
   
   public String generaCodigo() throws SQLException{
       return GeneroDAO.getInstancia().generaCodigo();
   } 
   
   public void llenar_datacombo(javax.swing.JComboBox dc)throws SQLException{
       GeneroDAO.getInstancia().llenar_datacombo(dc);
   }
}
