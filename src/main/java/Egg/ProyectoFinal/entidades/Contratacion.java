package Egg.ProyectoFinal.entidades;

import Egg.ProyectoFinal.enumeraciones.Estado;
import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Contratacion {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;

    private boolean altaBaja = true;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "estado_contratacion")
    private Estado estadoContratacion;

    public Contratacion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Estado getEstadoContratacion() {
        return estadoContratacion;
    }

    public void setEstadoContratacion(Estado estadoContratacion) {
        this.estadoContratacion = estadoContratacion;
    }

    public boolean isAltaBaja() {
        return altaBaja;
    }

    public void setAltaBaja(boolean altaBaja) {
        this.altaBaja = altaBaja;
    }

}
