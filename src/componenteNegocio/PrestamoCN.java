/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteNegocio;
import componenteDatos.*;
import componenteEntidad.*;
import java.sql.SQLException;
/**
 *
 * @author Enrique
 */
public class PrestamoCN {
    private static PrestamoCN instancia;

    public static PrestamoCN getInstancia() {
        if(instancia==null)
            instancia=new PrestamoCN();
        return instancia;
    }

    public void insertar(Prestamo prestamo) throws SQLException {
        PrestamoDAO.getInstancia().insertar(prestamo);
    }

//    public Prestamo buscarPrestamo(String idPrestamo)throws SQLException{
//        return PrestamoDAO.getInstancia().buscarPrestamo(idPrestamo);
//    }
//
//    public ArrayList<Prestamo> mostrarPrestamos(String idprestamo) throws SQLException {
//         return PrestamoDAO.getInstancia().mostrarPrestamos();
//   }
//
//   public ArrayList<Prestamo> mostrarPrestamo() throws SQLException {
//         return PrestamoDAO.getInstancia().mostrarPrestamo();
//   }
//   
//   public ArrayList<Prestamo> buscarPorTitulo(String titulo) throws SQLException {
//         return PrestamoDAO.getInstancia().buscarPorTitulo(titulo);
//   }
   
    public String generaCodigo() throws SQLException{
        return PrestamoDAO.getInstancia().generaCodigo();
    }
}
