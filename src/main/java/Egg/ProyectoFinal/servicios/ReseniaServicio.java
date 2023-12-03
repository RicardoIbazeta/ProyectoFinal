package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ReseniaRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Resenia;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReseniaServicio {
    
    @Autowired
    private ReseniaRepositorio reseniaRepositorio;

   @Transactional
    public Resenia crear(String comentario, Estrella estrella, Proveedor proveedor, Usuario usuario,Contratacion contratacion) throws Exception {
        
        validarResenia(comentario, estrella,proveedor,usuario,contratacion);
        
        Resenia resenia = new Resenia();
        resenia.setComentario(comentario);
        resenia.setEstrellas(estrella);
        resenia.setUsuario(usuario);
        resenia.setContratacion(contratacion);
        resenia.setProveedor(proveedor);
        resenia.setFecha(new Date());
        reseniaRepositorio.save(resenia);
        return resenia;
    }
    
    private Estrella estrellaFromString(String x){
        switch (x) {
            case "1" : return Estrella.UNO;
            case "2" : return Estrella.DOS;
            case "3" : return Estrella.TRES;
            case "4" : return Estrella.CUATRO;
            case "5" : return Estrella.CINCO;
        }
        return null;
    }
     


    private void validarResenia(String comentario, Estrella estrella,Proveedor proveedor,Usuario usuario,Contratacion contratacion) throws MiException {


        if (comentario == null || comentario.isEmpty()) {
            throw new MiException("Debes completar tu comentario");
        }
        if (estrella == null) {
            throw new MiException("Debes completar tu comentario");
        }


       if (proveedor == null ) {
            throw new MiException("Debes completar tu comentario");
        }
        if (usuario == null) {
            throw new MiException("Debes completar tu comentario");
        }
        if (contratacion == null ) {
            throw new MiException("Debes completar tu comentario");
        }

    }

    @Transactional
    public void eliminarResenia(Resenia resenia){

         Optional<Resenia> respuestaResenia = reseniaRepositorio.findById(resenia.getId());
        if (respuestaResenia.isPresent()) {
            reseniaRepositorio.delete(resenia);
        }
    }
    
    @Transactional
    public void modificarComentario(String id, String comentario) throws MiException {
        
        Optional<Resenia> respuesta = reseniaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Resenia resenia = respuesta.get();
            
            resenia.setComentario(comentario);
            
            reseniaRepositorio.save(resenia);
        }
        
    }
    
    
     public List<Resenia> listarReseniaAdmin(){
        List<Resenia> resenias = new ArrayList();
        resenias= reseniaRepositorio.findAll();
        return resenias;
        
    }
   
   
    public List<Resenia> listarResenia(String id){
        List<Resenia> resenias1= new ArrayList();
        List<Resenia> resenias = new ArrayList();
        
        resenias1 = reseniaRepositorio.findAll();
        
        for (Resenia resenia :resenias1) {
            if (resenia.getProveedor().getId().equals(id)) {
                resenias.add(resenia);
            }
        } 
        return resenias;
        
    }
    
    
     public Resenia getOne(String id) {
        return reseniaRepositorio.getOne(id);
    }
    
    
    
    
}
