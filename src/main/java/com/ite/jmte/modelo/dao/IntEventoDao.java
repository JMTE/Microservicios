package com.ite.jmte.modelo.dao;

import java.util.List;

import com.ite.jmte.modelo.beans.Evento;

public interface IntEventoDao {

	//Creamos un metodo para listar todos los eventos existentes
	List<Evento> findAll();
	
	//Creamos un metodo para encontrar un evento por su idEvento
	Evento findEventoById(int idEvento);
	
	//Creamos un metodo para encontrar una lista de eventos que contengan una subcadena que le pasamos por parametro en su atributo nombre
	List<Evento> findEventoBySubcadena(String subcadena);
	
	//Creamos un metodo que nos liste los eventos destacados
	List<Evento> findEventosDestacados();
	
	//Creamos un metodo que nos liste los eventos activos
	List<Evento> findEventosActivos();
	
	//Creamos un metodo para dar de alta un evento
	int altaEvento (Evento evento);
	
	//Creamos un metodo que modifique un evento
	int modificarEvento(Evento evento);
	
	//Creamos un metodo que nos elimine un evento pasando por parametro el idEvento a eliminar
	int eliminarEvento(int idEvento);
}
 