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
    
    @Query("SELECT c.proveedor.id FROM Contratacion c WHERE c.id = :contratacionId")
    String buscarIdProveedorPorContratacionId(@Param("contratacionId") String contratacionId);

    @Query("SELECT c.cliente.id FROM Contratacion c WHERE c.id = :contratacionId")
    String buscarIdClientePorContratacionId(@Param("contratacionId") String contratacionId);


}
