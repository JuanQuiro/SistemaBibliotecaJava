/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.EditorialDAO;
import componenteEntidad.Editorial;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class EditorialCN {
    private static EditorialCN instancia;

    public static EditorialCN getInstancia() {
        if(instancia==null)
            instancia=new EditorialCN();
        return instancia;
    }

    public void insertar(String ideditorial,String descr) throws SQLException {
        EditorialDAO.getInstancia().insertar(ideditorial,descr);
    }

    public Editorial buscarEditorial(String ideditorial) throws SQLException{
        return EditorialDAO.getInstancia().buscarEditorial(ideditorial);
    }

    public void actualizar(String ideditorial, String descr) throws SQLException {
        EditorialDAO.getInstancia().actualizar(ideditorial,descr);
    }

   public ArrayList<Editorial> mostrarEditorial() throws SQLException {
         return EditorialDAO.getInstancia().mostrarEditorial();
   }
   
   public ArrayList<Editorial> buscarPorIdEditorial(String ideditorial) throws SQLException {
         return EditorialDAO.getInstancia().buscarPorIdEditorial(ideditorial);
    }
   
   public String generaCodigo() throws SQLException{
       return EditorialDAO.getInstancia().generaCodigo();
   } 
   
   public void llenar_datacombo(javax.swing.JComboBox dc)throws SQLException{
       EditorialDAO.getInstancia().llenar_datacombo(dc);
   }
}
