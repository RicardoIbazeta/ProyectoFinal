package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.RubroServicio;
import java.util.ArrayList;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {
    
    @Autowired
    private ProveedorServicio proveedorServicio;

    @Autowired
    private RubroServicio rubroServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        // intento de recopilacion de rubros para proveedor_form -> opcion cambio de privacidad de atributo
        //                                                       -> opcion hacer un servicio a RUBRO para traer coleccion  

        List<Rubro> rubros = rubroServicio.listarRubros();
        modelo.addAttribute("rubros", rubros);
        return "proveedor_form.html";
    }

    @PostMapping("/registro")
    public String registro(Double precioHora, String descripcionServicio,@RequestParam Rubro rubro, @RequestParam String nombre, String apellido, String documento, String email, String password, String password2, String telefono, String direccion, ModelMap modelo) {

        try {
            /* creacion provisoria del parametro rubros debido a falta de etiqueta en form */
            
            proveedorServicio.crearProveedor(precioHora, descripcionServicio, rubro, nombre, apellido, documento, email, password, password2, telefono, direccion);

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

    /* Mapeo que lista todos los proveedores */
    @GetMapping("/lista")
    public String listarProveedores(ModelMap modelo){
        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        return "proveedor_list.html";
    }
    
    
    
}

