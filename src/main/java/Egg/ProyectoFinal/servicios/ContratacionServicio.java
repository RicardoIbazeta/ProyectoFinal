package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratacionServicio {

    @Autowired
    private ContratacionRepositorio contratacionRepositorio;

    @Transactional
    public void crearContratacion(Usuario cliente, Proveedor proveedor) throws MiException {

        /* verificar metodo validarContratacion */
        Contratacion contratacion = new Contratacion();
        
        validarContratacion(cliente,proveedor);

        contratacion.setCliente(cliente);
        contratacion.setProveedor(proveedor);
        contratacion.setEstadoContratacion(Estado.SOLICITADO);
        contratacion.setAlta(new Date());
//        contratacion.setAltaBaja(true);

        contratacionRepositorio.save(contratacion);
    }

    @Transactional
    public void darAltaBaja(Contratacion contratacion) {

        contratacion.setAltaBaja(!contratacion.isAltaBaja());
    }

    public List<Contratacion> listarContrataciones() {

        List<Contratacion> contrataciones = new ArrayList();
        contrataciones = contratacionRepositorio.findAll();

        return contrataciones;
    }

    public List<Contratacion> misContrataciones(String id) {

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
    
        public List<Contratacion> ContratacionesProveedor(String id) {

        List<Contratacion> contrataciones1 = new ArrayList();
        List<Contratacion> contrataciones = new ArrayList();

        contrataciones1 = contratacionRepositorio.findAll();

        for (Contratacion contratacion : contrataciones1) {
            if (contratacion.getProveedor().getId().equals(id)) {
                contrataciones.add(contratacion);
            }
        }

        return contrataciones;
    }
    
    
    
    
    

    //////////////////////////////METODOS ESTADO CONTRATACION////////////////////////|||||||||||||||\\\\\\\
    

    //Falta agregar una excepcion para cuando no se encuetra la contratacion
    @Transactional
    public void finalizarContratacion(String idContratacion) {

        Optional<Contratacion> respuesta = contratacionRepositorio.findById(idContratacion);

        if (respuesta.isPresent()) {

            Contratacion contratacion = respuesta.get();
            contratacion.setEstadoContratacion(Estado.FINALIZADO);
            contratacion.setAltaBaja(false);

            contratacionRepositorio.save(contratacion);
        }
    }

    //Falta agregar una excepcion para cuando no se encuetra la contratacion
    @Transactional
    public void cancelarContratacion(String idContratacion) {

        Optional<Contratacion> respuesta = contratacionRepositorio.findById(idContratacion);

        if (respuesta.isPresent()) {

            Contratacion contratacion = respuesta.get();
            contratacion.setEstadoContratacion(Estado.CANCELADO);
            contratacion.setAltaBaja(false);

            contratacionRepositorio.save(contratacion);
        }
    }
    
    @Transactional
    public void aceptarContratacion(String idContratacion) {
        Optional<Contratacion> respuesta = contratacionRepositorio.findById(idContratacion);

        /* Se valida que el id del proveedor y el proveedor en la contratacion sean los mismos,
        y si son los mismos, le permitira al proveedor cambiar el estado de la Cotratacion */
        if (respuesta.isPresent()){
            Contratacion contratacion= respuesta.get();
            contratacion.setEstadoContratacion(Estado.EN_PROCESO);
            contratacion.setAltaBaja(true);
            contratacionRepositorio.save(contratacion);
        }
    }
    
    
    
    
    
    
    
    
    
    
    //////////////////////////////METODOS ESTADO CONTRATACION////////////////////////|||||||||||||||\\\\\\\
    
    
    
    
    
    
    
    
    
    

    public void validarContratacion(Usuario cliente, Usuario proveedor/*, Date alta, Estado estadoContratacion*/) throws MiException {

        if (cliente == null) {
            throw new MiException("el Cliente no puede ser nulo");
        }
        if (proveedor == null) {
            throw new MiException("el Proveedor no puede ser nulo");
        }/*
        if (alta == null) {
            throw new MiException("indicar fecha de solicitud");
        }
        if (estadoContratacion == null) {
            throw new MiException("indicar estado de la contratacion");
        }*/
    }
    
    public Contratacion getOne(String id) {
        return contratacionRepositorio.getOne(id);
    }

}
