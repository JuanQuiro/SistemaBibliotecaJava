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
public class Acceso {
    String idacceso;
    String nomusuario;
    String password;
    Usuario usuario;

    public Acceso() {
    }

    public Acceso(String idacceso, String nomusuario, String password, Usuario usuario) {
        this.idacceso = idacceso;
        this.nomusuario = nomusuario;
        this.password = password;
        this.usuario = usuario;
    }

    public String getIdacceso() {
        return idacceso;
    }

    public void setIdacceso(String idacceso) {
        this.idacceso = idacceso;
    }

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idacceso);
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
        final Acceso other = (Acceso) obj;
        if (!Objects.equals(this.idacceso, other.idacceso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acceso{" + "idacceso=" + idacceso + ", nomusuario=" + nomusuario + '}';
    }
    
    
}
