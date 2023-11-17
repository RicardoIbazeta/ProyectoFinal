/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Contratacion;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratacionRepositorio extends JpaRepository<Contratacion, String> {
    
    /* Querys de busqueda individual */
    @Query("SELECT c FROM Contratacion c WHERE c.id= :id")
    public Contratacion buscarPorId(@Param ("id")String id);
    
    /* Querys de busqueda por lista */        
    @Query("SELECT c FROM Contratacion c WHERE c.alta = :alta")
    public List<Contratacion> buscarPorAlta(@Param ("alta")Date alta);
    
    @Query("SELECT c FROM Contratacion c WHERE c.estadoContratacion= :estadoContratacion")
    public List<Contratacion> buscarPorEstadoContratacion(@Param ("estadoContratacion")String estadoContratacion);
    
    //verificar funcionamiento -> posible error en tipo de dato de los parametros
    /*@Query("SELECT c FROM Contratacion c WHERE c.cliente_id= :cliente_id")
    public List<Contratacion> buscarPorClienteId(@Param ("cliente_id")String cliente_id);
    
    @Query("SELECT c FROM Contratacion c WHERE c.proveedor_id= :proveedor_id")
    public List<Contratacion> buscarPorProveedorId(@Param ("proveedor_id")String proveedor_id);*/
}
