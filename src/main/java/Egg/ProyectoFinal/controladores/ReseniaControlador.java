
package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import Egg.ProyectoFinal.servicios.ReseniaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/resenia")
public class ReseniaControlador {
    
    @Autowired
    ProveedorServicio proveedorServicio;
    
    @Autowired
    ReseniaServicio reseniaServicio;
    
    @Autowired
    ContratacionServicio contratacionServicio;
    
            
    
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
@GetMapping("/calificar/{id}")
public String mostrarFormularioResena(ModelMap modelo, @PathVariable String contratacionId) {
    Contratacion contratacion = contratacionServicio.getOne(contratacionId);
    
    // Obtén el proveedor asociado a la contratación
    Proveedor proveedor = contratacion.getProveedor();
    
    modelo.addAttribute("proveedor", proveedor);

    // Agrega lógica adicional según tus necesidades

    return "resenia_form.html";
}

@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
@PostMapping("/calificado/{id}")
public String calificarProveedor(RedirectAttributes redirectAttributes, @PathVariable String id,
        @RequestParam String comentario, @RequestParam Estrella estrellas,
        ModelMap modelo) throws Exception {

    try {
        reseniaServicio.crear(comentario, estrellas, id);
        redirectAttributes.addFlashAttribute("exito", "El proveedor fue calificado con éxito!");
        Proveedor proveedor = proveedorServicio.getOne(id);
        modelo.addAttribute("proveedor", proveedor);

        return "redirect:../proveedor/lista";

    } catch (Exception e) {
        e.printStackTrace();  // Imprime la traza de la excepción en la consola del servidor.
        redirectAttributes.addFlashAttribute("error", "Ocurrió un error al calificar al proveedor.");
        return "redirect:../proveedor/lista";
    }
}


    
}
