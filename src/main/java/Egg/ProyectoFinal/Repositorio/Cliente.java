
package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Contratacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cliente extends JpaRepository<Contratacion, String > {
    
}
