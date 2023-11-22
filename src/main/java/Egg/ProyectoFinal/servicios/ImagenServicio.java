
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ImagenRepositorio;
import Egg.ProyectoFinal.entidades.Imagen;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
    //Metodo para guardar imagen
    public Imagen guardar(MultipartFile archivo) throws MiException{
        if(archivo != null){
            try{
                Imagen imagen = new Imagen();
                
                imagen.setMime(archivo.getContentType());
                
                imagen.setNombre(archivo.getName());
                
                imagen.setContenido(archivo.getBytes());
                
                return imagenRepositorio.save(imagen);
                
                
            }catch (Exception e ){
                //Nos de vuelve el error
                System.err.println(e.getMessage());
            }
        }
        
        return null;
    }
    
    public Imagen actualizar(MultipartFile archivo,String idImagen)throws MiException{
        
        if(archivo != null){
            try{
                Imagen imagen = new Imagen();
                
                if(idImagen!=null){
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                    if(respuesta.isPresent()){
                        imagen = respuesta.get();
                    }
                }
                
                imagen.setMime(archivo.getContentType());
                
                imagen.setNombre(archivo.getName());
                
                imagen.setContenido(archivo.getBytes());
                
                return imagenRepositorio.save(imagen);
                
                
            }catch (Exception e ){
                //Nos de vuelve el error
                System.err.println(e.getMessage());
            }
        }
        
        return null;
        
        
    }
}