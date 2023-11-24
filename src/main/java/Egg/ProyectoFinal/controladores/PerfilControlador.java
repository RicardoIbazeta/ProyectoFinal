
package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import static org.hibernate.criterion.Projections.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/usuario/Perfil")
public class PerfilControlador {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    //@GetMapping("/{id}/perfil")
    public String mostrarPerfilUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepositorio.findById(id);
        model.addAttribute("usuario", usuario);
        return "perfil.html";
    }

  
}
