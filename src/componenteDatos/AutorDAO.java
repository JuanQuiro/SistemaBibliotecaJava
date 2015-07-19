/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import funciones.Banda;
import componenteEntidad.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Enrique
 */
public class AutorDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static AutorDAO instancia;

    public static AutorDAO getInstancia()
    {
        if(instancia==null)
            instancia=new AutorDAO();
        return instancia;
    }
    
    public void insertar(String idautor, String nombre) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO autor (idautor,nombre) VALUES (?,?)");
            ps.setString(1, idautor);
            ps.setString(2, nombre);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Autor buscarAutor(String idautor) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        Autor aut=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM autor WHERE idautor=?");
            ps.setString(1, idautor);
            rs = ps.executeQuery();
            if (rs.next()) {
                String nombre=rs.getString("nombre");

                aut= new Autor(idautor,nombre);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return aut;
    }

    public void actualizar(String idautor, String nombre) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("UPDATE autor SET nombre = ? WHERE idautor=?");
            ps.setString(2, idautor);
            ps.setString(1, nombre);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public ArrayList<Autor> mostrarAutor() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Autor> lista = new ArrayList<Autor>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM autor");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idautor=rs.getString("idautor");
                String nombre=rs.getString("nombre");
                Autor ed= new Autor(idautor,nombre);
                lista.add(ed);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
    
    public ArrayList<Autor> buscarPorIdAutor(String id) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Autor> lista = new ArrayList<Autor>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM autor where idautor = ?");
            ps.setString(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {
                String idautor=rs.getString("idautor");
                String nombre=rs.getString("nombre");
                Autor cli= new Autor(idautor,nombre);
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
    
    public String generaCodigo()throws SQLException{
        String id=null;
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("select CONCAT('A',right(CONCAT('0000',cast(count(*)+1 as char)),4)) from autor");
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
            ps = cnn.prepareStatement("SELECT * FROM autor");
            rs = ps.executeQuery();
            modeloCombo.addElement("Seleccione un Autor");
            dc.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(new Banda(rs.getObject("nombre").toString(),rs.getObject("idautor").toString()));
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
    
    public ArrayList<Autor> buscarPorNombre(String desc) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Autor> lista = new ArrayList<Autor>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM autor where nombre like ?");
            ps.setString(1, desc+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idautor=rs.getString("idautor");
                String nombre=rs.getString("nombre");
                Autor aut= new Autor(idautor,nombre);
                lista.add(aut);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
