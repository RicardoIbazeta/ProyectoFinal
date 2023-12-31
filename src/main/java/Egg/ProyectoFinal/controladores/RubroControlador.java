package Egg.ProyectoFinal.controladores;

import Egg.ProyectoFinal.entidades.Rubro;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.RubroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rubro")
public class RubroControlador {

    @Autowired
    private RubroServicio rubroServicio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/registrar")
    public String registrar() {
        return "rubro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {

            rubroServicio.registrar(nombre);
            modelo.put("exito", "Rubro registrado correctamente");

            return "index.html";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);

            return "rubro_form.html";
        }
    }

    /* Mapeo que lista todos los rubros */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROVEEDOR')")
    @GetMapping("/lista")
    public String listarRubros(ModelMap modelo) {

        List<Rubro> rubros = rubroServicio.listarRubros();
        modelo.addAttribute("rubros", rubros);

        return "rubro_list.html";
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Rubro>> buscarRubro(@RequestParam String query) {
        List<Rubro> resultados = (List<Rubro>) rubroServicio.buscarPorRubro(query);
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }
}
