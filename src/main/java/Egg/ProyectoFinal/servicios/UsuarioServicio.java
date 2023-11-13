package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.enumeraciones.Estado;
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
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearUsuario(String nombre, String apellido, String documento, String email, String password, Estado estado,
            String telefono, String direccion, Boolean tipoUsuario) throws MiException {

        validarUsuario(nombre, apellido, documento, email, password, password, telefono, direccion);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDocumento(documento);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setEstado(estado);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setFechaAlta(new Date());

        usuarioRepositorio.save(usuario);

    }

    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList();
        usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }

    @Transactional
    public void modificarUsuario(String id, String nombre, String apellido, String email, String password, String telefono,
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
            usuario.setTipoUsuario(tipoUsuario);

            usuarioRepositorio.save(usuario);

        }
    }

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
