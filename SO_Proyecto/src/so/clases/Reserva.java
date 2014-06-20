/**
 * 
 */
package so.clases;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.UUID;

/**
 * @author ceibal
 *
 */
public class Reserva {
	public final UUID  id;								// Identificador de la Reserva.
	
	public final Date tiempo;							// Tiempo en que se hace la Reserva.
	
	public final String evento;							// Nombre del Evento.
	
	public final Boolean reserva_sector;				// Determina si se reservo el Sector completo.
	public final String sector;							// Nombre del Sector.
	
	public final Boolean reserva_fila;					// Determina si se reservo la Fila completa.
	public final String fila;							// Nombre de la Fila.
	
	public final String[] asientos_especiales;			// Lista de los Asientos Especiales.
	
	public final Boolean reserva_asientos;				// Determina si se reservo un conjunto de Asientos.
	public final Hashtable<Integer, String> asientos;	// Tabla de Asientos reservados y si es Especial.
	
	public final int asiento;							// Numero de Asientos.
	public final Boolean especial;						// Determina si el Asiento reservado es Especial.
	
	/**
	 * Reserva de un Sector completo.
	 * 
	 * @param tiempo - Momento en que se va hacer la reseva.
	 * @param evento - Nombre del Evento.
	 * @param reserva_sector - Determina si se reserva el Sector completo.
	 * @param sector - Nombre del sector.
	 * @param asientos_especiales - Lista de Asientos Especiales en el Sector.
	 */
	public Reserva(Date tiempo, String evento, Boolean reserva_sector, String sector, String[] asientos_especiales) {
		
		this.id = UUID.randomUUID();
		this.tiempo = tiempo;
		this.evento = evento;
		this.reserva_sector = reserva_sector;
		this.sector = sector;
		this.reserva_fila = false;
		this.asientos_especiales = asientos_especiales;
		this.fila = null;
		this.reserva_asientos = false;
		this.asientos = null;
		this.asiento = 0;
		this.especial = false;
	}

	/**
	 * Reserva de una fila completa.
	 * 
	 * @param tiempo - Momento en que se va hacer la reseva.
	 * @param evento - Nombre del Evento.
	 * @param reserva_sector - Determina si se reserva el Sector completo.
	 * @param sector - Nombre del sector.
	 * @param reserva_fila - Determina si se reserva la Fila completa.
	 * @param fila - Nombre de la Fila.
	 * @param asientos_especiales - Lista de Asientos Especiales en la Fila.
	 */
	public Reserva(Date tiempo, String evento, Boolean reserva_sector, String sector,
			Boolean reserva_fila, String fila, String[] asientos_especiales) {
		
		this.id = UUID.randomUUID();
		this.tiempo = tiempo;
		this.evento = evento;
		this.reserva_sector = reserva_sector;
		this.sector = sector;
		this.reserva_fila = reserva_fila;
		this.fila = fila;
		this.asientos_especiales = asientos_especiales;
		this.reserva_asientos = false;
		this.asientos = null;
		this.asiento = 0;
		this.especial = false;
	}

	/**
	 * Reserva de un conjunto de asientos.
	 * 
	 * @param tiempo - Momento en que se va hacer la reseva.
	 * @param evento - Nombre del Evento.
	 * @param reserva_sector - Determina si se reserva el Sector completo.
	 * @param sector - Nombre del sector.
	 * @param reserva_fila - Determina si se reserva la Fila completa.
	 * @param fila - Nombre de la Fila.
	 * @param reserva_asientos - Determina si se reservan un conjunto de asientos.
	 * @param asientos - Tabla de Asientos a reservar y si es Especial.
	 */
	public Reserva(Date tiempo, String evento, Boolean reserva_sector, String sector, Boolean reserva_fila,
			String fila, Boolean reserva_asientos,Hashtable<Integer, String> asientos) {
		
		this.id = UUID.randomUUID();
		this.tiempo = tiempo;
		this.evento = evento;
		this.reserva_sector = reserva_sector;
		this.sector = sector;
		this.reserva_fila = reserva_fila;
		this.fila = fila;
		this.asientos_especiales = null;
		this.reserva_asientos = reserva_asientos;
		this.asientos = asientos;
		this.asiento = 0;
		this.especial = false;
	}

	/**
	 * Reserva de un asientos en particular.
	 * 
	 * @param tiempo - Momento en que se va hacer la reseva.
	 * @param evento - Nombre del Evento.
	 * @param reserva_sector - Determina si se reserva el Sector completo.
	 * @param sector - Nombre del sector.
	 * @param reserva_fila - Determina si se reserva la Fila completa.
	 * @param fila - Nombre de la Fila.
	 * @param reserva_asientos - Determina si se reservan un conjunto de asientos.
	 * @param asiento - Numero de Asiento.
	 * @param especial - Determina si el Asiento es Especial.
	 */
	public Reserva(Date tiempo, String evento, Boolean reserva_sector, String sector, Boolean reserva_fila,
			String fila, Boolean reserva_asientos, int asiento, Boolean especial) {
		
		this.id = UUID.randomUUID();
		this.tiempo = tiempo;
		this.evento = evento;
		this.reserva_sector = reserva_sector;
		this.sector = sector;
		this.reserva_fila = reserva_fila;
		this.fila = fila;
		this.asientos_especiales = null;
		this.reserva_asientos = reserva_asientos;
		this.asientos = null;
		this.asiento = asiento;
		this.especial = especial;
	}

	/**
	 * Muestra por consola en forma prolija los datos de la Reserva.
	 */
	public void Ver(){
		System.out.println("Identificador:                    " + id.toString());
		System.out.println("Fecha:                            " + tiempo.toString());
		System.out.println("Nombre del Evento:                " + evento );
		System.out.println("Se reserva Sector?:               " + reserva_sector);
		System.out.println("Nombre del Sector:                " + sector);
		System.out.println("Se Reserva Fila?:                 " + reserva_fila);
		System.out.println("Nombre de la Fila:                " + fila);
		if (reserva_sector | reserva_fila) {
			System.out.print("Lista de los asientos especiales: -");
			for (String a : asientos_especiales) {
				System.out.print(a+"-");
			}
		}
		System.out.println();
		System.out.println("Se reservan muchos Asientos?:     " + reserva_asientos);
		if (reserva_asientos == true) {
			System.out.print("Asientos reservados:              -");
			for (Entry<Integer, String> a: asientos.entrySet()) {
				System.out.print("Asiento: " + a.getKey() + " Es especial? " + a.getValue() + "-");
			}
		}else{
			System.out.println("Asiento:                          " + asiento);
			System.out.println("Es Especial?:                     " + especial);
		}
	}
}