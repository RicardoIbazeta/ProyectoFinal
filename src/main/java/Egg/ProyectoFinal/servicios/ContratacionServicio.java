/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ContratacionServicio {
    
    private ContratacionRepositorio contratacionRepositorio;
    
    public void crearContratacion(Usuario cliente, Usuario proveedor){
        
        Contratacion contratacion = new Contratacion();
        contratacion.setCliente(cliente);
        contratacion.setProveedor(proveedor);
        contratacion.setEstadoContratacion(Estado.SOLICITADO);
        contratacion.setAlta(new Date());
        contratacionRepositorio.save(contratacion);
        
    }
    
    
    //Falta agregar una excepcion para cuando no se encuetra la contratacion
    public void finalizarContratacion (String idContratacion, String idProveedor){
        Optional<Contratacion> respuesta = contratacionRepositorio.findById(idContratacion);
        if (respuesta.isPresent()){
            Contratacion contratacion = respuesta.get();
            contratacion.setEstadoContratacion(Estado.FINALIZADO);
            contratacionRepositorio.save(contratacion);
            
        }
    }
    
    
    //Falta agregar una excepcion para cuando no se encuetra la contratacion
    public void cancelarContratacion(String idContratacion){
        Optional<Contratacion> respuesta = contratacionRepositorio.findById(idContratacion);
        if (respuesta.isPresent()){
            Contratacion contratacion = respuesta.get();
            contratacion.setEstadoContratacion(Estado.CANCELADO);
            contratacionRepositorio.save(contratacion);
            
        }
        
    }
    
    
    
    
    
    
    public List listarContrataciones(){
        

        List<Contratacion> contrataciones = new ArrayList();

        //usamos el metodo que nos trae JpaRepositori "findAll" que nos trae todo lo que encuentra
        contrataciones = contratacionRepositorio.findAll();

        return contrataciones;
    }
    
}
