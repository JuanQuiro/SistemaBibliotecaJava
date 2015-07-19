/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;
import componenteEntidad.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Enrique
 */
public class AccesoDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static AccesoDAO instancia;

    public static AccesoDAO getInstancia()
    {
        if(instancia==null)
            instancia=new AccesoDAO();
        return instancia;
    }

    public void insertar(Acceso acceso)throws SQLException{
            //String idacceso, String nomusuario, String password,String usuario_idcarnet,TipoDocumento iddocumento,String nrodocumento,Date fechanaci,String direccion, String telefono, String celular,String email, TipoAcceso idtipoacceso) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO acceso " +
                    "(idacceso" +
                    ",nomusuario" +
                    ",password" +
                    ",usuario_idcarnet)" +
                    "VALUES (?,?,?,?)");
            ps.setString(1, acceso.getIdacceso());
            ps.setString(2, acceso.getNomusuario());
            ps.setString(3, acceso.getPassword());
            ps.setString(4, acceso.getUsuario().getIdcarnet());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Acceso buscarAcceso(String idacceso) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        Acceso acc=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM acceso " +
                   "WHERE idacceso=?");
            ps.setString(1, idacceso);
            rs = ps.executeQuery();
            if (rs.next()) {
                String nomusuario=rs.getString("nomusuario");
                String password=rs.getString("password");
                
                String usuario_idcarnet=rs.getString("usuario_idcarnet");
                Usuario usu = UsuarioDAO.getInstancia().buscarUsuario(usuario_idcarnet);
                        
                acc= new Acceso(idacceso,nomusuario,password,usu);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return acc;
    }

    public void actualizar(Acceso acceso)throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("UPDATE acceso " +
                    "SET nomusuario = ?," +
                    "password = ?" +
                    "WHERE idacceso=?");
            ps.setString(3, acceso.getIdacceso());
            ps.setString(1, acceso.getNomusuario());
            ps.setString(2, acceso.getPassword());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

//    public void eliminar(String idcliente) throws SQLException {
//
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        try {
//            ps = cnn.prepareStatement("DELETE FROM cliente " +
//                   "WHERE idcliente=?");
//            ps.setString(1, idcliente);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//
//    }

    public ArrayList<Acceso> mostrarAcceso() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Acceso> lista = new ArrayList<Acceso>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM Acceso");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idacceso=rs.getString("idacceso");
                String nomusuario=rs.getString("nomusuario");
                String password=rs.getString("password");
                
                String usuario_idcarnet=rs.getString("usuario_idcarnet");
                Usuario usu = UsuarioDAO.getInstancia().buscarUsuario(usuario_idcarnet);

                Acceso cli= new Acceso(idacceso,nomusuario,password,usu);
                lista.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
    
    public ArrayList<Acceso> buscarPorNomUsuario(String nomusu) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Acceso> lista = new ArrayList<Acceso>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM acceso where nomusuario = ?");
            ps.setString(1, nomusu);
            rs=ps.executeQuery();
            while (rs.next()) {
                String idacceso=rs.getString("idacceso");
                String nomusuario=rs.getString("nomusuario");
                String password=rs.getString("password");
                
                String usuario_idcarnet=rs.getString("usuario_idcarnet");
                Usuario usu = UsuarioDAO.getInstancia().buscarUsuario(usuario_idcarnet);

                Acceso acc= new Acceso(idacceso,nomusuario,password,usu);
                lista.add(acc);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
    
    public String generaCodigo()throws SQLException{
        String id=null;
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("select CONCAT('U',right(CONCAT('000',cast(count(*)+1 as char)),3)) from acceso where idacceso != 'U000'");
            rs = ps.executeQuery();
            if (rs.next()) {
              id = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        
        return  id;
    }
    
    
}
