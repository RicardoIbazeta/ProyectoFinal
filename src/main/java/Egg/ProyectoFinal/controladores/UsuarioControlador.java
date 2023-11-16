package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Contratacion;
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

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    private ContratacionServicio contratacionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registrar")
    public String registrar() {

        return "usuario_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, String apellido, String documento, String email, String password, String password2,
            String telefono, String direccion, Boolean tipoUsuario, ModelMap modelo) {

        try {
            usuarioServicio.crearUsuario(nombre, apellido, documento, email, password, password2, telefono, direccion, tipoUsuario);
            
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
            return "usuario_form.html";
        }
    }

    @GetMapping("/lista")
    public String historialContrataciones(ModelMap modelo) {

        List<Contratacion> historial = contratacionServicio.listarContrataciones();
        modelo.addAttribute("historial", historial);
        return "contratacion_list.html";
    }
}
