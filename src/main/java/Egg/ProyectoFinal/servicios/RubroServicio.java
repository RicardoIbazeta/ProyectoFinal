/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.RubroRepositorio;
import Egg.ProyectoFinal.entidades.Proveedor;
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
    @Autowired
    private ProveedorServicio proveedorServicio;
    
    public List<Rubro> listarRubros() {

        List<Rubro> rubros = new ArrayList();

        rubros = rubroRepositorio.findAll();

        return rubros;
    }
    
    public List<Rubro> listarRubrosPorId(String id) {
        Proveedor proveedor = proveedorServicio.getOne(id);
        List<Rubro> rubros = new ArrayList();
        List<Rubro> rubros1 = new ArrayList();
        rubros1 = rubroRepositorio.findAll();
        
        for (Rubro rubro : rubros1) {
            if (proveedor.getRubro().equals(rubro)) {
                rubros.add(rubro);
            }
        }

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
