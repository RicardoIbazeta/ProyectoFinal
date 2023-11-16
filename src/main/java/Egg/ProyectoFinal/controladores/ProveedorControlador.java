package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {
    
    
    
    @Autowired
    private ProveedorServicio proveedorServicio;
    
    @GetMapping("/registrar")
    public String registrar() {

        return "proveedor_form.html";
    }

    @PostMapping("/registro")
    public String registro(Double precioHora, String descripcionServicio, List<Rubro> rubros, @RequestParam String nombre, String apellido, String documento, String email, String password, String password2,
            String telefono, String direccion, Boolean tipoUsuario, ModelMap modelo) {

        try {
            proveedorServicio.crearProveedor(precioHora, descripcionServicio, rubros, nombre, apellido, documento, email, password, password2, telefono, direccion);
            
            modelo.put("exito", "El proveedor fue cargado correctamente");
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
            return "proveedor_form.html";
        }
    }
}