
package Egg.ProyectoFinal.entidades;

import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.enumeraciones.Rol;
import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;
    protected String nombre;
    protected String apellido;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaAlta;
    protected String email;
    protected String password;
    protected String password2;
    @Enumerated(EnumType.STRING)
    protected Estado estado;
    @Enumerated(EnumType.STRING)
    protected Rol rol;
    protected String telefono;
    @OneToOne
    protected Imagen imagen;
    protected String direccion;
    protected String documento;
    protected boolean altaBaja;
    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public boolean isAltaBaja() {
        return altaBaja;
    }

    public void setAltaBaja(boolean altaBaja) {
        this.altaBaja = altaBaja;
    }

  
    
}
