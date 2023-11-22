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

        validarProveedor(nombre, apellido, documento, email, telefono, direccion, precioHora, descripcionServicio, rubro);
        validarPassword(password, password2);

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
    private void validarProveedor(String nombre, String apellido, String documento, String email,
            String telefono, String direccion, Double precioHora, String descripcionServicio, Rubro rubro) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("Debes completar tu nombre");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new MiException("Debes completar tu apellido");
        }
        if (documento == null || documento.isEmpty()) {
            throw new MiException("Debes completar tu DNI");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("Debes completar tu correo electrónico");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new MiException("Debes completar tu número de telefono");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new MiException("Debes completar tu dirección");
        }
        if (precioHora == null || precioHora.isNaN()) {
            throw new MiException("Debe indicar el valor hora/labor");
        }
        if (descripcionServicio == null || descripcionServicio.isEmpty()) {
            throw new MiException("Debes indicar la descripción del servicio que deseas proveer");
        }
        if (rubro == null) {
            throw new MiException("Debes seleccionar un rubro de la lista ");
        }       

    }
    
    // Metodo que valida que la contraseña cumpla con los criterios requeridos.
        private void validarPassword(String password, String password2) throws MiException {
        
        if (password.isEmpty()) {
            throw new MiException("La contraseña no debe estar vacía");
        }
        if (password.length() < 8) {
            throw new MiException("La contraseña debe tener al menos 8 caracteres");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new MiException("La contraseña debe contener al menos una letra mayúscula");
        }
        if (!password.matches(".*\\d.*")) {
            throw new MiException("La contraseña debe contener al menos un número");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas deben coincidir");
        }
    }

}
