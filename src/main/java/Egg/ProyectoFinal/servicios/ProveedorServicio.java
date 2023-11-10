
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Proveedor;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServicio {
    
    public void crearProveedor(Double precioHora, String descripcionServicio, String rubro){
        Proveedor proveedor = new Proveedor();
        proveedor.setDescripcionServicio(descripcionServicio);
        proveedor.setPrecioHora(precioHora);
        proveedor.setRubros(rubro);
    }
    
}
