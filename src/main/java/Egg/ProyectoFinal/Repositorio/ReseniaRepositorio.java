
package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Resenia;
import Egg.ProyectoFinal.enumeraciones.Estrella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseniaRepositorio extends JpaRepository<Resenia,String> {
    
}
