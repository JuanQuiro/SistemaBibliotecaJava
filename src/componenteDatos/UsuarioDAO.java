/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;
import componenteEntidad.*;
import funciones.Banda;
import java.sql.*;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
/***
 *
 * @author Enrique
 */
public class UsuarioDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static UsuarioDAO instancia;

    public static UsuarioDAO getInstancia()
    {
        if(instancia==null)
            instancia=new UsuarioDAO();
        return instancia;
    }

    public void insertar(Usuario usuario)throws SQLException{
            //String idcarnet, String apepat, String apemat,String nombres,TipoDocumento iddocumento,String nrodocumento,Date fechanaci,String direccion, String telefono, String celular,String email, TipoUsuario idtipousuario) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO usuario " +
                    "(idcarnet" +
                    ",apepat" +
                    ",apemat" +
                    ",nombres" +
                    ",iddocumento" +
                    ",nrodocumento" +
                    ",fechanaci" +
                    ",direccion" +
                    ",telefono" +
                    ",celular" +
                    ",email" +
                    ",idtipousuario)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, usuario.getIdcarnet());
            ps.setString(2, usuario.getApepat());
            ps.setString(3, usuario.getApemat());
            ps.setString(4, usuario.getNombres());
            ps.setString(5, usuario.getTipoDocumento().getIddocumento());
            ps.setString(6, usuario.getNrodocumento());
            ps.setString(7, usuario.getFechanaci());
            ps.setString(8, usuario.getDireccion());
            ps.setString(9, usuario.getTelefono());
            ps.setString(10, usuario.getCelular());
            ps.setString(11, usuario.getEmail());
            ps.setString(12, usuario.getTipoUsuario().getIdtipousuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Usuario buscarUsuario(String idcarnet) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        Usuario usu=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM usuario " +
                   "WHERE idcarnet=?");
            ps.setString(1, idcarnet);
            rs = ps.executeQuery();
            if (rs.next()) {
                String apepat=rs.getString("apepat");
                String apemat=rs.getString("apemat");
                String nombres=rs.getString("nombres");
                
                String iddocumento=rs.getString("iddocumento");
                TipoDocumento td = TipoDocumentoDAO.getInstancia().buscarTipoDocumento(iddocumento);

                String nrodocumento=rs.getString("nrodocumento");
                String fechanaci=rs.getString("fechanaci");
                String direccion=rs.getString("direccion");
                String telefono=rs.getString("telefono");
                String celular=rs.getString("celular");
                String email=rs.getString("email");
                
                String idtipousuario=rs.getString("idtipousuario");
                
                TipoUsuario tu = TipoUsuarioDAO.getInstancia().buscarTipoUsuario(idtipousuario);
                        
                usu= new Usuario(idcarnet,apepat,apemat,nombres,td,nrodocumento,fechanaci,direccion,telefono,celular,email,tu);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return usu;
    }

    public void actualizar(Usuario usuario)throws SQLException {
            
            //String idcliente, String apellidos, String nombres, String direccion,String telefono) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("UPDATE usuario " +
                    "SET apepat = ?," +
                    "apemat = ?," +
                    "nombres = ?," +
                    "iddocumento = ?," +
                    "nrodocumento = ?," +
                    "fechanaci = ?," +
                    "direccion = ?," +
                    "telefono = ?," +
                    "celular = ?," +
                    "email = ?," +
                    "idtipousuario = ? " +
                    "WHERE idcarnet=?");
            ps.setString(12, usuario.getIdcarnet());
            ps.setString(1, usuario.getApepat());
            ps.setString(2, usuario.getApemat());
            ps.setString(3, usuario.getNombres());
            ps.setString(4, usuario.getTipoDocumento().getIddocumento());
            ps.setString(5, usuario.getNrodocumento());
            ps.setString(6, usuario.getFechanaci());
            ps.setString(7, usuario.getDireccion());
            ps.setString(8, usuario.getTelefono());
            ps.setString(9, usuario.getCelular());
            ps.setString(10, usuario.getEmail());
            ps.setString(11, usuario.getTipoUsuario().getIdtipousuario());

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

    public ArrayList<Usuario> mostrarUsuario() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM Usuario where idcarnet!='C000000000'");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idcarnet=rs.getString("idcarnet");
                String apepat=rs.getString("apepat");
                String apemat=rs.getString("apemat");
                String nombres=rs.getString("nombres");
                
                String iddocumento=rs.getString("iddocumento");
                TipoDocumento td = TipoDocumentoDAO.getInstancia().buscarTipoDocumento(iddocumento);
                
                String nrodocumento=rs.getString("nrodocumento");
                String fechanaci=rs.getString("fechanaci");
                String direccion=rs.getString("direccion");
                String telefono=rs.getString("telefono");
                String celular=rs.getString("celular");
                String email=rs.getString("email");
                
                String idtipousuario=rs.getString("idtipousuario");
                TipoUsuario tu = TipoUsuarioDAO.getInstancia().buscarTipoUsuario(idtipousuario);
   
                Usuario cli= new Usuario(idcarnet,apepat,apemat,nombres,td,nrodocumento,fechanaci,direccion,telefono,celular,email,tu);
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
    
    public ArrayList<Usuario> buscarPorApellidos(String ape) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM usuario where concat( apepat ,' ', apemat) like ?  and idcarnet!='C000000000'");
            ps.setString(1, ape+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idcarnet=rs.getString("idcarnet");
                String apepat=rs.getString("apepat");
                String apemat=rs.getString("apemat");
                String nombres=rs.getString("nombres");
                
                String iddocumento=rs.getString("iddocumento");
                TipoDocumento td = TipoDocumentoDAO.getInstancia().buscarTipoDocumento(iddocumento);
                
                String nrodocumento=rs.getString("nrodocumento");
                String fechanaci=rs.getString("fechanaci");
                String direccion=rs.getString("direccion");
                String telefono=rs.getString("telefono");
                String celular=rs.getString("celular");
                String email=rs.getString("email");
                
                String idtipousuario=rs.getString("idtipousuario");
                TipoUsuario tu = TipoUsuarioDAO.getInstancia().buscarTipoUsuario(idtipousuario);
                
                Usuario usu= new Usuario(idcarnet,apepat,apemat,nombres,td,nrodocumento,fechanaci,direccion,telefono,celular,email,tu);
                lista.add(usu);
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
            ps = cnn.prepareStatement("select CONCAT('C',right(CONCAT('000000000',cast(count(*)+1 as char)),9)) from usuario where idcarnet != 'C000000000'");
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
    
    public void llenar_datacombo(javax.swing.JComboBox dc)throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        dc.removeAllItems();
        try {
            ps = cnn.prepareStatement("SELECT idcarnet, concat(apepat,' ' ,apemat, ', ', nombres) nombre FROM usuario where idcarnet!='C000000000' and idtipousuario = '01';");
            rs = ps.executeQuery();
            modeloCombo.addElement("Seleccione una Persona");
            dc.setModel(modeloCombo);
            while (rs.next()) {
                //modeloCombo.addElement(rs.getObject("descr"));
                modeloCombo.addElement(new Banda(rs.getObject("nombre").toString(),rs.getObject("idcarnet").toString()));
                dc.setModel(modeloCombo);
            }
            cnn.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }
}
