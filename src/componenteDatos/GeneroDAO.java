/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;

import funciones.Banda;
import componenteEntidad.Genero;
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
public class GeneroDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static GeneroDAO instancia;

    public static GeneroDAO getInstancia()
    {
        if(instancia==null)
            instancia=new GeneroDAO();
        return instancia;
    }
    
    public void insertar(String idgenero, String descr) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO genero (idgenero,descr) VALUES (?,?)");
            ps.setString(1, idgenero);
            ps.setString(2, descr);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Genero buscarGenero(String idgenero) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        Genero ge=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM genero WHERE idgenero=?");
            ps.setString(1, idgenero);
            rs = ps.executeQuery();
            if (rs.next()) {
                String descr=rs.getString("descr");

                ge= new Genero(idgenero,descr);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return ge;
    }

    public void actualizar(String idgenero, String descr) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("UPDATE genero SET descr = ? WHERE idgenero=?");
            ps.setString(2, idgenero);
            ps.setString(1, descr);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public ArrayList<Genero> mostrarGenero() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Genero> lista = new ArrayList<Genero>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM genero");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idgenero=rs.getString("idgenero");
                String descr=rs.getString("descr");
                Genero ge= new Genero(idgenero,descr);
                lista.add(ge);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
    
    public ArrayList<Genero> buscarPorIdGenero(String id) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Genero> lista = new ArrayList<Genero>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM genero where idgenero = ?");
            ps.setString(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {
                String idgenero=rs.getString("idgenero");
                String descr=rs.getString("descr");
                Genero cli= new Genero(idgenero,descr);
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
            ps = cnn.prepareStatement("select CONCAT('G',right(CONCAT('000',cast(count(*)+1 as char)),3)) from genero");
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
            ps = cnn.prepareStatement("SELECT * FROM genero");
            rs = ps.executeQuery();
            modeloCombo.addElement(new Banda("Seleccione un Genero",""));
            dc.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(new Banda(rs.getObject("descr").toString(),rs.getObject("idgenero").toString()));
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
