package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ProveedorRepositorio;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Imagen;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Rol;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
=======
>>>>>>> 5bbeb2c (encriptacion password proveedor)
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    //Llamo a imagen servicio para vincular la imagen con el usuario
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearProveedor(MultipartFile archivo, Double precioHora, String descripcionServicio, Rubro rubro, String nombre, String apellido, String documento, String email, String password, String password2,
            String telefono, String direccion) throws MiException {

        validarProveedor(nombre, apellido, documento, email, telefono, direccion, precioHora, descripcionServicio, rubro, archivo);
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
        proveedor.setPassword(new BCryptPasswordEncoder().encode(password));
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);
        proveedor.setRol(Rol.PROVEEDOR);
        proveedor.setFechaAlta(new Date());
<<<<<<< HEAD
        //Paso la imagen y la seteo
        Imagen imagen = imagenServicio.guardar(archivo);

        proveedor.setImagen(imagen);
=======
>>>>>>> 5bbeb2c (encriptacion password proveedor)

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
    private void validarProveedor(String nombre, String apellido, String documento, String email, String telefono, String direccion, Double precioHora, String descripcionServicio, Rubro rubro, MultipartFile archivo) throws MiException {

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
        if (archivo.isEmpty()) {
            throw new MiException("Debes subir una imagen con el logo de emprendimiento");
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

    public Proveedor getOne(String id) {
        return proveedorRepositorio.getOne(id);
    }
    
    
        // Metodo usado para autenticar usuarios
    // @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Proveedor proveedor = proveedorRepositorio.buscarPorEmail(email);

        if (proveedor != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + proveedor.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("proveedor", proveedor);

            return new User(proveedor.getEmail(), proveedor.getPassword(), permisos);
        } else {
            return null;
        }

    }

}
