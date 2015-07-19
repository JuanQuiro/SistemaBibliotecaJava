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
public class Usuario {
    private String idcarnet;
    private String apepat;
    private String apemat;
    private String nombres;
    private TipoDocumento tipoDocumento;
    private String nrodocumento;
    private String fechanaci;
    private String direccion;
    private String telefono;
    private String celular;
    private String email;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String idcarnet, String apepat, String apemat, String nombres, TipoDocumento tipoDocumento, String nrodocumento, String fechanaci, String direccion, String telefono, String celular, String email, TipoUsuario tipoUsuario) {
        this.idcarnet = idcarnet;
        this.apepat = apepat;
        this.apemat = apemat;
        this.nombres = nombres;
        this.tipoDocumento = tipoDocumento;
        this.nrodocumento = nrodocumento;
        this.fechanaci =  fechanaci;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIdcarnet() {
        return idcarnet;
    }

    public void setIdcarnet(String idcarnet) {
        this.idcarnet = idcarnet;
    }

    public String getApepat() {
        return apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    public String getApemat() {
        return apemat;
    }

    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNrodocumento() {
        return nrodocumento;
    }

    public void setNrodocumento(String nrodocumento) {
        this.nrodocumento = nrodocumento;
    }

    public String getFechanaci() {
        return fechanaci;
    }

    public void setFechanaci(String fechanaci) {
        this.fechanaci = fechanaci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idcarnet);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.idcarnet, other.idcarnet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idcarnet=" + idcarnet + ", apepat=" + apepat + ", apemat=" + apemat + ", nombres=" + nombres + '}';
    }      
    
}
