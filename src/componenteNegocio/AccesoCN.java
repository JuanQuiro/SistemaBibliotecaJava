/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.AccesoDAO;
import componenteEntidad.Acceso;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class AccesoCN {
    private static AccesoCN instancia;

    public static AccesoCN getInstancia() {
        if(instancia==null)
            instancia=new AccesoCN();
        return instancia;
    }

    public void insertar(Acceso acceso) throws SQLException {
        AccesoDAO.getInstancia().insertar(acceso);
    }

    public Acceso buscarAcceso(String idacceso) throws SQLException{
        return AccesoDAO.getInstancia().buscarAcceso(idacceso);
    }

    public void actualizar(Acceso acceso) throws SQLException {
        AccesoDAO.getInstancia().actualizar(acceso);
    }

   public ArrayList<Acceso> mostrarAcceso() throws SQLException {
         return AccesoDAO.getInstancia().mostrarAcceso();
   }
   
   public ArrayList<Acceso> buscarPorNomUsuario(String nomusuario) throws SQLException {
         return AccesoDAO.getInstancia().buscarPorNomUsuario(nomusuario);
    }
   
   public String generaCodigo() throws SQLException{
       return AccesoDAO.getInstancia().generaCodigo();
   }
}
