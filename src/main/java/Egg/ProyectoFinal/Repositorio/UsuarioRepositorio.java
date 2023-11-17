package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String>{
    
    /* Querys de busqueda individual */
    @Query("SELECT u FROM Usuario u WHERE u.id= :id")
    public Usuario buscarPorId(@Param ("id")String id);
    
    @Query("SELECT u FROM Usuario u WHERE u.documento= :documento")
    public Usuario buscarPorDocumento(@Param ("documento")String documento);
    
    // Metodo para buscar usuario por email
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email")String email);
    
    /* Querys de busqueda por lista */
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    public List<Usuario> buscarPorNombre(@Param ("nombre")String nombre);
    
    @Query("SELECT u FROM Usuario u WHERE u.apellido= :apellido")
    public List<Usuario> buscarPorApellido(@Param ("apellido")String apellido);
    
    @Query("SELECT u FROM Usuario u WHERE u.telefono= :telefono")
    public List<Usuario> buscarPorTelefono(@Param ("telefono")String telefono);
    
    @Query("SELECT u FROM Usuario u WHERE u.direccion= :direccion")
    public List<Usuario> buscarPorDireccion(@Param ("direccion")String direccion);
            
    /*@Query ("SELECT u FROM usuario u WHERE u.tipoUsuario = :true")
    public UsuarioRepositorio buscarPorProveedor (@Param ("tipoUsuario") Boolean tipoUsuario);*/
    
    
        
    }
    
    




