
package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import Egg.ProyectoFinal.servicios.ReseniaServicio;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import java.util.List;
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
    UsuarioServicio usuarioServicio;
    
    @Autowired
    ProveedorServicio proveedorServicio;
    
    @Autowired
    ContratacionServicio contratacionServicio;
    
    @Autowired
    ReseniaServicio reseniaServicio;
            
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/calificar/{id}")
    public String calificar(ModelMap modelo, @PathVariable String id/*, @RequestParam String idProveedor*/) {

        /*Usuario usuario = usuarioServicio.getOne(id);
        List<Contratacion> contratacionesUsuario = contratacionServicio.misContrataciones(id);
        Proveedor proveedor = proveedorServicio.getOne(id);
        List<Contratacion> contratacionesProveedor = contratacionServicio.ContratacionesProveedor(id);*/

        Contratacion contratacion = contratacionServicio.getOne(id);
        
        modelo.addAttribute("contratacion", contratacion);
        return "resenia_form.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/calificado/{id}")
    public String calificarProveedor(/*RedirectAttributes redirectAttributes,*/ @PathVariable String id,
            @RequestParam String comentario, @RequestParam String estrellas,
            ModelMap modelo) throws Exception {

        try {
            Contratacion contratacion = contratacionServicio.getOne(id);
            reseniaServicio.crear(comentario, Estrella.valueOf(estrellas), contratacion.getProveedor().getId());
            //redirectAttributes.addFlashAttribute("exito", "El proveedor fue calificado con exito!");
            modelo.put("exito", "El proveedor fue calificado con exito!");
            return "redirect:../proveedor/lista";

        } catch (MiException e) {
            //redirectAttributes.addFlashAttribute("error", "El proveedor NO fue calificado con exito!");
            modelo.put("error", "El proveedor NO fue calificado con exito!");
            Proveedor proveedor = proveedorServicio.getOne(id);
            modelo.addAttribute("proveedor", proveedor);
            
            return "redirect:../proveedor/lista";
        }
    }
    
    
}
