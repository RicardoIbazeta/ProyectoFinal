package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    UsuarioServicio usuarioServicio;
    
    
    @GetMapping("/")
    public String index() {

        return "index.html";
    }

    @GetMapping("/registrarseBotones")
    public String registrarseBotones(){
        
        return "registrarse-botones.html";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, ModelMap modelo){
        if(error !=null){
            modelo.put("error","Usuario o Contraseña Invalidos");
            
        }
        return "login.html";
    }
        
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    } 
    
    //Verifica que el usuario sea user o admin
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo,HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
         modelo.put("usuario", usuario);
        return "usuario_modificar.html";
    }
    //si es usuario lo manda a modificar usuario
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar(MultipartFile archivo,@PathVariable String id, @RequestParam String nombre,@RequestParam String apellido,@RequestParam String email,@RequestParam String password,@RequestParam String telefono,
            @RequestParam String direccion,@RequestParam Boolean tipoUsuario,ModelMap modelo) {

        try {
            usuarioServicio.modificarUsuario(archivo,id,nombre, apellido, email,password,telefono,
             direccion);

            modelo.put("exito", "Usuario actualizado correctamente!");

            return "inicio.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "usuario_modificar.html";
        }
    }
    
}
