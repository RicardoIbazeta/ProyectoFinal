package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Rubro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepositorio extends  JpaRepository<Rubro, String >{
    
   @Query("SELECT r FROM Rubro r WHERE r.id = :id")
   public Rubro buscarPorId(@Param("id") String id);
  
   @Query("SELECT r FROM Rubro r WHERE r.nombre = :nombre")
   public Rubro buscarPorRubro(@Param("nombre") String nombre);
}


