/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private Usuario proveedor;
    
    @Temporal(TemporalType.TIMESTAMP)   
    private Date alta;
    
    /*@ManyToOne
    private List<Proveedor> proveedores; */
    
    
}
