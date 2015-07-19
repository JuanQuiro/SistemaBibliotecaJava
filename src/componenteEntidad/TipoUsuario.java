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
public class TipoUsuario {
    private String idtipousuario;
    private String descr;

    public TipoUsuario() {
    }

    public TipoUsuario(String idtipousuario, String descr) {
        this.idtipousuario = idtipousuario;
        this.descr = descr;
    }

    public String getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(String idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idtipousuario);
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
        final TipoUsuario other = (TipoUsuario) obj;
        if (!Objects.equals(this.idtipousuario, other.idtipousuario)) {
            return false;
        }
        return true;
    }
    
    
}
