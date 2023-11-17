/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.RubroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rubro")
public class RubroControlador {
    
    @Autowired
    private RubroServicio rubroServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "rubro_form.html";
    }
            
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {
            
            rubroServicio.registrar(nombre);
            
            modelo.put("exito", "Rubro registrado correctamente");
            return "index.html";
            
        } catch (MiException ex){
            
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
           
            return "rubro_form.html";
            
        }
    }
    
    
    
    
}
