package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    
    
    @Query("Select u FROM usuario u WHERE u.usuario_id= :id")
    public UsuarioRepositorio buscarPorId(String id);
        
    @Query("SELECT u FROM usuario u WHERE u.nombre = :nombre")
    public UsuarioRepositorio buscarPorNombre(String nombre);
    
    @Query("SELECT u FROM usuario u WHERE u.documento = :documento")
    public UsuarioRepositorio buscarPorDni(String documento);
}

