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
public class Libro {
    private String idlibro;
    private String titulo;
    private String isbn;
    private int nroejemplar;
    private int nropagina;
    private int nroedicion;
    private String yearpublica;
    private Genero genero;
    private Editorial editorial;
    private ListaItemLibro LI;

    public Libro() {
        LI=new ListaItemLibro();
    }

    public Libro(String idlibro, String titulo, String isbn, int nroejemplar, int nropagina, int nroedicion, String yearpublica, Genero genero, Editorial editorial) {
        this.idlibro = idlibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.nroejemplar = nroejemplar;
        this.nropagina = nropagina;
        this.nroedicion = nroedicion;
        this.yearpublica = yearpublica;
        this.genero = genero;
        this.editorial = editorial;
        //no olvides colocar esto para el detalle..
        LI=new ListaItemLibro();
    }
    
    public Libro(String idlibro, String titulo, String isbn, String yearpublica) {
        this.idlibro = idlibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.yearpublica = yearpublica;
    }
    
    public ListaItemLibro getLI() {
        return LI;
    }
    
    public void setLI(ListaItemLibro LI) {
        this.LI = LI;
    }
    
    public String getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(String idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNroejemplar() {
        return nroejemplar;
    }

    public void setNroejemplar(int nroejemplar) {
        this.nroejemplar = nroejemplar;
    }

    public int getNropagina() {
        return nropagina;
    }

    public void setNropagina(int nropagina) {
        this.nropagina = nropagina;
    }

    public int getNroedicion() {
        return nroedicion;
    }

    public void setNroedicion(int nroedicion) {
        this.nroedicion = nroedicion;
    }

    public String getYearpublica() {
        return yearpublica;
    }

    public void setYearpublica(String yearpublica) {
        this.yearpublica = yearpublica;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if ((this.idlibro == null) ? (other.idlibro != null) : !this.idlibro.equals(other.idlibro)) {
            return false;
        }
        return true;
    }

    public void registrarItemLibro(Autor aut)
    {
        ItemLibro item=new ItemLibro(aut);
        LI.agregar(item);
    }
    
}
