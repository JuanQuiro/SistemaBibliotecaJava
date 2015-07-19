/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;
import funciones.Banda;
import componenteEntidad.TipoDocumento;
import java.sql.*;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Enrique
 */
public class TipoDocumentoDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static TipoDocumentoDAO instancia;

    public static TipoDocumentoDAO getInstancia()
    {
        if(instancia==null)
            instancia=new TipoDocumentoDAO();
        return instancia;
    }
    
    public void insertar(String iddocumento, String descr) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO tipo_documento (iddocumento,descr) VALUES (?,?)");
            ps.setString(1, iddocumento);
            ps.setString(2, descr);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public TipoDocumento buscarTipoDocumento(String iddocumento) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        TipoDocumento td=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM tipo_documento WHERE iddocumento=?");
            ps.setString(1, iddocumento);
            rs = ps.executeQuery();
            if (rs.next()) {
                String descr=rs.getString("descr");

                td= new TipoDocumento(iddocumento,descr);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return td;
    }

    public void actualizar(String iddocumento, String descr) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("UPDATE tipo_documento SET descr = ? WHERE iddocumento=?");
            ps.setString(2, iddocumento);
            ps.setString(1, descr);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public ArrayList<TipoDocumento> mostrarTipoDocumento() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<TipoDocumento> lista = new ArrayList<TipoDocumento>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM tipo_documento");
            rs=ps.executeQuery();
            while (rs.next()) {
                String iddocumento=rs.getString("iddocumento");
                String descr=rs.getString("descr");
                TipoDocumento td= new TipoDocumento(iddocumento,descr);
                lista.add(td);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
    
    public ArrayList<TipoDocumento> buscarPorIdDocumento(String id) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<TipoDocumento> lista = new ArrayList<TipoDocumento>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM tipo_documento where iddocumento = ?");
            ps.setString(1, id);
            rs=ps.executeQuery();
            while (rs.next()) {
                String iddocumento=rs.getString("iddocumento");
                String descr=rs.getString("descr");
                TipoDocumento cli= new TipoDocumento(iddocumento,descr);
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
//        int numberOfRows=0;
        String id=null;
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("select CONCAT('D',right(CONCAT('0',cast(count(*)+1 as char)),2)) from tipo_documento where iddocumento != 'D00'");
            rs = ps.executeQuery();
            if (rs.next()) {
              id = rs.getString(1);
//              System.out.println("numberOfRows= " + numberOfRows);
//            } else {
//              id='D01';
//              System.out.println("error: could not get the record counts");
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
            ps = cnn.prepareStatement("SELECT * FROM tipo_documento where iddocumento!='D00'");
            rs = ps.executeQuery();
            modeloCombo.addElement("Seleccione un Tipo documento");
            dc.setModel(modeloCombo);
            while (rs.next()) {
                //modeloCombo.addElement(rs.getObject("descr"));
                modeloCombo.addElement(new Banda(rs.getObject("descr").toString(),rs.getObject("iddocumento").toString()));
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
