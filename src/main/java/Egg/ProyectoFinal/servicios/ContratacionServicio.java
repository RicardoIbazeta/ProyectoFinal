/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.ContratacionRepositorio;
import Egg.ProyectoFinal.entidades.Contratacion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContratacionServicio {
    
    private ContratacionRepositorio contratacionRepositorio;
    
    public List listarContrataciones(){
        

        List<Contratacion> contrataciones = new ArrayList();

        //usamos el metodo que nos trae JpaRepositori "findAll" que nos trae todo lo que encuentra
        contrataciones = contratacionRepositorio.findAll();

        return contrataciones;
    }
    
}
