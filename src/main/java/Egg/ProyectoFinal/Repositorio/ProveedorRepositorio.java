package Egg.ProyectoFinal.Repositorio;

import Egg.ProyectoFinal.entidades.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String > {
    
    /* Querys de atributos heredados */
   
    @Query("SELECT p FROM Proveedor p WHERE p.altaBaja= :altaBaja")
    public List<Proveedor> filtrarPorAlta(@Param("altaBaja") boolean altaBaja);
    @Query("SELECT p FROM Proveedor p WHERE p.id= :id")
    public Proveedor buscarPorId(@Param ("id")String id);
    @Query("SELECT p FROM Proveedor p WHERE p.documento= :documento")
    public Proveedor buscarPorDocumento(@Param ("documento")String documento);
    
    @Query("SELECT p FROM Proveedor p WHERE p.email = :email")
    public Proveedor buscarPorEmail(@Param("email")String email);
    
    /* Querys de busqueda por lista */        
    @Query("SELECT p FROM Proveedor p WHERE p.nombre = :nombre")
    public List<Proveedor> buscarPorNombre(@Param ("nombre")String nombre);
    
    @Query("SELECT p FROM Proveedor p WHERE p.apellido= :apellido")
    public List<Proveedor> buscarPorApellido(@Param ("apellido")String apellido);
    
    @Query("SELECT p FROM Proveedor p WHERE p.telefono= :telefono")
    public List<Proveedor> buscarPorTelefono(@Param ("telefono")String telefono);
    
    @Query("SELECT p FROM Proveedor p WHERE p.direccion= :direccion")
    public List<Proveedor> buscarPorDireccion(@Param ("direccion")String direccion);

    /* Querys de atributos de entidad */
    @Query("SELECT p FROM Proveedor p WHERE p.calificacion= :calificacion")
    public List<Proveedor> buscarPorCalificacion(@Param ("calificacion")String calificacion);
       
}