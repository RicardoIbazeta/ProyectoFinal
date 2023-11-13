package Egg.ProyectoFinal;

import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoFinalApplication {

	public static void main(String[] args) throws MiException {
		SpringApplication.run(ProyectoFinalApplication.class, args);
                
                
	}

}
