package Egg.ProyectoFinal.controladores;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 1aed3b3 (merge con rama front)
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
import Egg.ProyectoFinal.servicios.RubroServicio;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ProveedorServicio;
>>>>>>> 92b9a40 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
=======
>>>>>>> 1aed3b3 (merge con rama front)
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
<<<<<<< HEAD

=======
    
<<<<<<< HEAD
<<<<<<< HEAD
=======
    
    
>>>>>>> 92b9a40 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
<<<<<<< HEAD
>>>>>>> 6cc7270 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
=======
=======
>>>>>>> 1aed3b3 (merge con rama front)
>>>>>>> a8e86fc (merge con rama front)
    @Autowired
    private ProveedorServicio proveedorServicio;

    @Autowired
    private RubroServicio rubroServicio;

    @GetMapping("/registrar")
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 1aed3b3 (merge con rama front)
    public String registrar(ModelMap modelo) {

        // intento de recopilacion de rubros para proveedor_form -> opcion cambio de privacidad de atributo
        //                                                       -> opcion hacer un servicio a RUBRO para traer coleccion  

        
//        
<<<<<<< HEAD
        List<Rubro> rubros = rubroServicio.listarRubros();
        modelo.addAttribute("rubros", rubros);
=======
//        List<Rubro> rubros = rubroServicio.listarRubros();
//        modelo.addAttribute("rubros",rubros);
<<<<<<< HEAD
=======
    public String registrar() {

>>>>>>> 92b9a40 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
<<<<<<< HEAD
>>>>>>> 6cc7270 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
=======
=======
>>>>>>> 1aed3b3 (merge con rama front)
>>>>>>> a8e86fc (merge con rama front)
        return "proveedor_form.html";
    }

    @PostMapping("/registro")
<<<<<<< HEAD
<<<<<<< HEAD
    public String registro(Double precioHora, String descripcionServicio, Rubro rubro, @RequestParam String nombre, String apellido, String documento, String email, String password, String password2, String telefono, String direccion, ModelMap modelo) {

        try {
            /* creacion provisoria del parametro rubros debido a falta de etiqueta en form */
=======
=======
>>>>>>> a8e86fc (merge con rama front)
    public String registro(Double precioHora, String descripcionServicio, List<Rubro> rubros, @RequestParam String nombre, String apellido, String documento, String email, String password, String password2,
<<<<<<< HEAD
            String telefono, String direccion, ModelMap modelo) {
=======
    public String registro(Double precioHora, String descripcionServicio, List<Rubro> rubros, @RequestParam String nombre, String apellido, String documento, String email, String password, String password2,String telefono, String direccion, Boolean tipoUsuario, ModelMap modelo) {
>>>>>>> 1aed3b3 (merge con rama front)

        try {
            /* creacion provisoria del parametro rubros debido a falta de etiqueta en form */
            Rubro gas = new Rubro();
            gas.setId("1");
            gas.setNombre("gasista");
            rubros.add(gas);
<<<<<<< HEAD
=======
            String telefono, String direccion, Boolean tipoUsuario, ModelMap modelo) {

        try {
>>>>>>> 92b9a40 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
=======
>>>>>>> 1aed3b3 (merge con rama front)
            proveedorServicio.crearProveedor(precioHora, descripcionServicio, rubros, nombre, apellido, documento, email, password, password2, telefono, direccion);
>>>>>>> 6cc7270 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
            
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
<<<<<<< HEAD
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 1aed3b3 (merge con rama front)
>>>>>>> a8e86fc (merge con rama front)
    
>>>>>>> 6cc7270 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
    /* Mapeo que lista todos los proveedores */
 /*@GetMapping("/lista")
    public String listarProveedores(ModelMap modelo){
        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        return "proveedor_list.html";
    }*/
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
=======
>>>>>>> 92b9a40 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
}
>>>>>>> 6cc7270 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
=======
=======
>>>>>>> 92b9a40 (creacion de ProveedorControlador, modificacion de Inheretance en usuario, modificacion usuarioServicio)
=======
>>>>>>> 1aed3b3 (merge con rama front)
}
>>>>>>> a8e86fc (merge con rama front)
