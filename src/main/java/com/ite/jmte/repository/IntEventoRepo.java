package com.ite.jmte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ite.jmte.modelo.beans.Evento;

public interface IntEventoRepo extends JpaRepository<Evento, Integer> {

	@Query("select e from Evento e where e.nombre like  %:nombre% ")
	List<Evento> findEventosByNameLike(@Param("nombre") String subcadena);
	
}
