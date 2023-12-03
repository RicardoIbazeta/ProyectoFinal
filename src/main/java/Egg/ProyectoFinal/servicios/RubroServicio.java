package Egg.ProyectoFinal.servicios;

import Egg.ProyectoFinal.Repositorio.RubroRepositorio;
import Egg.ProyectoFinal.entidades.Proveedor;
import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RubroServicio {

    @Autowired
    private RubroRepositorio rubroRepositorio;
    @Autowired
    private ProveedorServicio proveedorServicio;

    @Transactional
    public void registrar(String nombre) throws MiException {

        validarRubro(nombre);
        Rubro rubro = new Rubro();
        rubro.setNombre(nombre);

        rubroRepositorio.save(rubro);
    }

    @Transactional
    public void modificarRubro(String id, String nombre) {

        Optional<Rubro> respuesta = rubroRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Rubro rubro = new Rubro();

            rubro.setId(nombre);

            rubroRepositorio.save(rubro);
        }
    }

    @Transactional
    public void eliminar(Rubro rubro) {

        Optional<Rubro> respuestaRubro = rubroRepositorio.findById(rubro.getId());
        if (respuestaRubro.isPresent()) {

            rubroRepositorio.delete(rubro);
        }
    }

    public List<Rubro> listarRubros() {

        List<Rubro> rubros = new ArrayList();

        rubros = rubroRepositorio.findAll();

        return rubros;
    }

    public List<Rubro> listarRubrosPorId(String id) {

        Proveedor proveedor = proveedorServicio.getOne(id);
        List<Rubro> rubros = new ArrayList();
        List<Rubro> rubros1 = new ArrayList();
        rubros1 = rubroRepositorio.findAll();

        for (Rubro rubro : rubros1) {
            if (proveedor.getRubro().equals(rubro)) {
                rubros.add(rubro);
            }
        }

        return rubros;
    }

    private void validarRubro(String nombre) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("Debes indicar el rubro");
        }
    }
    
    public List<Rubro> buscarPorRubro(String query) {
        return (List<Rubro>) rubroRepositorio.buscarPorRubro(query);
    }

}
