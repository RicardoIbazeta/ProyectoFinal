package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ProveedorRepositorio;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Rol;
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
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearProveedor(Double precioHora, String descripcionServicio, Rubro rubro, String nombre, String apellido, String documento, String email, String password, String password2,
            String telefono, String direccion) throws MiException {

//         Rubro gas = new Rubro();
//            gas.setId("1");
//            gas.setNombre("gasista");
//            rubros.add(gas);
        validarProveedor(precioHora, descripcionServicio, rubro);

        Proveedor proveedor = new Proveedor();

        proveedor.setDescripcionServicio(descripcionServicio);
        proveedor.setPrecioHora(precioHora);
        proveedor.setRubro(rubro);
        proveedor.setCalificacion(1D);
        proveedor.setNombre(nombre);
        proveedor.setApellido(apellido);
        proveedor.setDocumento(documento);
        proveedor.setEmail(email);
        proveedor.setPassword(password);
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);
        proveedor.setRol(Rol.PROVEEDOR);

        proveedor.setNombre(nombre);
        proveedor.setApellido(apellido);
        proveedor.setDocumento(documento);
        proveedor.setEmail(email);
        proveedor.setPassword(password);
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);

        proveedorRepositorio.save(proveedor);
    }

    @Transactional
    public void modificarProveedor(String id, Double precioHora, String descripcionServicio, Rubro rubro) {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        Proveedor proveedor = new Proveedor();

        if (respuesta.isPresent()) {

            /* Proveedor proveedor = (Proveedor) respuesta.get(); */ // verificar funcionamiento del casteo
            Usuario usuario = respuesta.get();

            proveedor.setDescripcionServicio(descripcionServicio);
            proveedor.setPrecioHora(precioHora);
            proveedor.setRubro(rubro);

            proveedorRepositorio.save(proveedor);
        }
    }

    public List<Proveedor> listarProveedores() {

        List<Proveedor> proveedores = new ArrayList();

        proveedores = proveedorRepositorio.findAll();

        return proveedores;
    }

    // Metodo que valida que el Proveedor haya incluido todos los datos requeridos del form.
    private void validarProveedor(Double precioHora, String descripcionServicio, Rubro rubro) throws MiException {
        if (precioHora == null || precioHora.isNaN()) {
            throw new MiException("Debe indicar el valor hora/labor");
        }
        if (descripcionServicio == null || descripcionServicio.isEmpty()) {
            throw new MiException("Debes indicar la descripción del servicio que deseas proveer");
        }
        if (rubro == null) {
            throw new MiException("Debes seleccionar un oficio de la lista ");
        }

    }
<<<<<<< HEAD

=======
    
    public List<Proveedor> listarProveedores() {

        List<Proveedor> proveedores = new ArrayList();

        proveedores = proveedorRepositorio.findAll();

        return proveedores;
    }
            
    
    
    
>>>>>>> 263775b (commit numero 3 millones)
}
