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
public class LibroDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static LibroDAO instancia;

    public static LibroDAO getInstancia()
    {
        if(instancia==null)
            instancia=new LibroDAO();
        return instancia;
    }
    
    public void insertar(Libro libro) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        PreparedStatement ps1=null;
//        PreparedStatement ps2=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO libro " +
                    "(idlibro" +
                    ",titulo" +
                    ",isbn" +
                    ",nroejemplar" +
                    ",nropagina" +
                    ",nroedicion" +
                    ",yearpublica" +
                    ",genero_idgenero" +
                    ",editorial_ideditorial)" +
                    "VALUES (?,?,?,?,?,?,?,?,?)");            
            ps.setString(1, libro.getIdlibro());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getIsbn());
            ps.setInt(4, libro.getNroejemplar());
            ps.setInt(5, libro.getNropagina());
            ps.setInt(6, libro.getNroedicion());
            ps.setString(7, libro.getYearpublica());
            ps.setString(8, libro.getGenero().getIdgenero());
            ps.setString(9, libro.getEditorial().getIdeditorial());
            ps.executeUpdate();
            
            Object datos[][]=libro.getLI().devuelvedatos();
            for(int i=0;i<datos.length;i++){
                ps1=cnn.prepareStatement("INSERT INTO libro_has_autor (libro_idlibro,autor_idautor) VALUES (?,?)");
                
                ps1.setString(1, libro.getIdlibro());
                ps1.setString(2, datos[i][0].toString());
                ps1.executeUpdate();
                
//                ps2=cnn.prepareStatement("UPDATE PRODUCTO set stock=stock - ? where idautor=?");
//                ps2.setInt(1,((Double)datos[i][3]).intValue());
//                ps2.setString(2, datos[i][0].toString());
//                ps2.executeUpdate();
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

    public Libro buscarLibro(String idLibro) throws SQLException
    {
         cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        Libro  ped=null;
        try {
            ps1 = cnn.prepareStatement("SELECT * FROM libro " +
                   "WHERE idlibro=?");
            ps1.setString(1, idLibro);
            rs = ps1.executeQuery();
            if (rs.next()) {
//                String fecha=rs.getString("fecha");
                String titulo=rs.getString("titulo");
                String isbn=rs.getString("isbn");
                int nroejemplar=rs.getInt("nroejemplar");
                int nropagina=rs.getInt("nropagina");
                int nroedicion=rs.getInt("nroedicion");
                String yearpublica=rs.getString("yearpublica");

                String idgenero=rs.getString("idgenero");
                Genero gn = GeneroDAO.getInstancia().buscarGenero(idgenero);
                
                String ideditorial=rs.getString("ideditorial");
                Editorial ed = EditorialDAO.getInstancia().buscarEditorial(ideditorial);
                
                ped= new Libro(idLibro,titulo,isbn,nroejemplar,nropagina,nroedicion,yearpublica,gn,ed);
                
                ps2=cnn.prepareStatement("SELECT * FROM detalledelibro where idlibro=?");
                ps2.setString(1, idLibro);
                rs=ps2.executeQuery();
                while(rs.next())
                {
                    String idautor=rs.getString("idautor");
//                    double precio=rs.getDouble("precio");
//                    int cantidad=rs.getInt("cantidad");
                    Autor aut=AutorDAO.getInstancia().buscarAutor(idautor);
                    ped.registrarItemLibro(aut);

                }
                ps2.close();
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps1.close();
            
        }
        return ped;

    }
    
    public ArrayList<Libro> mostrarLibro() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Libro> lista = new ArrayList<Libro>();
        try {
            ps=cnn.prepareStatement("SELECT l.idlibro idlibro, l.titulo titulo, a.nombre nombre, g.descr descr " +
                                        "FROM libro l " +
                                        "	inner join genero g " +
                                        "		on l.genero_idgenero=g.idgenero " +
                                        "	inner join libro_has_autor d " +
                                        "		on l.idlibro=d.libro_idlibro " +
                                        "	inner join autor a " +
                                        "		on d.autor_idautor=a.idautor");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idlibro=rs.getString("idlibro");
                String titul=rs.getString("titulo");
                String nombre=rs.getString("nombre");
                String descr=rs.getString("descr");
                Libro cli= new Libro(idlibro,titul,nombre,descr);
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
    
    public ArrayList<Libro> buscarPorTitulo(String titulo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Libro> lista = new ArrayList<Libro>();
        try {
            ps=cnn.prepareStatement("SELECT l.idlibro, l.titulo, a.nombre,g.descr " +
                                        "FROM libro l " +
                                        "	inner join genero g " +
                                        "		on l.genero_idgenero=g.idgenero " +
                                        "	inner join libro_has_autor d " +
                                        "		on l.idlibro=d.libro_idlibro " +
                                        "	inner join autor a " +
                                        "		on d.autor_idautor=a.idautor where titulo like ?");
            ps.setString(1, titulo+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String idlibro=rs.getString("idlibro");
                String titul=rs.getString("titulo");
                String nombre=rs.getString("nombre");
                String descr=rs.getString("descr");
                Libro cli= new Libro(idlibro,titul,nombre,0,0,0,descr,null,null);
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
    

    public ArrayList<Libro> mostrarLibros()throws SQLException
    {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ResultSet rs1=null;
        ArrayList<Libro> lista = new ArrayList<Libro>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM libro");
            rs1=ps.executeQuery();
            while (rs1.next()) {
                String idlibro=rs1.getString("idlibro");
                Libro lib=buscarLibro(idlibro);
                lista.add(lib);
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
            ps = cnn.prepareStatement("select CONCAT('L',right(CONCAT('0000000',cast(count(*)+1 as char)),7)) from libro");
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
