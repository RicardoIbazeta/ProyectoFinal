package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Imagen;
import Egg.ProyectoFinal.enumeraciones.Rol;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    //Llamo a imagen servicio para vincular la imagen con el usuario
    @Autowired
    private ImagenServicio imagenServicio;
    
    
    //Agrego el Atributo MultiPartFile
    @Transactional
    public void crearUsuario(MultipartFile archivo,String nombre, String apellido, String documento, String email, String password, String password2,
            String telefono, String direccion) throws MiException {

        validarUsuario(nombre, apellido, documento, email, telefono, direccion);
        validarPassword(password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        
        usuario.setApellido(apellido);
        
        usuario.setDocumento(documento);
        
        usuario.setEmail(email); 
        
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        
        // usuario.setPassword2(password2);
       
        usuario.setTelefono(telefono);
       
        usuario.setDireccion(direccion);
       
        usuario.setRol(Rol.USER);
       
        usuario.setFechaAlta(new Date());   
        
        //Paso la imagen y la seteo
        Imagen imagen = imagenServicio.guardar(archivo);
        
        usuario.setImagen(imagen);
        
        usuarioRepositorio.save(usuario);
    }

    //Agrego atributo MultiPartFile para la imagen
    @Transactional
    public void modificarUsuario(MultipartFile archivo,String id, String nombre, String apellido, String email, String password, String telefono,
            String direccion, Boolean tipoUsuario) {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            
            Usuario usuario = respuesta.get();
            
            usuario.setApellido(apellido);
            usuario.setPassword(password);
            usuario.setDireccion(direccion);
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            usuario.setTelefono(telefono);
            //Verifica que la imagen no sea nula,busca por idImagen y la actualiza
            String idImagen = null;
            if(usuario.getImagen()!=null){
                idImagen = usuario.getImagen().getId();
                
            }
            
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            
            usuario.setImagen(imagen);
            
            usuarioRepositorio.save(usuario);
        }
    }
    
     public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList();
        usuarios = usuarioRepositorio.findAll();
        
        return usuarios;
    }

    //Metodo para validar que el usuario ingrese todos los datos requeridos en el form
    private void validarUsuario(String nombre, String apellido, String documento, String email,
            String telefono, String direccion) throws MiException {

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
    }

    //Metodo que valida los requisitos de la contraseña
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

    // Metodo usado para autenticar usuarios
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuario", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }

    }

}
