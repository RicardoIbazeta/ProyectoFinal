package Egg.ProyectoFinal.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Proveedor extends Usuario {
    
    private Double calificacion;
    private Double precioHora;
    private String descripcionServicio;
    private boolean altaBaja;
    @OneToMany
    private List<Resenia> resenias;
    
    @OneToOne
    public Rubro rubro;
    
    /*@OneToMany
    List<Contratacion> contrataciones; */

    public Proveedor() {
    }
    
    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public boolean isAltaBaja() {
        return altaBaja;
    }

    public void setAltaBaja(boolean altaBaja) {
        this.altaBaja = altaBaja;
    }
  
    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }
    
    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }
    
    /*public List<Contratacion> getContrataciones() {
        return contrataciones;
    }

    public void setContrataciones(List<Contratacion> contrataciones) {
        this.contrataciones = contrataciones;
    }*/
}
