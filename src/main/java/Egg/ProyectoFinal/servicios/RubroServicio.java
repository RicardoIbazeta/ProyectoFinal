/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.RubroRepositorio;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RubroServicio {
    
    
    @Autowired
    private RubroRepositorio rubroRepositorio;
    
    public List<Rubro> listarRubros() {

        List<Rubro> rubros = new ArrayList();

        rubros = rubroRepositorio.findAll();

        return rubros;
    }
    
    

    
    @Transactional
    public void registrar(String nombre) throws MiException {
        validarRubro(nombre);
        Rubro rubro=new Rubro();
        rubro.setNombre(nombre);
        rubroRepositorio.save(rubro);
        
    }
    
    private void validarRubro(String nombre) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("Debes completar tu nombre");
        }
        
    }
    
    
    
}