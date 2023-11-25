package Egg.ProyectoFinal.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente extends Usuario {
    
    @OneToMany
    private List<Contratacion> contrataciones;

    public Cliente() {
    }

    public List<Contratacion> getContrataciones() {
        return contrataciones;
    }

    public void setContrataciones(List<Contratacion> contrataciones) {
        this.contrataciones = contrataciones;
    }
    
    
}
