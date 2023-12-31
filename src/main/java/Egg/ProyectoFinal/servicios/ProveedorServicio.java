package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ProveedorRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Imagen;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.enumeraciones.Rol;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearProveedor(MultipartFile archivo, Double precioHora, String descripcionServicio,
            Rubro rubro, String nombre, String apellido, String documento, String email, String password,
            String password2, String telefono, String direccion, Boolean altaBaja) throws MiException {

        validarProveedor(nombre, apellido, documento, telefono, direccion, precioHora, descripcionServicio, rubro /*archivo*/);
        validarEmail(email);
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
        proveedor.setAltaBaja(true);

        // Validacion de imagen y asignacion de imagen predeterminada
        if (archivo != null && !archivo.isEmpty()) {
            // Usuario proporcionó una imagen, guárdala y asigna al usuario
            Imagen imagen = imagenServicio.guardar(archivo);
            proveedor.setImagen(imagen);

        } else {
            // Si el usuario no sube una imagen, se le asigna la imagen predeterminada
            Imagen imagenPredeterminada = imagenServicio.obtenerImagenPredeterminada();
            proveedor.setImagen(imagenPredeterminada);

        }

        proveedorRepositorio.save(proveedor);
    }

    @Transactional
    public void modificarProveedor(String nombre, String apellido, String password, String direccion,
            String email, String telefono, MultipartFile archivo, String id, Double precioHora,
            String descripcionServicio, Rubro rubro) throws MiException {

        validarProveedor(nombre, apellido, telefono, telefono, direccion, precioHora, descripcionServicio, rubro);
        validarEmail(email);
        validarPassword(password, password);

        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);

        Proveedor proveedor = new Proveedor();

        if (respuesta.isPresent()) {

            proveedor = respuesta.get();

            proveedor.setNombre(nombre);
            proveedor.setApellido(apellido);
            proveedor.setPassword(new BCryptPasswordEncoder().encode(password));
            proveedor.setDireccion(direccion);
            proveedor.setEmail(email);
            proveedor.setTelefono(telefono);

            proveedor.setDescripcionServicio(descripcionServicio);
            proveedor.setPrecioHora(precioHora);
            proveedor.setRubro(rubro);

            if (archivo != null && !archivo.isEmpty()) {
                Imagen imagen = imagenServicio.guardar(archivo);
                proveedor.setImagen(imagen);
            } else {
                Imagen imagenPredeterminada = imagenServicio.obtenerImagenPredeterminada();
                proveedor.setImagen(imagenPredeterminada);

            }

            proveedorRepositorio.save(proveedor);
        }
    }

    @Transactional
    public void darAltaBaja(Proveedor proveedor) {

        proveedor.setAltaBaja(!proveedor.isAltaBaja());
    }

    //////////////////////////////METODOS ESTADO CONTRATACION////////////////////////|||||||||||||||\\\\\\\
    // Metodo que permite al proveedor aceptar una solicitud de contratacion.
    @Transactional
    public void aceptarContratacion(Proveedor proveedor, Contratacion contratacion) {

        /* Se valida que el id del proveedor y el proveedor en la contratacion sean los mismos,
        y si son los mismos, le permitira al proveedor cambiar el estado de la Cotratacion */
        if (proveedor.getId().equalsIgnoreCase(contratacion.getProveedor().getId())) {
            contratacion.setEstadoContratacion(Estado.EN_PROCESO);
        }
    }

    // Metodo que permite al proveedor rechazar una solicitud de contratacion
    @Transactional
    public void rechazarContratacion(Proveedor proveedor, Contratacion contratacion) {

        /* Se valida que el id del proveedor y el proveedor en la contratacion sean los mismos,
        y si son los mismos, le permitira al proveedor cambiar el estado de la Cotratacion */
        if (proveedor.getId().equalsIgnoreCase(contratacion.getProveedor().getId())) {
            contratacion.setEstadoContratacion(Estado.CANCELADO);
        }
    }

    // Metodo que permite al proveedor dar por finalizada una contratacion
    @Transactional
    public void finalizarContratacion(Proveedor proveedor, Contratacion contratacion) {

        /* Se valida que el id del proveedor y el proveedor en la contratacion sean los mismos,
        y si son los mismos, le permitira al proveedor cambiar el estado de la Cotratacion */
        if (proveedor.getId().equalsIgnoreCase(contratacion.getProveedor().getId())) {
            contratacion.setEstadoContratacion(Estado.FINALIZADO);
        }

    }
    //////////////////////////////METODOS ESTADO CONTRATACION////////////////////////|||||||||||||||\\\\\\\

    // Metodo que valida que el Proveedor haya incluido todos los datos requeridos del form.
    private void validarProveedor(String nombre, String apellido, String documento, String telefono, String direccion,
            Double precioHora, String descripcionServicio, Rubro rubro /*MultipartFile archivo*/) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("Debes completar tu nombre");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new MiException("Debes completar tu apellido");
        }
        if (documento == null || documento.isEmpty()) {
            throw new MiException("Debes completar tu DNI");
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
            throw new MiException("Debes seleccionar un rubro de la lista");
        }
        /*
        if (archivo.isEmpty()) {
            throw new MiException("Debes subir una imagen con el logo de emprendimiento");
        }
         */
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

    // Metodo para validar que el usuario ingrese un formarto de email valido
    private void validarEmail(String email) throws MiException {

        if (email == null || email.isEmpty()) {
            throw new MiException("Debes completar tu correo electrónico");
        }

        /*
        ^[A-Za-z0-9._%-]+: Empieza con uno o más caracteres alfanuméricos, puntos, guiones bajos o porcentaje.
        @: Contiene un símbolo de "@".
        [A-Za-z0-9.-]+: Después del "@" contiene uno o más caracteres alfanuméricos, puntos o guiones.
        \\.: Luego viene un punto.
        [A-Z|a-z]{2,4}$: Termina con al menos 2 y hasta 4 letras, lo que representa la extensión del dominio (como "com" o "org").
         */
        String emailRegex = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,4}$";
        Pattern patron = Pattern.compile(emailRegex);

        Matcher match = patron.matcher(email);
        if (!match.find()) {
            throw new MiException("Correo electrónico invalido");
        }
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

    public Proveedor getOne(String id) {
        return proveedorRepositorio.getOne(id);
    }

    public List<Proveedor> listarProveedores() {

        List<Proveedor> proveedores = new ArrayList();
        proveedores = proveedorRepositorio.filtrarPorAlta(true);

        return proveedores;
    }

}
