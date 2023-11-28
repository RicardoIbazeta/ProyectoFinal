package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Contratacion;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.ContratacionServicio;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ContratacionServicio contratacionServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "usuario_form.html";
    }

    @PostMapping("/registro")
    public String registro(MultipartFile archivo, @RequestParam String nombre, String apellido,
            String documento, String email, String password, String password2, String telefono,
            String direccion, boolean AltaBaja, ModelMap modelo) {

        try {
            usuarioServicio.crearUsuario(archivo, nombre, apellido, documento, email, password, password2, telefono, direccion, AltaBaja);
            modelo.put("exito", "El usuario fue cargado correctamente");

            return "index.html";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());

            /* Se Inyecta la informacion proporcionada por el usuario previo a un error 
            y asi no tiene que volver ingresar todo nuevamente.
            La contraseña y el tipoUsuario siempre deberan ser ingresados */
            modelo.put("archivo", archivo);
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("documento", documento);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            modelo.put("direccion", direccion);
            modelo.put("AltaBaja", AltaBaja);

            return "usuario_form.html";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/altaBaja/{id}")
    public String altaBajaUsuario(@PathVariable String id, ModelMap modelo) {

        Usuario usuario = usuarioServicio.getOne(id);

        usuarioServicio.darAltaBaja(usuario);
        modelo.put("usuario", usuario);

        return "redirect:/usuario/lista";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id, ModelMap modelo) {

        Usuario usuario = usuarioServicio.getOne(id);

        usuarioServicio.eliminar(usuario);
        modelo.put("usuario", usuario);

        return "redirect:/usuario/lista";
    }

    /* Mapeo que lista todos los usuarios */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR')")
    @GetMapping("/lista")
    public String listarUsuarios(ModelMap modelo) {

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "usuario_list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN','ROLE_PROVEEDOR' )")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        modelo.put("usuario", usuario);

        return "perfil.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PROVEEDOR' )")
    @GetMapping("/editarPerfil/{id}")
    public String editarPerfil(ModelMap modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        modelo.addAttribute("usuario", usuario);

        return "perfil_form.html";
    } 
    
    //Verifica que el usuario sea user o admin
    //si es usuario lo manda a modificar usuario
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PROVEEDOR' )")
    @PostMapping("/editarPerfil/{id}")
    public String actualizar(MultipartFile archivo, @PathVariable String id, @RequestParam String nombre,
            @RequestParam String apellido, @RequestParam String email,/* @RequestParam*/ String password,
            @RequestParam String telefono, @RequestParam String direccion, ModelMap modelo) {

        try {
            usuarioServicio.modificarUsuario(archivo, id, nombre, apellido, email, password, telefono, direccion);
            modelo.put("exito", "Usuario actualizado correctamente!");

            return "inicio.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "usuario_modificar.html";
        }
    } 

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/contrataciones/{id}")
    public String historialContrataciones(ModelMap modelo,  @PathVariable String id) {

        List<Contratacion> historial = contratacionServicio.listarContrataciones();
        List<Contratacion> contrataciones = new ArrayList<Contratacion>();
        for (Contratacion contratacion : historial) {
            if (contratacion.getCliente().getId().equals(id)) {
                    contrataciones.add(contratacion);
            }
        }
        modelo.addAttribute("contrataciones", contrataciones);

        return "contratacion_list.html";
    }
}
