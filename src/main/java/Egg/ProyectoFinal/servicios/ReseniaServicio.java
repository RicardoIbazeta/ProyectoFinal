
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Resenia;
import java.util.List;


public interface ReseniaServicio {
    public Resenia crear (String comentario, String estrella, String idProveedor,String idSolicitud) throws Exception;
    public void eliminarById (String id)throws Exception;
    public void modificarById (String id, String comentario, String estrella, Proveedor proveedor) throws Exception;
    public List<Resenia> lsitarResenia() throws Exception;
    public Resenia buscarById (String id) throws Exception;
    public Resenia getOne(String id) throws Exception;

    void editarComentario(String id) throws Exception; 
}
