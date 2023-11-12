/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;


@Entity
public class Proveedor extends Usuario {
    private Double calificacion;
    private Double precioHora;
    private String descripcionServicio;
    private List<String> rubros;
    
    /*@OneToMany
    List<Contratacion> contrataciones; */

    public Proveedor() {
    }

    public Proveedor(Double calificacion, Double precioHora, String descripcionServicio, List<String> rubros, List<Contratacion> contrataciones) {
        this.calificacion = calificacion;
        this.precioHora = precioHora;
        this.descripcionServicio = descripcionServicio;
        this.rubros = rubros;
        //this.contrataciones = contrataciones;
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

    public List<String> getRubros() {
        return rubros;
    }

    public void setRubros(List<String> rubros) {
        this.rubros = rubros;
    }

    /*public List<Contratacion> getContrataciones() {
        return contrataciones;
    }

    public void setContrataciones(List<Contratacion> contrataciones) {
        this.contrataciones = contrataciones;
    }*/
    
    
    
}
