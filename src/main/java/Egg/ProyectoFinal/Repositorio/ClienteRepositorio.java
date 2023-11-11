
package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Contratacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepositorio extends JpaRepository<Contratacion, String > {
    
}
