package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.Repositorio.ProveedorRepositorio;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import Egg.ProyectoFinal.servicios.ReseniaServicio;
import Egg.ProyectoFinal.servicios.RubroServicio;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/contratacion")
public class ContratacionControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    @Autowired
    private ContratacionRepositorio contratacionRepositorio;
    @Autowired
    private ContratacionServicio contratacionServicio;
    @Autowired
    private ProveedorServicio proveedorServicio;
    @Autowired
    private RubroServicio rubroServicio;
    @Autowired
    private ReseniaServicio reseniaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/contratar/{id}")
    public String contratar(@PathVariable String id, ModelMap modelo) {
        
        System.out.println("Proveedor Id:"+id);
        
       try {
        Proveedor proveedor = proveedorRepositorio.buscarPorId(id);
        List<Rubro> rubros = rubroServicio.listarRubrosPorId(id);

        modelo.addAttribute("proveedor", proveedor);
        modelo.addAttribute("rubros", rubros);
        return "contratacion_form.html";
    } catch (Exception e) {
        e.printStackTrace();
        // Manejar el error de alguna manera apropiada
        return "error.html";
    }
    }

    @PostMapping("/contratado/{idProveedor}")
    public String crearContratacion(@RequestParam String idCliente, @PathVariable String idProveedor) {

        Optional<Usuario> respuestaCliente = usuarioRepositorio.findById(idCliente);
        Optional<Proveedor> respuestaProveedor = proveedorRepositorio.findById(idProveedor);

        if (respuestaCliente.isPresent() && respuestaProveedor.isPresent()) {

            Usuario cliente = respuestaCliente.get();
            Proveedor proveedor = respuestaProveedor.get();
            Contratacion contratacion = new Contratacion();

            contratacion.setCliente(cliente);
            contratacion.setProveedor(proveedor);
            contratacion.setAlta(new Date());
            contratacion.setEstadoContratacion(Estado.SOLICITADO);

            contratacionRepositorio.save(contratacion);
        }

        return "index.html";
    }

    //ADMIN
    /* Mapeo que lista todas las contrataciones */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR')")
    @GetMapping("/lista")
    public String listarContrataciones(ModelMap modelo) {

        List<Contratacion> contrataciones = contratacionServicio.listarContrataciones();
        modelo.addAttribute("contrataciones", contrataciones);

        return "contratacion_list.html";
    }

    /* En desarrollo! Trae todas las contrataciones del id del usuario que esta en sesion   */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_PROVEEDOR')")
    @GetMapping("/historial/{id}")
    public String misContrataciones(@PathVariable String id, ModelMap modelo) {

        List<Contratacion> contrataciones = contratacionServicio.ContratacionesProveedor(id);
        modelo.addAttribute("contrataciones", contrataciones);

        return "contratacion_list";
    }
 
}
