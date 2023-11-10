
package Repositorios;

import Egg.ProyectoFinal.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    
    
    @Query("Select u FROM usuario u WHERE u.usuario_id= :id")
    public Usuario buscarPorId(String id);
        
    @Query("SELECT u FROM usuario u WHERE u.nombre = :nombre")
    public Usuario buscarPorNombre(String nombre);
    
    @Query("SELECT u FROM usuario u WHERE u.documento = :documento")
    public Usuario buscarPorDni(String documento);
}
