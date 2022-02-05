package com.ite.jmte.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.jmte.modelo.beans.Evento;
import com.ite.jmte.repository.IntEventoRepo;

@Service
public class EventoDaoImplMy8SP implements IntEventoDao{

	@Autowired
	private IntEventoRepo eventoRepo;
	
	@Override
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		
		return eventoRepo.findAll();
	}

	@Override
	public Evento findEventoById(int idEvento) {
		// TODO Auto-generated method stub
		
		return eventoRepo.findById(idEvento).orElse(null);
	}

	@Override
	public List<Evento> findEventoBySubcadena(String subcadena) {
		// TODO Auto-generated method stub
		
		List<Evento> listaCoincidente=eventoRepo.findEventosByNameLike(subcadena);
		return listaCoincidente;
	}

	@Override
	public List<Evento> findEventosDestacados() {
		// TODO Auto-generated method stub
		List<Evento> listaDestacados=new ArrayList<Evento>();
		List<Evento> listaTodos=eventoRepo.findAll();
		for (int i=0;i<listaTodos.size();i++) {
			if(listaTodos.get(i).getDestacado().equals("S")) {
				listaDestacados.add(listaTodos.get(i));
			}
		}
		return listaDestacados;
	}

	@Override
	public List<Evento> findEventosActivos() {
		// TODO Auto-generated method stub
		List<Evento> listaActivos=new ArrayList<Evento>();
		List<Evento> listaTodos=eventoRepo.findAll();
		
		for (int i=0;i<listaTodos.size();i++) {
			if(listaTodos.get(i).getEstado().equals("Activo")) {
				listaActivos.add(listaTodos.get(i));
			}
		}
		
		return listaActivos;
	}

	@Override
	public int altaEvento(Evento evento) {
		// TODO Auto-generated method stub
		int filas=0;
		try {
			eventoRepo.save(evento);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return filas;
	}

	@Override
	public int modificarEvento(Evento evento) {
		// TODO Auto-generated method stub
		int filas=0;
		try {
			eventoRepo.save(evento);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return filas;
	}

	@Override
	public int eliminarEvento(int idEvento) {
		// TODO Auto-generated method stub
		int filas=0;
		try {
			eventoRepo.deleteById(idEvento);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return filas;
	}

}
