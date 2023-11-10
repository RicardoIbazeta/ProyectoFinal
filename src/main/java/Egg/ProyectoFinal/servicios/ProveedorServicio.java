
package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.entidades.Proveedor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServicio {
    
    public void crearProveedor(Double precioHora, String descripcionServicio, List<String> rubros){
        Proveedor proveedor = new Proveedor();
        proveedor.setDescripcionServicio(descripcionServicio);
        proveedor.setPrecioHora(precioHora);
        proveedor.setRubros(rubros);
    }
    
}
