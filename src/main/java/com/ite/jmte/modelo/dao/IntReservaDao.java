package com.ite.jmte.modelo.dao;

import java.util.List;

import com.ite.jmte.modelo.beans.Reserva;

public interface IntReservaDao {

	List<Reserva> findAll();
	
	List<Reserva> findReservasByIdEvento(int idEvento);
	
	int ReservasPorEvento(int idEvento);
}
