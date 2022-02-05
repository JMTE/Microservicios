package com.ite.jmte.restcontroller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ite.jmte.modelo.beans.Evento;
import com.ite.jmte.modelo.dao.IntEventoDao;
import com.ite.jmte.modelo.dao.IntReservaDao;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/eventos")
public class EventoRestController {

	@Autowired
	private IntEventoDao eventDao;
	@Autowired
	private IntReservaDao reserDao;
	
	@GetMapping("/activos")
	
	public List<Evento> devolverActivos (){
		
		List<Evento>listaActivos=eventDao.findEventosActivos();
		
		return listaActivos;
	}
	
	@GetMapping("/destacados")
	
	public List<Evento> devolverDestacados(){
		List<Evento>listaDestacados=eventDao.findEventosDestacados();
		
		return listaDestacados;
	}
	
	@GetMapping("buscarEventos/{subcadena}")
	
	public List<Evento> devolverEventosSubcadena(@PathVariable String subcadena){
		List<Evento>listaSubcadena=eventDao.findEventoBySubcadena(subcadena);
		
		return listaSubcadena;
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("plazasQuedan/{idEvento}")
	
	public Map plazasDisponibles (@PathVariable int idEvento) {
		int plazas=eventDao.findEventoById(idEvento).getAforoMaximo()-reserDao.ReservasPorEvento(idEvento);
	
		
	
		 return Collections.singletonMap("quedan_plazas", plazas);
	}
	
	@GetMapping("/verUno/{idEvento}")
	
	public Evento verEvento(@PathVariable int idEvento) {
		
		return eventDao.findEventoById(idEvento);
	}
	
	
	@PostMapping("/alta")
	
	public String altaEvento(@RequestBody Evento evento) {
		
		return (eventDao.altaEvento(evento)==1)?"Alta Realizada":"Alta no realizada";
	}
	
	@PutMapping("/modificar")
	
	public String modificarEvento(@RequestBody Evento evento) {
		
		return (eventDao.modificarEvento(evento)==1)?"Evento modificado":"Evento no modificado";
	}
	
	@DeleteMapping("/eliminar/{idEvento}")
	
	public String eliminarEvento(@PathVariable int idEvento) {
		
		return (eventDao.eliminarEvento(idEvento)==1)?"Evento eliminado":"Evento no eliminado";
	}
}
