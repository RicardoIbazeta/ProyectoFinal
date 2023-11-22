package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.Repositorio.ProveedorRepositorio;
import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProveedorRepositorio proveedorRepositorio;
    private ContratacionRepositorio contratacionRepositorio;
    /* podemos acceder a los repositorios desde los servicios */
    @Autowired
    private ContratacionServicio contratacionServicio;
    @Autowired
    private ProveedorServicio proveedorServicio;
    
    @GetMapping("/contratar")
    public String registrar(ModelMap modelo) {
        
//        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
//        modelo.addAttribute("proveedores",proveedores);
        return "contratacion_form.html";
    }
    
    @PostMapping("/contratado")
    public String crearContratacion(@RequestParam String idCliente, @RequestParam String idProveedor) {

        Optional<Usuario> respuestaCliente = usuarioRepositorio.findById(idCliente);
        Optional<Usuario> respuestaProveedor = usuarioRepositorio.findById(idProveedor);
        
        /* agregar try-catch y modelo en caso de que falle */

        if (respuestaCliente.isPresent() && respuestaProveedor.isPresent()) {
            
            Usuario cliente = respuestaCliente.get();
            Usuario proveedor = respuestaProveedor.get();
            
            Contratacion contratacion = new Contratacion();
            
            contratacion.setCliente(cliente);
            contratacion.setProveedor(proveedor);
            contratacion.setAlta(new Date());
            contratacion.setEstadoContratacion(Estado.SOLICITADO);
            
            contratacionRepositorio.save(contratacion);
            //contratacionServicio.crearContratacion(cliente, proveedor); -> permite que la contratacion valide los datos
            //necesitamos try-catch para los exito/error y a su vez el contratacionServicio para que genere excepcioness
            // en catch //        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
                        //        modelo.addAttribute("proveedores",proveedores); -> para tener opciones desplegables en etiqueta select
        }
        return "index.html";

    }
    
   /* @GetMapping("/lista")
    public String listarProveedores(ModelMap modelo){
        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        
        return "contratacion_list.html";
    }*/

    /* Mapeo que lista todas las contrataciones */
    @GetMapping("/lista")
    public String listarContrataciones(ModelMap modelo){
        List<Contratacion> contrataciones = contratacionServicio.listarContrataciones() ;
        modelo.addAttribute("contrataciones", contrataciones);
        return "contratacion_list.html";
    }
    
    
    /* En desarrollo! Trae todas las contrataciones del id del usuario que esta en sesion   */
    @GetMapping("/misContrataciones/{id}")
    public String misContrataciones(@PathVariable String id, ModelMap modelo) {

            List<Contratacion> misContrataciones = contratacionServicio.misContrataciones(id);

            modelo.addAttribute("misContrataciones", misContrataciones);

            return "mis_contrataciones.html";
            
    }
    
    
}
