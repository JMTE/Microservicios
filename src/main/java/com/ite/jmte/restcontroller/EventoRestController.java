package com.ite.jmte.restcontroller;

/** @author JMTE */

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

/*El intercambio de recursos de origen cruzado (CORS) es un protocolo estándar que define la interacción entre un navegador 
 * y un servidor para manejar de forma segura las solicitudes HTTP de origen cruzado
 */
@CrossOrigin(origins="*")
/*@RestController anotación en Spring es esencialmente una combinación de @Controller y @ResponseBody*/
@RestController

//Cuando @RequestMapping se usa en el nivel de clase, crea un URI base para el que se usará el controlador. Aqui creamos nuestra url general
@RequestMapping("rest/eventos")
public class EventoRestController {

	
	/*Lo que hace un autowired es buscar un objeto manejado (beans) que implementen determinada interfaz para hacer referencia a él.
	 *  De esta manera no es neceario crear una instancia nueva del objeto cada vez que se necesite la funcionalidad de determinada clase */
	@Autowired
	private IntEventoDao eventDao;
	@Autowired
	private IntReservaDao reserDao;
	
	
	//En este caso de uso vamos a devolver los eventos activos
	@GetMapping("/activos")
	
	public List<Evento> devolverActivos (){
		
		//Creamos una lista de eventos y utilizamos el metodo creado en nuestro dao para localizar los eventos activos
		List<Evento>listaActivos=eventDao.findEventosActivos();
		
		return listaActivos;
	}
	
	
	//En este caso de uso vamos a devolver los eventos destacados
	@GetMapping("/destacados")
	
	public List<Evento> devolverDestacados(){
		
		//Creamos una lista de eventos y utilizamos el metodo creado en nuestro dao para localizar los eventos destacados
		List<Evento>listaDestacados=eventDao.findEventosDestacados();
		
		return listaDestacados;
	}
	
	
	//En este caso de uso buscamos los eventos en donde su atributo nombre contenga la subcadena que pasamos por parametro
	@GetMapping("buscarEventos/{subcadena}")
	
	public List<Evento> devolverEventosSubcadena(@PathVariable String subcadena){
		
		//Creamos una lista de eventos y utilizamos el metodo creado en nuestro dao para localizar los eventos en los que el nombre contenga la subcadena
		List<Evento>listaSubcadena=eventDao.findEventoBySubcadena(subcadena);
		
		return listaSubcadena;
		
	}
	
	
	//En este caso de uso vamos a devolver las plazas que restan disponibles en cada evento
	@SuppressWarnings("rawtypes")
	@GetMapping("plazasQuedan/{idEvento}")
	
	//Devolvemos en tipo map porque nos permite representar una estructura de datos para almacenar pares "clave/valor"
	public Map plazasDisponibles (@PathVariable int idEvento) {
		//Si el evento no existe, devolvemos que no existe
		if (eventDao.findEventoById(idEvento)==null) {
			return Collections.singletonMap("quedan_plazas", "No existe ese evento");
		}
		//Si el evento existe, devolvemos la diferencia entre las plazas de aforo maximo y las reservas que tenemos hechas para ese evento calculadas
		//con nuestro metodo en el dao
		int plazas=eventDao.findEventoById(idEvento).getAforoMaximo()-reserDao.ReservasPorEvento(idEvento);
	
		
	
		 return Collections.singletonMap("quedan_plazas", plazas);
	}
	
	
	//Con este metodo vamos a ver un evento segun su idEvento
	@GetMapping("/verUno/{idEvento}")
	
	public Evento verEvento(@PathVariable int idEvento) {
		
		
		return eventDao.findEventoById(idEvento);
	}
	
	//Con este metodo damos de alta un evento en nuestra lista de eventos
	@PostMapping("/alta")
	
	public String altaEvento(@RequestBody Evento evento) {
		
		//Si nuestro metodo del dao nos retorna un 1 alta realizada, sino alta no realizada
		return (eventDao.altaEvento(evento)==1)?"Alta Realizada":"Alta no realizada";
	}
	
	//Con este metodo modificamos un evento de nuestra lista de eventos
	@PutMapping("/modificar")
	
	public String modificarEvento(@RequestBody Evento evento) {
		//Si nuestro metodo del dao nos retorna un 1 modificacion realizada, sino modificacion no realizada
		return (eventDao.modificarEvento(evento)==1)?"Evento modificado":"Evento no modificado";
	}
	
	
	//Con este metodo eliminamos un evento de nuestra lista de eventos
	@DeleteMapping("/eliminar/{idEvento}")
	
	public String eliminarEvento(@PathVariable int idEvento) {
		//Si nuestro metodo del dao nos retorna un 1 eliminacion realizada, sino eliminacion no realizada
		
		return (eventDao.eliminarEvento(idEvento)==1)?"Evento eliminado":"Evento no eliminado";
	}
}
