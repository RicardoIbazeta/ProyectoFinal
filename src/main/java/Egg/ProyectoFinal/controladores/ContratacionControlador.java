package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.Repositorio.ProveedorRepositorio;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contratacion")
public class ContratacionControlador {

    private UsuarioRepositorio usuarioRepositorio;
    private ContratacionRepositorio contratacionRepositorio;
    private ProveedorServicio proveedorServicio;
    private ProveedorRepositorio proveedorRepositorio;
    
    @GetMapping("/lista")
    public String listarProveedores(ModelMap modelo){
        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        
        return "contratacion_list.html";
    }

    
    
    @PostMapping("/contratar")
    public String crearContratacion(@RequestParam String idCliente, @RequestParam String idProveedor) {

        Optional<Usuario> respuestaCliente = usuarioRepositorio.findById(idCliente);
        Optional<Usuario> respuestaProveedor = usuarioRepositorio.findById(idProveedor);

        if (respuestaCliente.isPresent() && respuestaProveedor.isPresent()) {
            Usuario cliente = respuestaCliente.get();
            Usuario proveedor = respuestaProveedor.get();
            
            Contratacion contratacion = new Contratacion();
            contratacion.setCliente(cliente);
            contratacion.setProveedor(proveedor);
            contratacion.setAlta(new Date());
            contratacion.setEstadoContratacion(Estado.SOLICITADO);
            
            contratacionRepositorio.save(contratacion);
        }
        return "index.html";

    }

}
