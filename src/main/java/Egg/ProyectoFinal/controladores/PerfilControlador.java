
package Egg.ProyectoFinal.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/usuario/Perfil")
public class PerfilControlador {

    @GetMapping("/usuario/Perfil")
    public String verPerfilAdmin(Model model) {
        // Lógica para mostrar el perfil del administrador
        return "perfil.html";
    }

  
}
