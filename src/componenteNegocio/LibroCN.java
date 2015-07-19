/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;
import componenteDatos.*;
import componenteEntidad.*;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Enrique
 */
public class LibroCN {
    private static LibroCN instancia;

    public static LibroCN getInstancia() {
        if(instancia==null)
            instancia=new LibroCN();
        return instancia;
    }

    public void insertar(Libro ped) throws SQLException {
        LibroDAO.getInstancia().insertar(ped);
    }

    public Libro buscarLibro(String idLibro)throws SQLException{
        return LibroDAO.getInstancia().buscarLibro(idLibro);
    }

    public ArrayList<Libro> mostrarLibros(String idlibro) throws SQLException {
         return LibroDAO.getInstancia().mostrarLibros();
   }

   public ArrayList<Libro> mostrarLibro() throws SQLException {
         return LibroDAO.getInstancia().mostrarLibro();
   }
   
   public ArrayList<Libro> buscarPorTitulo(String titulo) throws SQLException {
         return LibroDAO.getInstancia().buscarPorTitulo(titulo);
   }
   
   public String generaCodigo() throws SQLException{
        return LibroDAO.getInstancia().generaCodigo();
    }
}
