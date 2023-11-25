
package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.Repositorio.UsuarioRepositorio;
import Egg.ProyectoFinal.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/perfil2")
public class PerfilControlador {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @GetMapping("/perfil2/{id}")
    public String mostrarPerfilUsuario(@PathVariable String id, Model model) {
        
        Usuario usuario = (Usuario) usuarioRepositorio.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        
        return "perfil.html";
    }  
}
