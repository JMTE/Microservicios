package com.ite.jmte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import com.ite.jmte.modelo.beans.Reserva;



//Al extender a JpaRepository, podemos utilizar los metodos y clases integradas dentro de Spring Boot para tratar bases de datos

public interface IntReservaRepo extends JpaRepository<Reserva, Integer>{
	
	@Query("SELECT r FROM Reserva r WHERE r.evento.idEvento = ?1")
	public List<Reserva> findReservasByIdEvento(int idEvento);

}
