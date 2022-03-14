package com.ite.jmte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ite.jmte.modelo.beans.Evento;

//Al extender a JpaRepository, podemos utilizar los metodos y clases integradas dentro de Spring Boot para tratar bases de datos

public interface IntEventoRepo extends JpaRepository<Evento, Integer> {

	/* Tambien podemos usar la anotación Query de Spring Data, directamente modificando solo el repository. 
	 * En este caso, creamos un método que devuelva la lista de eventos que contenga en su atributo nombre la cadena de caracteres que consideremos*/
	
	@Query("select e from Evento e where e.nombre like  %:nombre% ")
	List<Evento> findEventosByNameLike(@Param("nombre") String subcadena);
	
	@Query("select e from Evento e where e.estado='activo'")
	List<Evento> findEventosActivos();
	
	@Query("select e from Evento e where e.destacado='s'")
	List<Evento> findEventosDestacados();

	
}
