package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    
    
    @Query("Select u FROM usuario u WHERE u.id= :id")
    public UsuarioRepositorio buscarPorId(@Param ("id")String id);
        
    @Query("SELECT u FROM usuario u WHERE u.nombre = :nombre")
    public UsuarioRepositorio buscarPorNombre(@Param ("nombre")String nombre);
    
    /*@Query("SELECT u FROM usuario u WHERE u.documento = :documento")
    public UsuarioRepositorio buscarPorDni(@Param ("documento")String documento);*/
    
    /*@Query ("SELECT u FROM usuario u WHERE u.tipoUsuario = :true")
    public UsuarioRepositorio buscarPorProveedor (@Param ("tipoUsuario") Boolean tipoUsuario);*/
    
    
    
}



