package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.servicios.RubroServicio;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import Egg.ProyectoFinal.servicios.ReseniaServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {

    @Autowired
    private ProveedorServicio proveedorServicio;
    @Autowired
    private ReseniaServicio reseniaServicio;
    @Autowired
    private RubroServicio rubroServicio;
    @Autowired
    private ContratacionServicio contratacionServicio;
    

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Rubro> rubros = rubroServicio.listarRubros();
        modelo.addAttribute("rubros", rubros);

        return "proveedor_form.html";
    }

    @PostMapping("/registro")
    public String registro(MultipartFile archivo, Double precioHora, String descripcionServicio,
            @RequestParam Rubro rubro, @RequestParam String nombre, String apellido, String documento,
            String email, String password, String password2, String telefono, String direccion, ModelMap modelo, Boolean altaBaja) {

        try {

            proveedorServicio.crearProveedor(archivo, precioHora, descripcionServicio, rubro, nombre,
                    apellido, documento, email, password, password2, telefono, direccion, altaBaja);
            modelo.put("exito", "El proveedor fue cargado correctamente");

            return "index.html";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            /* Se Inyecta la informacion proporcionada por el usuario previo a un error 
            y asi no tiene que volver ingresar todo nuevamente.
            La contraseña y el tipoUsuario siempre deberan ser ingresados */
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("documento", documento);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            modelo.put("direccion", direccion);
            modelo.put("precioHora", precioHora);
            modelo.put("descripcionServicio", descripcionServicio);
            // De esta forma se inyectan los rubros al desplegable
            List<Rubro> rubros = rubroServicio.listarRubros();
            modelo.addAttribute("rubros", rubros);

            return "proveedor_form.html";
        }
    }

    
    @PostMapping("/cancelar/{id}")
    public String cancelarContratacion(@PathVariable String id, ModelMap modelo) {

        Contratacion contratacion = contratacionServicio.getOne(id);

        contratacionServicio.cancelarContratacion(id);
        

        return "redirect:/proveedor/contrataciones";
    }
    @PostMapping("/aceptar/{id}")
    public String aceptarContratacion(@PathVariable String id, ModelMap modelo) {

        Contratacion contratacion = contratacionServicio.getOne(id);

        contratacionServicio.aceptarContratacion(id);
        

        return "redirect:/proveedor/contrataciones";
    }
    @PostMapping("/finalizar/{id}")
    public String finalizarContratacion(@PathVariable String id, ModelMap modelo) {

        Contratacion contratacion = contratacionServicio.getOne(id);

        contratacionServicio.finalizarContratacion(id);
        

        return "redirect:/proveedor/contrataciones";
    }
    
    
    

    
    @PostMapping("/calificar/{id}")
    public String registrarProveedor(RedirectAttributes redirectAttributes, @PathVariable String id,
            @RequestParam String comentario, @RequestParam String estrellas,
            ModelMap model) throws Exception {

        try {
            reseniaServicio.crear(comentario, estrellas, id);
            redirectAttributes.addFlashAttribute("exito", "El proveedor fue calificado con exito!");

            return "redirect:/usuarios";

        } catch (MiException e) {
            redirectAttributes.addFlashAttribute("error", "El proveedor NO fue calificado con exito!");

            return "redirect:/usuario";
        }
    } 

    /* Mapeo que lista todos los proveedores */
    @GetMapping("/lista")
    public String listarProveedores(ModelMap modelo) {

        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);

        return "proveedor_list.html";
    }
    
    
    
    
    @GetMapping("/contrataciones/{id}")
    public String historialContrataciones(ModelMap modelo,  @PathVariable String id) {

        List<Contratacion> historial = contratacionServicio.listarContrataciones();
        List<Contratacion> contrataciones = new ArrayList<Contratacion>();
        
        
        for (Contratacion contratacion : historial) {
            if (contratacion.getProveedor().getId().equals(id)) {
                    contrataciones.add(contratacion);
            }
        }
        modelo.addAttribute("contrataciones", contrataciones);

        return "contratacion_list.html";
    }
    
   

}
