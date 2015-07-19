/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componenteEntidad;

import java.util.Objects;

/**
 *
 * @author Enrique
 */
public class TipoDocumento {
    String iddocumento;
    String descripcion;

    public TipoDocumento() {
    }

    public TipoDocumento(String iddocumento, String descripcion) {
        this.iddocumento = iddocumento;
        this.descripcion = descripcion;
    }

    public String getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" + "iddocumento=" + iddocumento + ", descripcion=" + descripcion + '}';
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.iddocumento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoDocumento other = (TipoDocumento) obj;
        if (!Objects.equals(this.iddocumento, other.iddocumento)) {
            return false;
        }
        return true;
    }
    
    
}
