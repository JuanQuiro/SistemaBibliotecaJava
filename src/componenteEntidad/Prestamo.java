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
public class Prestamo {
    private String idprestamo;
    private Usuario usuario;
    private String fechamaxima;
    private ListaItemPrestamo LI;

    public Prestamo() {
        LI=new ListaItemPrestamo();
    }

    public Prestamo(String idprestamo, Usuario usuario, String fechamaxima) {
        this.idprestamo = idprestamo;
        this.usuario = usuario;
        this.fechamaxima = fechamaxima;
        //no olvides colocar esto para el detalle..
        LI=new ListaItemPrestamo();
        
    }
    
    public ListaItemPrestamo getLI() {
        return LI;
    }
    
    public void setLI(ListaItemPrestamo LI) {
        this.LI = LI;
    }
    
    public String getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(String idprestamo) {
        this.idprestamo = idprestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFechamaxima() {
        return fechamaxima;
    }

    public void setFechamaxima(String fechamaxima) {
        this.fechamaxima = fechamaxima;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestamo other = (Prestamo) obj;
        if ((this.idprestamo == null) ? (other.idprestamo != null) : !this.idprestamo.equals(other.idprestamo)) {
            return false;
        }
        return true;
    }

    public void registrarItemPrestamo(Libro libro, String fechadevolucion)
    {
        ItemPrestamo item=new ItemPrestamo(libro,fechadevolucion);
        LI.agregar(item);
    }
}
