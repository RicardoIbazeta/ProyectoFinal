package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Resenia;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseniaRepositorio extends JpaRepository<Resenia, String> {

    @Query("SELECT r FROM Resenia r WHERE r.id = :id")
    public Resenia buscarPorId(@Param("id") String id);

    @Query("SELECT r.estrellas FROM Resenia r")
    public List<Estrella> buscarCalificaciones();

}
