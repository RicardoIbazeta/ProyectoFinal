
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ProveedorRepositorio;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorServicio {
    
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    @Transactional
     public void crearProveedor(Double precioHora, String descripcionServicio, List<Rubro> rubros, String nombre, String apellido, String documento, String email, String password, String password2,
            String telefono, String direccion) throws MiException{
        Proveedor proveedor = new Proveedor();
        
        validarProveedor(precioHora, descripcionServicio, rubros);
        proveedor.setDescripcionServicio(descripcionServicio);
        proveedor.setPrecioHora(precioHora);
        proveedor.setRubros(rubros);
        
        proveedor.setNombre(nombre);
        proveedor.setApellido(apellido);
        proveedor.setDocumento(documento);
        proveedor.setEmail(email);
        proveedor.setPassword(password);
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);
        
        
        proveedorRepositorio.save(proveedor);
    }

    
    public void modificarProveedor (String id, Double precioHora, String descripcionServicio, List<Rubro> rubros){
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        Proveedor proveedor = new Proveedor();
        
        
        
        if (respuesta.isPresent()){
            Usuario usuario=respuesta.get();
            proveedor.setDescripcionServicio(descripcionServicio);
            proveedor.setPrecioHora(precioHora);
            proveedor.setRubros(rubros);
            
            
            proveedorRepositorio.save(proveedor);
            
        }
    }
    
    
    
    // Metodo que valida que el Proveedor haya incluido todos los datos requeridos del form.
    private void validarProveedor(Double precioHora, String descripcionServicio, List<Rubro> rubros) throws MiException {
        if (precioHora == null || precioHora.isNaN()) {
            throw new MiException("Debe indicar el valor hora/labor");
        }
        if (descripcionServicio == null || descripcionServicio.isEmpty()) {
            throw new MiException("Debes indicar la descripción del servicio que deseas proveer");
        }
        if (rubros == null || rubros.isEmpty()) {
            throw new MiException("Debes seleccionar un oficio de la lista ");
        }
        
    }
    
    public List<Proveedor> listarProveedores() {

        List<Proveedor> proveedores = new ArrayList();

        proveedores = proveedorRepositorio.findAll();

        return proveedores;
    }
            
    
    
    
}
