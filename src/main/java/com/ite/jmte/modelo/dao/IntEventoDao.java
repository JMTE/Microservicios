package com.ite.jmte.modelo.dao;

import java.util.List;

import com.ite.jmte.modelo.beans.Evento;

public interface IntEventoDao {

	List<Evento> findAll();
	
	Evento findEventoById(int idEvento);
	
	List<Evento> findEventoBySubcadena(String subcadena);
	
	List<Evento> findEventosDestacados();
	
	List<Evento> findEventosActivos();
	
	
	int altaEvento (Evento evento);
	
	int modificarEvento(Evento evento);
	
	int eliminarEvento(int idEvento);
}
 