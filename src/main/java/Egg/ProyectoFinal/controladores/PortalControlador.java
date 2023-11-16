package Egg.ProyectoFinal.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String index() {

        return "index.html";

    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    //no tener en cuenta, lo cree para poder acceder a la vista de iniciar sesion y poder darle formato..
    @GetMapping("/login")
    public String login(){
        
        return "login.html";
    }
}
