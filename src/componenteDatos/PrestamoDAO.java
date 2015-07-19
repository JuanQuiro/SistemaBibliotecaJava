/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteDatos;
import componenteEntidad.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Enrique
 */
public class PrestamoDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static PrestamoDAO instancia;

    public static PrestamoDAO getInstancia()
    {
        if(instancia==null)
            instancia=new PrestamoDAO();
        return instancia;
    }
    
    public void insertar(Prestamo prestamo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        
        try {
            ps = cnn.prepareStatement("INSERT INTO prestamo " +
                    "(idprestamo" +
                    ",idcarnet" +
                    ",fechamaxima)" +
                    "VALUES (?,?,?)");            
            ps.setString(1, prestamo.getIdprestamo());
            ps.setString(2, prestamo.getUsuario().getIdcarnet());
            ps.setString(3, prestamo.getFechamaxima());
            ps.executeUpdate();
            
            Object datos[][]=prestamo.getLI().devuelvedatos();
            for(int i=0;i<datos.length;i++)
            {
                ps1=cnn.prepareStatement("INSERT INTO prestamo_has_libro (prestamo_idprestamo,libro_idlibro,fechadevolucion) VALUES (?,?,'')");
                
                ps1.setString(1, prestamo.getIdprestamo());
                ps1.setString(2, datos[i][0].toString());
                ps1.executeUpdate();
                
            }            
            
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
            ps1.close();
        }
    }

//    public Prestamo buscarPrestamo(String idPrestamo) throws SQLException
//    {
//         cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps1=null;
//        PreparedStatement ps2=null;
//        Prestamo  ped=null;
//        try {
//            ps1 = cnn.prepareStatement("SELECT * FROM prestamo " +
//                   "WHERE idprestamo=?");
//            ps1.setString(1, idPrestamo);
//            rs = ps1.executeQuery();
//            if (rs.next()) {
////                String fecha=rs.getString("fecha");
//                String titulo=rs.getString("titulo");
//                String isbn=rs.getString("isbn");
//                int nroejemplar=rs.getInt("nroejemplar");
//                int nropagina=rs.getInt("nropagina");
//                int nroedicion=rs.getInt("nroedicion");
//                String yearpublica=rs.getString("yearpublica");
//
//                String idgenero=rs.getString("idgenero");
//                Genero gn = GeneroDAO.getInstancia().buscarGenero(idgenero);
//                
//                String ideditorial=rs.getString("ideditorial");
//                Editorial ed = EditorialDAO.getInstancia().buscarEditorial(ideditorial);
//                
//                ped= new Prestamo(idPrestamo,titulo,isbn,nroejemplar,nropagina,nroedicion,yearpublica,gn,ed);
//                
//                ps2=cnn.prepareStatement("SELECT * FROM detalledeprestamo where idprestamo=?");
//                ps2.setString(1, idPrestamo);
//                rs=ps2.executeQuery();
//                while(rs.next())
//                {
//                    String idautor=rs.getString("idautor");
////                    double precio=rs.getDouble("precio");
////                    int cantidad=rs.getInt("cantidad");
//                    Autor aut=AutorDAO.getInstancia().buscarAutor(idautor);
//                    ped.registrarItemPrestamo(aut);
//
//                }
//                ps2.close();
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps1.close();
//            
//        }
//        return ped;
//
//    }
    
//    public ArrayList<Prestamo> mostrarPrestamo() throws SQLException {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
//        try {
//            ps=cnn.prepareStatement("SELECT l.idprestamo idprestamo, l.titulo titulo, a.nombre nombre, g.descr descr " +
//                                        "FROM prestamo l " +
//                                        "	inner join genero g " +
//                                        "		on l.genero_idgenero=g.idgenero " +
//                                        "	inner join prestamo_has_autor d " +
//                                        "		on l.idprestamo=d.prestamo_idprestamo " +
//                                        "	inner join autor a " +
//                                        "		on d.autor_idautor=a.idautor");
//            rs=ps.executeQuery();
//            while (rs.next()) {
//                String idprestamo=rs.getString("idprestamo");
//                String titul=rs.getString("titulo");
//                String nombre=rs.getString("nombre");
//                String descr=rs.getString("descr");
//                Prestamo cli= new Prestamo(idprestamo,titul,nombre,descr);
//                lista.add(cli);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//        return lista;
//    }
    
//    public ArrayList<Prestamo> buscarPorTitulo(String titulo) throws SQLException {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
//        try {
//            ps=cnn.prepareStatement("SELECT l.idprestamo, l.titulo, a.nombre,g.descr " +
//                                        "FROM prestamo l " +
//                                        "	inner join genero g " +
//                                        "		on l.genero_idgenero=g.idgenero " +
//                                        "	inner join prestamo_has_autor d " +
//                                        "		on l.idprestamo=d.prestamo_idprestamo " +
//                                        "	inner join autor a " +
//                                        "		on d.autor_idautor=a.idautor where titulo like ?");
//            ps.setString(1, titulo+"%");
//            rs=ps.executeQuery();
//            while (rs.next()) {
//                String idprestamo=rs.getString("idprestamo");
//                String titul=rs.getString("titulo");
//                String nombre=rs.getString("nombre");
//                String descr=rs.getString("descr");
//                Prestamo cli= new Prestamo(idprestamo,titul,nombre,0,0,0,descr,null,null);
//                lista.add(cli);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//        return lista;
//    }
//    

//    public ArrayList<Prestamo> mostrarPrestamos()throws SQLException
//    {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        ResultSet rs1=null;
//        ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
//        try {
//            ps=cnn.prepareStatement("SELECT * FROM prestamo");
//            rs1=ps.executeQuery();
//            while (rs1.next()) {
//                String idprestamo=rs1.getString("idprestamo");
//                Prestamo lib=buscarPrestamo(idprestamo);
//                lista.add(lib);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//        return lista;
//    }
    
    public String generaCodigo()throws SQLException{
        String id=null;
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("select CONCAT('P',right(CONCAT('00000000',cast(count(*)+1 as char)),8)) from prestamo");
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
