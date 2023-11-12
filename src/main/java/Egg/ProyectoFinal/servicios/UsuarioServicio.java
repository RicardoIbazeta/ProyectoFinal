package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
<<<<<<< HEAD
=======
import Egg.ProyectoFinal.enumeraciones.Estado;
>>>>>>> origin
import Egg.ProyectoFinal.excepciones.MiException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuariorepositorio;

    @Transactional
<<<<<<< HEAD
    public void crearUsuario(String nombre, String apellido, String documento, String email, String password, Boolean estado,
=======
    public void crearUsuario(String nombre, String apellido, String documento, String email, String password, Estado estado,
>>>>>>> origin
            String telefono, String direccion, Boolean tipoUsuario) throws MiException {

        Usuario usuario = new Usuario();

        validarUsuario(nombre, apellido, documento, email, password, password, telefono, direccion);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDocumento(documento);
        usuario.setEmail(email);
        usuario.setPassword(password);
<<<<<<< HEAD
        usuario.setEstado(true);
=======
        // definir si ESTADO es referente al ESTADO DE LA CONTRATACION o estado del usuario.
        usuario.setEstado(estado);
>>>>>>> origin
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setFechaAlta(new Date());

        usuariorepositorio.save(usuario);

    }

    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList();
        usuarios = usuariorepositorio.findAll();
        return usuarios;
    }

    public void modificarUsuario(String id, String nombre, String apellido, String email, String password, String telefono,
            String direccion, Boolean tipoUsuario) {

        Optional<Usuario> respuesta = usuariorepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setApellido(apellido);
            usuario.setPassword(password);
            usuario.setDireccion(direccion);
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            usuario.setTelefono(telefono);
            usuario.setTipoUsuario(tipoUsuario);

            usuariorepositorio.save(usuario);

        }
    }
        // Metodo que valida que el usuario haya introducido todos los datos del form
    private void validarUsuario(String nombre, String apellido, String documento, String email,
            String password, String password2, String telefono, String direccion) throws MiException {

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
        if (password == null || password.isEmpty()) {
            throw new MiException("Debes completar tu contraseña");
        }
        if (!password2.equals(password)) {
            throw new MiException("Las contraseñas deben coincidir");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new MiException("Debes completar tu número de telefono");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new MiException("Debes completar tu dirección");
        }

    }
}


