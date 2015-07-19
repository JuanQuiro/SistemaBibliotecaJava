/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import funciones.Banda;
import componenteEntidad.Editorial;
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
public class EditorialDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static EditorialDAO instancia;

    public static EditorialDAO getInstancia()
    {
        if(instancia==null)
            instancia=new EditorialDAO();
        return instancia;
    }
    
    public void insertar(String ideditorial, String descr) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO editorial (ideditorial,descr) VALUES (?,?)");
            ps.setString(1, ideditorial);
            ps.setString(2, descr);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Editorial buscarEditorial(String ideditorial) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        Editorial ed=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM editorial WHERE ideditorial=?");
            ps.setString(1, ideditorial);
            rs = ps.executeQuery();
            if (rs.next()) {
                String descr=rs.getString("descr");

                ed= new Editorial(ideditorial,descr);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return ed;
    }

    public void actualizar(String ideditorial, String descr) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("UPDATE editorial SET descr = ? WHERE ideditorial=?");
            ps.setString(2, ideditorial);
            ps.setString(1, descr);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public ArrayList<Editorial> mostrarEditorial() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Editorial> lista = new ArrayList<Editorial>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM editorial");
            rs=ps.executeQuery();
            while (rs.next()) {
                String ideditorial=rs.getString("ideditorial");
                String descr=rs.getString("descr");
                Editorial ed= new Editorial(ideditorial,descr);
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
    
    public ArrayList<Editorial> buscarPorIdEditorial(String id) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Editorial> lista = new ArrayList<Editorial>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM editorial where ideditorial = ?");
            ps.setString(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {
                String ideditorial=rs.getString("ideditorial");
                String descr=rs.getString("descr");
                Editorial cli= new Editorial(ideditorial,descr);
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
            ps = cnn.prepareStatement("select CONCAT('E',right(CONCAT('000',cast(count(*)+1 as char)),3)) from editorial");
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
            ps = cnn.prepareStatement("SELECT * FROM editorial");
            rs = ps.executeQuery();
            modeloCombo.addElement("Seleccione un Editorial");
            dc.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(new Banda(rs.getObject("descr").toString(),rs.getObject("ideditorial").toString()));
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
