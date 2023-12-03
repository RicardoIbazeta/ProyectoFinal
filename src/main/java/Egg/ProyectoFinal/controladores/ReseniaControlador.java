    
package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Resenia;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import Egg.ProyectoFinal.servicios.ReseniaServicio;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import java.lang.System.Logger;
import static org.hibernate.internal.CoreLogging.logger;
import static org.hibernate.internal.HEMLogging.logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
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
    public String calificar(ModelMap modelo, @PathVariable String id/*, String idContratacion, String idProveedor, String idCliente*/) {

        
        
        Contratacion contratacion = contratacionServicio.getOne(id);
        Proveedor proveedor = proveedorServicio.getOne(contratacion.getProveedor().getId());
        Usuario usuario = usuarioServicio.getOne(contratacion.getCliente().getId());
        
        String idProveedor = contratacion.getProveedor().getId();
        String idCliente = contratacion.getCliente().getId();
        
        System.out.println("idProveedor :" + idProveedor);
        System.out.println("idContratacion : "+ id);
        System.out.println("idCliente : "+idCliente);
        
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("proveedor", proveedor);
        modelo.addAttribute("contratacion", contratacion);
        return "resenia_form.html";
    }

   

    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/calificado/{id}")
    public String calificarProveedor(RedirectAttributes redirectAttributes,
            @PathVariable("id") String id ,
            @RequestParam String comentario, 
            @RequestParam String estrellas,
            ModelMap modelo) throws Exception {
        
        try {
            Estrella estrella = Estrella.valueOf(estrellas);
        
            Contratacion contratacion = contratacionServicio.getOne(id);
            
            Proveedor proveedor = proveedorServicio.getOne(contratacion.getProveedor().getId());
            
            Usuario cliente = usuarioServicio.getOne(contratacion.getCliente().getId());

            reseniaServicio.crear(comentario, estrella,proveedor,cliente,contratacion);
        
            redirectAttributes.addFlashAttribute("exito", "El proveedor fue calificado con exito!");
            
            modelo.put("exito", "El proveedor fue calificado con exito!");
            
            return "proveedor_list";
            
        } catch (Exception e) {
            
            redirectAttributes.addFlashAttribute("error", "Se produjo un error al calificar al proveedor. Por favor, inténtelo de nuevo.");
            
            return "redirect:/error";
        }
    }
    
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminarResenia(@PathVariable String id, ModelMap modelo) {

        Resenia resenia = reseniaServicio.getOne(id);

        reseniaServicio.eliminarResenia(resenia);
        modelo.put("resenia", resenia);

        return "redirect:/resenia/lista";
    }
    
    @GetMapping("/lista/{id}")
    public String listarResenias(ModelMap modelo, @PathVariable String id){
        
        List<Resenia> resenias=new ArrayList();
        resenias=reseniaServicio.listarResenia(id);
        modelo.addAttribute("resenias", resenias);
     
        
        return "resenia_list.html";
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////LISTADO DE TODAS LAS RESEÑAS PARA EL ADMIN///////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/lista")
    public String listarReseniasAdmin(ModelMap modelo){
        
        List<Resenia> resenias=new ArrayList();
        resenias=reseniaServicio.listarReseniaAdmin();
        modelo.addAttribute("resenias", resenias);
     
        
        return "resenia_list.html";
    }
    
    
}
    

    
    
    





