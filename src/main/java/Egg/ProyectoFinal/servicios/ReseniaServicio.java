package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ReseniaRepositorio;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Resenia;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.List;
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
    public void crear(String comentario, String estrella, String idProveedor, String idSolicitud) throws Exception {
    
        validarResenia(comentario);
        
        Resenia resenia = new Resenia();
        
        resenia.setComentario(comentario);
        resenia.setEstrellas(Estrella.UNO);
        resenia.setProveedor(proveedorServicio.getOne(idProveedor));
        
        reseniaRepositorio.save(resenia);
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
    
    private void validarResenia(String comentario) throws MiException {

        if (comentario == null || comentario.isEmpty()) {
            throw new MiException("Debes completar tu comentario");
        }
    }

}
