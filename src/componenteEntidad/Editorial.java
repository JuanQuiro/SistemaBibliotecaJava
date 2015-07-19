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
public class Editorial {
    String ideditorial;
    String descr;

    public Editorial() {
    }

    public Editorial(String ideditorial, String descr) {
        this.ideditorial = ideditorial;
        this.descr = descr;
    }

    public String getIdeditorial() {
        return ideditorial;
    }

    public void setIdeditorial(String ideditorial) {
        this.ideditorial = ideditorial;
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
        hash = 13 * hash + Objects.hashCode(this.ideditorial);
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
        final Editorial other = (Editorial) obj;
        if (!Objects.equals(this.ideditorial, other.ideditorial)) {
            return false;
        }
        return true;
    }
    
    
}
