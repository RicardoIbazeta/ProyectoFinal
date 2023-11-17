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

<<<<<<< HEAD
=======
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login.html";
//    }
    
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    } 
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, ModelMap modelo){
        if(error !=null){
            modelo.put("error","Usuario o Contraseña Invalidos");
            
        }
        return "login.html";
>>>>>>> 8e98b87 (Seguridad web, encriptacion de password, ROL Proveedor)
    }
<<<<<<< HEAD
<<<<<<< HEAD

    @GetMapping("/registrarseBotones")
    public String registrarseBotones(){
        
        return "registrarse-botones.html";
=======
=======
>>>>>>> 97961be (ultima version. no ingresa session)
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, ModelMap modelo){
        if(error !=null){
            modelo.put("error","Usuario o Contraseña Invalidos");
            
        }
        return "login.html";
>>>>>>> af2e986 (asdasd)
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, ModelMap modelo){
        if(error !=null){
            modelo.put("error","Usuario o Contraseña Invalidos");
            
        }
        return "login.html";
    }
<<<<<<< HEAD
    
    
    
=======
>>>>>>> ade606e (pull main)
=======
    
>>>>>>> f2539ca (ultima version. no ingresa session)


//    @GetMapping("/login")
//    public String login() {
//        return "login.html";
//    }
    
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    } 



}
