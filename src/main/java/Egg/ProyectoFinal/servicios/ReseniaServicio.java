package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ReseniaRepositorio;
import Egg.ProyectoFinal.entidades.Resenia;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import Egg.ProyectoFinal.excepciones.MiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReseniaServicio {
    
    @Autowired
    private ReseniaRepositorio reseniaRepositorio;
    @Autowired
    private ProveedorServicio proveedorServicio;

   @Transactional
    public Resenia crear(String comentario, Estrella estrella, String idProveedor) throws Exception {
    
        validarResenia(comentario,estrella,idProveedor);
        
        Resenia resenia = new Resenia();
        
        resenia.setComentario(comentario);
        resenia.setEstrellas(estrella);
        resenia.setProveedor(proveedorServicio.getOne(idProveedor));
        
        
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
    
    private void validarResenia(String comentario, Estrella estrella, String idProveedor) throws MiException {

        if (comentario == null || comentario.isEmpty()) {
            throw new MiException("Debes completar tu comentario");
        }
        if (estrella == null) {
            throw new MiException("Debes completar tu comentario");
        }
        if (idProveedor == null || idProveedor.isEmpty()) {
            throw new MiException("Debes completar tu comentario");
        }
    }

}
