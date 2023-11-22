package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ContratacionServicio contratacionServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "cliente_form.html";
    }

    @PostMapping("/registro")
    public String registro(MultipartFile archivo, @RequestParam String nombre, String apellido, String documento, String email, String password, String password2, String telefono, String direccion, ModelMap modelo) {


        try {
            /* eliminacion parametro tipoUsuario por campo eliminado en el formulario
                se opta por poner BOOLEAN.TRUE al ser un registro de usuario*/
            usuarioServicio.crearUsuario(archivo,nombre, apellido, documento, email, password, password2, telefono, direccion);

            modelo.put("exito", "El usuario fue cargado correctamente");
            return "index.html";
            
        } catch (MiException ex) {
            
            modelo.put("error", ex.getMessage());
            
            /*
            Se Inyecta la informacion proporcionada por el usuario previo a un error 
            y asi no tiene que volver ingresar todo nuevamente.
            La contraseña y el tipoUsuario siempre deberan ser ingresados
            */
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("documento", documento);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            modelo.put("direccion", direccion);

            return "cliente_form.html";
        }
    }

    @GetMapping("/lista")
    public String historialContrataciones(ModelMap modelo) {

        List<Contratacion> historial = contratacionServicio.listarContrataciones();
        modelo.addAttribute("historial", historial);
        return "contratacion_list.html";
    }
    
    /* Mapeo que lista todos los usuarios */
    /*@GetMapping("/lista")
    public String listarUsuarios(ModelMap modelo){
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list.html";
    }*/
}
