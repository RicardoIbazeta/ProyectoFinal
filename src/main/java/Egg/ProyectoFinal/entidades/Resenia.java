package Egg.ProyectoFinal.entidades;

import Egg.ProyectoFinal.enumeraciones.Estrella;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resenias")
public class Resenia {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "cant_estrellas")
    @Enumerated(EnumType.ORDINAL)
    private Estrella estrellas;

    @ManyToOne(targetEntity = Proveedor.class)
    private Proveedor proveedor;
    
    @ManyToOne(targetEntity = Proveedor.class)
    private Usuario cliente;
    

}
