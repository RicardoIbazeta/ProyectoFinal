package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @GetMapping("/registrarseBotones")
    public String registrarseBotones(){
        
        return "registrarse-botones.html";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, ModelMap modelo){
        if(error !=null){
            modelo.put("error","Usuario o Contraseña Invalidos");
            
        }
        return "login.html";
    }


//    @GetMapping("/login")
//    public String login() {
//        return "login.html";
//    }
    
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    } 



}
