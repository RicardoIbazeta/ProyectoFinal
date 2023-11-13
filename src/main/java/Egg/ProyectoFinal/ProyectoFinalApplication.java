package Egg.ProyectoFinal;

import Egg.ProyectoFinal.entidades.Usuario;
import Egg.ProyectoFinal.enumeraciones.Estado;
import Egg.ProyectoFinal.excepciones.MiException;
import Egg.ProyectoFinal.servicios.UsuarioServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoFinalApplication {

	public static void main(String[] args) throws MiException {
		SpringApplication.run(ProyectoFinalApplication.class, args);
                
                //UsuarioServicio us = new UsuarioServicio();
                //us.crearUsuario("pepe", "martinez", "40123987", "pepe@gmail.com", "123456", Estado.EN_PROCESO, "1112349876", "las colinas 123", Boolean.TRUE);
                
	}

}
