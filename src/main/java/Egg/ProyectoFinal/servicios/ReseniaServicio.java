package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ReseniaRepositorio;
import Egg.ProyectoFinal.entidades.Resenia;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReseniaServicio {
    
    @Autowired
    private ReseniaRepositorio reseniaRepositorio;
    @Autowired
    private ProveedorServicio proveedorServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ContratacionServicio contratacionServicio;

   @Transactional
    public Resenia crear(String comentario, Estrella estrella/*, String idProveedor, String idCliente, String idContratacion*/) throws Exception {
    
        validarResenia(comentario,estrella/*,idProveedor,idCliente,idContratacion*/);
        
        Resenia resenia = new Resenia();
        
        resenia.setComentario(comentario);
        resenia.setEstrellas(estrella);
//        resenia.setProveedor(proveedorServicio.getOne(idProveedor));
//        resenia.setCliente(usuarioServicio.getOne(idCliente));
//        resenia.setContratacion(contratacionServicio.getOne(idContratacion));
        
        
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
     

    /*/@Transactional
    public void modificarById(String id, String comentario, String estrella, Proveedor proveedor) throws Exception;

    //@Transactional
    public void editarComentario(String id) throws Exception;
    
    //@Transactional
    public void eliminarById(String id) throws Exception;

    public List<Resenia> listarResenia() throws Exception;

    public Resenia getOne(String id) throws Exception;
    
    public Resenia buscarById(String id) throws Exception; */
    
    private void validarResenia(String comentario, Estrella estrella/*, String idProveedor, String idCliente, String idContratacion*/) throws MiException {

        if (comentario == null || comentario.isEmpty()) {
            throw new MiException("Debes completar tu comentario");
        }
        if (estrella == null) {
            throw new MiException("Debes completar tu comentario");
        }
//        if (idProveedor == null || idProveedor.isEmpty()) {
//            throw new MiException("Debes completar tu comentario");
//        }
//        if (idCliente == null || idCliente.isEmpty()) {
//            throw new MiException("Debes completar tu comentario");
//        }
//        if (idContratacion == null || idContratacion.isEmpty()) {
//            throw new MiException("Debes completar tu comentario");
//        }
    }

    @Transactional
    public void eliminarResenia(String id) throws MiException {
        // Puedes agregar lógica de validación aquí si es necesario
        // por ejemplo, verificar si la reseña existe antes de eliminarla
        Resenia resenia = reseniaRepositorio.findById(id).orElse(null);
        if (resenia != null) {
            reseniaRepositorio.delete(resenia);
        } else {
            throw new MiException("La reseña con el ID " + id + " no existe.");
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
    
    
}
