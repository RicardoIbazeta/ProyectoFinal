package Egg.ProyectoFinal.entidades;

import Egg.ProyectoFinal.enumeraciones.Estrella;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Resenia {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    private String comentario;

    @Enumerated(EnumType.STRING)
    private Estrella estrellas; 

    @Temporal(TemporalType.TIMESTAMP)   
    private Date fecha;
    
    @ManyToOne
    private Proveedor proveedor;
    
    @ManyToOne
    private Usuario usuario;
    
    @OneToOne
    private Contratacion contratacion;

    public Resenia() {
    }

    public Resenia(String id, String comentario, Estrella estrellas, Date fecha, Proveedor proveedor, Usuario usuario, Contratacion contratacion) {
        this.id = id;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.contratacion = contratacion;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Estrella getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Estrella estrellas) {
        this.estrellas = estrellas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Contratacion getContratacion() {
        return contratacion;
    }

    public void setContratacion(Contratacion contratacion) {
        this.contratacion = contratacion;
    }
    
    
    

}
