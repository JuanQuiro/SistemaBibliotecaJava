/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;
import componenteDatos.*;
import componenteEntidad.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class TipoDocumentoCN {
    private static TipoDocumentoCN instancia;

    public static TipoDocumentoCN getInstancia() {
        if(instancia==null)
            instancia=new TipoDocumentoCN();
        return instancia;
    }

    public void insertar(String iddocumento,String descr) throws SQLException {
        TipoDocumentoDAO.getInstancia().insertar(iddocumento,descr);
    }

    public TipoDocumento buscarTipoDocumento(String iddocumento) throws SQLException{
        return TipoDocumentoDAO.getInstancia().buscarTipoDocumento(iddocumento);
    }

    public void actualizar(String iddocumento, String descr) throws SQLException {
        TipoDocumentoDAO.getInstancia().actualizar(iddocumento,descr);
    }

//    public void eliminar(String idcliente) throws SQLException {
//        TipoDocumentoDAO.getInstancia().eliminar(idcliente);
//    }

   public ArrayList<TipoDocumento> mostrarTipoDocumento() throws SQLException {
         return TipoDocumentoDAO.getInstancia().mostrarTipoDocumento();
   }
   
   public ArrayList<TipoDocumento> buscarPorApellidos(String id) throws SQLException {
         return TipoDocumentoDAO.getInstancia().buscarPorIdDocumento(id);
    }
   
   public String generaCodigo() throws SQLException{
       return TipoDocumentoDAO.getInstancia().generaCodigo();
   } 
   
   public void llenar_datacombo(javax.swing.JComboBox dc)throws SQLException{
       TipoDocumentoDAO.getInstancia().llenar_datacombo(dc);
   }
}
