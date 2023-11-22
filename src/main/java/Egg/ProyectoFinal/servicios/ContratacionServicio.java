/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratacionServicio {
    
    @Autowired
    private ContratacionRepositorio contratacionRepositorio;
    
    @Transactional
    public void crearContratacion(Usuario cliente, Usuario proveedor){
        
        /* verificar metodo validarContratacion */
        
        Contratacion contratacion = new Contratacion();
        
        contratacion.setCliente(cliente);
        contratacion.setProveedor(proveedor);
        contratacion.setEstadoContratacion(Estado.SOLICITADO);
        contratacion.setAlta(new Date());
        
        contratacionRepositorio.save(contratacion);
    }
    
    //Falta agregar una excepcion para cuando no se encuetra la contratacion
    @Transactional
    public void finalizarContratacion (String idContratacion, String idProveedor){
        
        Optional<Contratacion> respuesta = contratacionRepositorio.findById(idContratacion);
        
        if (respuesta.isPresent()){
            
            Contratacion contratacion = respuesta.get();
            contratacion.setEstadoContratacion(Estado.FINALIZADO);
            
            contratacionRepositorio.save(contratacion);
        }
    }
    
    //Falta agregar una excepcion para cuando no se encuetra la contratacion
    @Transactional
    public void cancelarContratacion(String idContratacion){
        
        Optional<Contratacion> respuesta = contratacionRepositorio.findById(idContratacion);
        
        if (respuesta.isPresent()){
            
            Contratacion contratacion = respuesta.get();
            contratacion.setEstadoContratacion(Estado.CANCELADO);
            
            contratacionRepositorio.save(contratacion);
        }
        
    }
    
    public List<Contratacion> listarContrataciones(){
        
        List<Contratacion> contrataciones = new ArrayList();
        //usamos el metodo que nos trae JpaRepositori "findAll" que nos trae todo lo que encuentra
        contrataciones = contratacionRepositorio.findAll();

        return contrataciones;
    }
    
    public void validarContratacion(Usuario cliente, Usuario proveedor, Date alta, Estado estadoContratacion) throws MiException{
    
        if (cliente == null ) {
            throw new MiException("el Cliente no puede ser nulo");
        }
        if (proveedor == null ) {
            throw new MiException("el Proveedor no puede ser nulo");
        }
        if (alta == null ) {
            throw new MiException("indicar fecha de solicitud");
        }
        if (estadoContratacion == null ) {
            throw new MiException("indicar estado de la contratacion");
        }
    }

    public List<Contratacion> listarContrataciones(String id) {
        
        List<Contratacion> contrataciones1 = new ArrayList();
        List<Contratacion> contrataciones = new ArrayList();

        contrataciones1 = contratacionRepositorio.findAll();
        
        for (Contratacion contratacion : contrataciones1) {
            if (contratacion.getCliente().getId().equals(id)) {
                contrataciones.add(contratacion);
            }
        }

        return contrataciones;
    }
    
}
