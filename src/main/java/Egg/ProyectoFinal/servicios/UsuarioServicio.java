
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
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
    
    public List<Usuario> listarUsuarios(){
        
        List<Usuario> usuarios = new ArrayList();
        usuarios = usuariorepositorio.findAll();
        return usuarios;
    }
    
    
    public void modificarUsuario(String id,String nombre,String apellido,String email,String contraseña,String telefono,String direccion,Boolean tipoUsuario){
        
        Optional<Usuario> respuesta = usuariorepositorio.findById(id);
        
        
        if(respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuario.setApellido(apellido);
            usuario.setContraseña(contraseña);
            usuario.setDireccion(direccion);
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            usuario.setTelefono(telefono);
            usuario.setTipoUsuario(tipoUsuario);
            
            usuariorepositorio.save(usuario);
            
        }
        
        
    }
    
    
    
    
}
