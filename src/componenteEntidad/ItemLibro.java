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
public class ItemLibro {
    private Autor autor;

    public ItemLibro() {
    }

    public ItemLibro(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemLibro other = (ItemLibro) obj;
        if (this.autor != other.autor && (this.autor == null || !this.autor.equals(other.autor))) {
            return false;
        }
        return true;
    }

}
