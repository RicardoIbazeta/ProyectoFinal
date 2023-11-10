/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Egg.ProyectoFinal.entidades.repositorios;

import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Usuario, String > {
    
    
    @Query("SELECT p FROM Proveedor p WHERE p.nombre = :nombre ")
    public Proveedor buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT p FROM Proveedor p WHERE p.rubro = :rubro")
    public Proveedor buscarPorRubro (@Param("rubro") String rubro);
    
    
    
        

}
