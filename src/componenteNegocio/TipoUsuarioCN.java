/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;

import componenteDatos.TipoDocumentoDAO;
import componenteDatos.TipoUsuarioDAO;
import componenteEntidad.TipoDocumento;
import componenteEntidad.TipoUsuario;
import java.sql.SQLException;

/**
 *
 * @author Enrique
 */
public class TipoUsuarioCN {
    private static TipoUsuarioCN instancia;

    public static TipoUsuarioCN getInstancia() {
        if(instancia==null)
            instancia=new TipoUsuarioCN();
        return instancia;
    }
    
    public TipoUsuario buscarTipoUsuario(String idtipousuario) throws SQLException{
        return TipoUsuarioDAO.getInstancia().buscarTipoUsuario(idtipousuario);
    }
    
    public void llenar_datacombo(javax.swing.JComboBox dc)throws SQLException{
       TipoUsuarioDAO.getInstancia().llenar_datacombo(dc);
   }
}
