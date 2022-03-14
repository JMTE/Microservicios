package com.ite.jmte.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Evento;
import com.ite.jmte.repository.IntEventoRepo;

/* @Service se encarga de gestionar las operaciones más importantes a nivel de la aplicación y aglutina llamadas a varios repositorios 
 * de forma simultánea. Su tarea fundamental es la de agregador.*/
@Service
public class EventoDaoImplMy8SP implements IntEventoDao{

	/*Lo que hace un autowired es buscar un objeto manejado (beans) que implementen determinada interfaz para hacer referencia a él.
	 *  De esta manera no es neceario crear una instancia nueva del objeto cada vez que se necesite la funcionalidad de determinada clase */
	@Autowired
	private IntEventoRepo eventoRepo;
	
	
	//Con este metodo vamos a listar todos los eventos existentes
	@Override
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		
		return eventoRepo.findAll();
	}

	//Con este metodo buscamos un evento por su idEvento
	@Override
	public Evento findEventoById(int idEvento) {
		// TODO Auto-generated method stub
		
		return eventoRepo.findById(idEvento).orElse(null);
	}
	
	//Con este metodo devolvemos una lista de eventos en la que su atributo nombre contiene la subcadena que pasamos por parametro,
	//Para ello utilizamos el metodo que creamos mediante query en nuestro repository
	@Override
	public List<Evento> findEventoBySubcadena(String subcadena) {
		// TODO Auto-generated method stub
		
		List<Evento> listaCoincidente=eventoRepo.findEventosByNameLike(subcadena);
		return listaCoincidente;
	}

	
	//Con este metodo devolvemos una lista con los eventos destacados
	@Override
	public List<Evento> findEventosDestacados() {
		// TODO Auto-generated method stub
		
		return eventoRepo.findEventosDestacados();
	}

	//Con este metodo devolvemos una lista con los eventos activos
	@Override
	public List<Evento> findEventosActivos() {
		// TODO Auto-generated method stub
		
		
		return eventoRepo.findEventosActivos();
	}

	
	//Con este evento damos de alta un evento nuevo
	@Override
	public int altaEvento(Evento evento) {
		// TODO Auto-generated method stub
		
		int filas=0;
		//Si el evento no existe lo añadimos
		if (eventoRepo.findAll().indexOf(evento)==-1) {
			
			try {
				eventoRepo.save(evento);
				filas=1;
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			return filas;
		//Si existe pues no lo modificamos
		}else {
			
			return filas;
		}
		
	}

	//Con este metodo modificamos un evento
	@Override
	public int modificarEvento(Evento evento) {
		// TODO Auto-generated method stub
		int filas=0;
		//Si no existe no lo añadimos
	
		if(!eventoRepo.findAll().contains(evento)) {
			
			return filas;
		}else {
		
		//Si existe lo modificamos
		
			try {
				eventoRepo.save(evento);
				filas=1;
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			return filas;
		
		}
		
	}

	//Con este metodo eliminamos un evento segun el idEvento que le pasamos por parametro
	@Override
	public int eliminarEvento(int idEvento) {
		// TODO Auto-generated method stub
		int filas=0;
		if(eventoRepo.findById(idEvento).orElse(null)==null) {
		return filas;	
		}
		
		try {
			eventoRepo.deleteById(idEvento);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return filas;
	}

}
