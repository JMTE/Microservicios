package com.ite.jmte.modelo.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Reserva;

import com.ite.jmte.repository.IntReservaRepo;

/* @Service se encarga de gestionar las operaciones más importantes a nivel de la aplicación y aglutina llamadas a varios repositorios 
 * de forma simultánea. Su tarea fundamental es la de agregador.*/
@Service
public class ReservaDaoImplMy8SP implements IntReservaDao{

	/*Lo que hace un autowired es buscar un objeto manejado (beans) que implementen determinada interfaz para hacer referencia a él.
	 *  De esta manera no es neceario crear una instancia nueva del objeto cada vez que se necesite la funcionalidad de determinada clase */
	@Autowired
	private IntReservaRepo reserRepo;
	
	/*
	@Override
	public List<Reserva> findAll() {
		// TODO Auto-generated method stub
		return reserRepo.findAll();
	}

	@Override
	public List<Reserva> findReservasByIdEvento(int idEvento) {
		
		List<Reserva> listaReservasPorEvento=new ArrayList<Reserva>();
		List<Reserva> listaReservasCompleta=reserRepo.findAll();
		for (int i=0;i<listaReservasCompleta.size();i++) {
			if (listaReservasCompleta.get(i).getEvento().getIdEvento()==idEvento) {
				listaReservasPorEvento.add(listaReservasCompleta.get(i));
			}
		}
		return listaReservasPorEvento;
	}
    */
	
	//Con el siguiente metodo vamos a calcular el numero de reservas existentes para un evento
	@Override
	public int ReservasPorEvento(int idEvento) {
		//Creamos una variable numeroReservas y la ponemos a cero.
		int numeroReservas=0;
		//Creamos una lista de Reservas que contiene todas las reservas existentes
		List<Reserva> listaReservasCompleta=reserRepo.findAll();
		//Recorremos esa lista de reservas 
		for (int i=0;i<listaReservasCompleta.size();i++) {
			//Cuando de esas reservas, la idEvento coincida con la que pasamos por parametro, 
			//añadimos la cantidad reservada a nuestra variable numeroReservas
			if (listaReservasCompleta.get(i).getEvento().getIdEvento()==idEvento) {
				numeroReservas=numeroReservas+listaReservasCompleta.get(i).getCantidad();
			}
			
		
		
			}
		//Devolvemos el numero de Reservas para ese idEvento
		return numeroReservas;
	}
}
