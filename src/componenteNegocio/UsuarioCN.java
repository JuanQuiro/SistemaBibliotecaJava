/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;
import componenteDatos.UsuarioDAO;
import componenteEntidad.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class UsuarioCN {
    private static UsuarioCN instancia;

    public static UsuarioCN getInstancia() {
        if(instancia==null)
            instancia=new UsuarioCN();
        return instancia;
    }

    public void insertar(Usuario usuario) throws SQLException {
        UsuarioDAO.getInstancia().insertar(usuario);
    }

    public Usuario buscarUsuario(String idcarnet) throws SQLException{
        return UsuarioDAO.getInstancia().buscarUsuario(idcarnet);
    }

    public void actualizar(Usuario usuario) throws SQLException {
        UsuarioDAO.getInstancia().actualizar(usuario);
    }

    public ArrayList<Usuario> mostrarUsuario() throws SQLException {
          return UsuarioDAO.getInstancia().mostrarUsuario();
    }

    public ArrayList<Usuario> buscarPorApellidos(String ape) throws SQLException {
          return UsuarioDAO.getInstancia().buscarPorApellidos(ape);
     }

    public String generaCodigo() throws SQLException{
        return UsuarioDAO.getInstancia().generaCodigo();
    } 
   
    public void llenar_datacombo(javax.swing.JComboBox dc)throws SQLException{
       UsuarioDAO.getInstancia().llenar_datacombo(dc);
   }
}
