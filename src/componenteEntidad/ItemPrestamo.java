/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteEntidad;

/**
 *
 * @author Enrique
 */
public class ItemPrestamo {
    private Libro libro;
    private String fechadevolucion;

    public ItemPrestamo() {
    }

    public ItemPrestamo(Libro libro, String fechadevolucion) {
        this.libro = libro;
        this.fechadevolucion = fechadevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(String fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPrestamo other = (ItemPrestamo) obj;
        if (this.libro != other.libro && (this.libro == null || !this.libro.equals(other.libro))) {
            return false;
        }
        return true;
    }
}
