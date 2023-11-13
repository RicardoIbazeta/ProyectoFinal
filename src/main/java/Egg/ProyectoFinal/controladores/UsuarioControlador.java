
package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "usuario_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, String apellido, String documento, String email, String password, Estado estado,
            String telefono, String direccion, Boolean tipoUsuario, ModelMap modelo){
        
        try {
            usuarioServicio.crearUsuario(nombre, apellido, documento, email, password, estado, telefono, direccion, tipoUsuario);
            modelo.put("exito", "El usuario fue cargado correctamente");
        } catch (MiException ex) {
            return "usuario_form";
        }
        return "usuario_form";
    }
}
