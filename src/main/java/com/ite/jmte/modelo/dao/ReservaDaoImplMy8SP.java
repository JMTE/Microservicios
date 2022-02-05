package com.ite.jmte.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Reserva;
import com.ite.jmte.repository.IntEventoRepo;
import com.ite.jmte.repository.IntReservaRepo;
@Service
public class ReservaDaoImplMy8SP implements IntReservaDao{

	@Autowired
	private IntReservaRepo reserRepo;
	@Autowired
	private IntEventoRepo eventRepo;
	
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

	@Override
	public int ReservasPorEvento(int idEvento) {
		int numeroReservas=0;
		List<Reserva> listaReservasCompleta=reserRepo.findAll();
		for (int i=0;i<listaReservasCompleta.size();i++) {
			if (listaReservasCompleta.get(i).getEvento().getIdEvento()==idEvento) {
				numeroReservas=numeroReservas+listaReservasCompleta.get(i).getCantidad();
			}
			
		
		
			}
		return numeroReservas;
	}
}
