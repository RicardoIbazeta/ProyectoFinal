package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Cliente;
import Egg.ProyectoFinal.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String > {
    
    /* Querys de atributos heredados */
    
    /* Querys de busqueda individual */
    @Query("SELECT c FROM Cliente c WHERE c.id= :id")
    public Cliente buscarPorId(@Param ("id")String id);
    
    @Query("SELECT c FROM Cliente c WHERE c.documento= :documento")
    public Cliente buscarPorDocumento(@Param ("documento")String documento);
    
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Cliente buscarPorEmail(@Param("email")String email);
    
    /* Querys de busqueda por lista */        
    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    public List<Cliente> buscarPorNombre(@Param ("nombre")String nombre);
    
    @Query("SELECT c FROM Cliente c WHERE c.apellido= :apellido")
    public List<Cliente> buscarPorApellido(@Param ("apellido")String apellido);
    
    @Query("SELECT c FROM Cliente c WHERE c.telefono= :telefono")
    public List<Cliente> buscarPorTelefono(@Param ("telefono")String telefono);
    
    @Query("SELECT c FROM Cliente c WHERE c.direccion= :direccion")
    public List<Cliente> buscarPorDireccion(@Param ("direccion")String direccion);
    
    /* Query de atributos de entidad */
    /*@Query("SELECT c FROM Contratacion c WHERE c.cliente_id= :cliente_id")
    public List<Cliente> buscarPorContrataciones(@Param ("cliente_id")String cliente_id);*/
}
