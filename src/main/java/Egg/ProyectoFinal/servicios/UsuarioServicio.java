
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuariorepositorio;
    
    @Transactional
    public void crearUsuario(String nombre,String apellido,String documento,String email,String contraseña,Boolean estado,String telefono,String direccion,Boolean tipoUsuario){
        
        Usuario usuario = new Usuario();
        
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDocumento(documento);
        usuario.setEmail(email);
        usuario.setContraseña(contraseña);
        usuario.setEstado(true);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setFechaAlta(new Date());
        
        usuariorepositorio.save(usuario);
        
        
    }
    
}
