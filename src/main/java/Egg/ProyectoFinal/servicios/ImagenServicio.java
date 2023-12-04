package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ImagenRepositorio;
import Egg.ProyectoFinal.entidades.Imagen;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    //Metodo para guardar imagen
    @Transactional
    public Imagen guardar(MultipartFile archivo) throws MiException {
        validarImagen(archivo);
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                //Nos de vuelve el error
                System.err.println(e.getMessage());
            }
        }

        return null;
    }

    @Transactional
    public Imagen actualizar(MultipartFile archivo, String idImagen) throws MiException {

        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();

                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                //Nos de vuelve el error
                System.err.println(e.getMessage());
            }
        }

        return null;
    }

    @Transactional
    public void eliminarImagen(Imagen imagen) {
        Optional<Imagen> respuesta = imagenRepositorio.findById(imagen.getId());

        if (respuesta.isPresent()) {

            imagenRepositorio.delete(imagen);
        }
    }

    public Imagen obtenerImagenPredeterminada() {
        try {
            // Especifica la ruta relativa al directorio de recursos estáticos
            String rutaImagenPredeterminada = "static/imagenPredeterminada.png";

            // Carga la imagen predeterminada desde el directorio de recursos estáticos
            Resource resource = new ClassPathResource(rutaImagenPredeterminada);

            // Lee el contenido de la imagen en un byte array
            byte[] contenidoImagen = FileCopyUtils.copyToByteArray(resource.getInputStream());

            // Crea la entidad Imagen con la información de la imagen predeterminada
            Imagen imagenPredeterminada = new Imagen();
            imagenPredeterminada.setMime("image/png");
            imagenPredeterminada.setNombre("imagenPredeterminada.png");
            imagenPredeterminada.setContenido(contenidoImagen);

            imagenRepositorio.save(imagenPredeterminada);

            return imagenPredeterminada;
        } catch (Exception e) {
            // Maneja las excepciones apropiadamente (puedes lanzar una excepción personalizada o registrar el error)
            e.printStackTrace(); // Solo imprime en la consola en este ejemplo; debes manejarlo según tu caso de uso.
            return null; // O devuelve una imagen predeterminada genérica si ocurre un error
        }
    }

    public void validarImagen(MultipartFile archivo) throws MiException {

        if (archivo == null || archivo.isEmpty()) {
            throw new MiException("La imagen no se cargo correctamente");

        }

    }

}
