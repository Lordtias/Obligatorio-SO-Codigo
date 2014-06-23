package so.clases;

import java.util.ArrayList;
import so.interfaces.IPlanificador;

public class Colas_Multinivel implements IPlanificador {
	
	private String nombre;							// Nombre del Planificador
	private ArrayList<Reserva> lista_nivel_uno;		// Lista de nivel 1
	private ArrayList<Reserva> lista_nivel_dos;		// Lista de nivel 2
	private ArrayList<Reserva> lista_nivel_tres;	// Lista de nivel 3

	/**
	 * Constructor de Colas Multinivel.
	 * 
	 * @param nombre - El nombre.
	 */
	public Colas_Multinivel(String nombre) {
		this.nombre = nombre;
		this.lista_nivel_uno = new ArrayList<Reserva>();
		this.lista_nivel_dos = new ArrayList<Reserva>();
		this.lista_nivel_tres = new ArrayList<Reserva>();
	}

	@Override
	public void Planificar(Reserva unaReserva) {
		synchronized (this) {
			
			// Si la Reserva es de Sector o Fila, se agrega a la lista nivel 1.
			if (unaReserva.reserva_sector || unaReserva.reserva_fila) {
				//System.out.println("Reserva lista uno: " + unaReserva.evento);
				lista_nivel_uno.add(unaReserva);
			}
			else{
				
				// Si la Reserva es de un conjunto de Asientos, se agrega a la lista nivel 2.
				if (unaReserva.reserva_asientos) {
					//System.out.println("Reserva lista dos: " + unaReserva.evento);
					lista_nivel_dos.add(unaReserva);
				}
				
				// Si la Reserva es del tipo de Asiento especifico, se agrega a la lista nivel 3.
				else{
					//System.out.println("Reserva lista tres: " + unaReserva.evento);
					lista_nivel_tres.add(unaReserva);
				}
			}
		}
	}

	@Override
	public Reserva Obtener() {
		synchronized (this) {
			
			Reserva candidata = null;
			
			// Buscada en el primer nivel.
			if (! lista_nivel_uno.isEmpty()) {
				
				for (Reserva R : lista_nivel_uno) {
					
					// Filtramos por las Reservas de Sector.
					if (R.reserva_sector) {
						if (candidata == null) candidata = R;
						
						// Se ponderan las que tienen mas Asientos Especiales.
						if (R.Cantidad_Especiales_Sector() > candidata.Cantidad_Especiales_Sector()) {
							candidata = R;
						}
					}
				}
				
				if (candidata == null) {
					
					for (Reserva R : lista_nivel_uno) {
						
						// Filtramos por las Reservas de Fila.
						if (R.reserva_fila) {
							if (candidata == null) candidata = R;
							
							// Se ponderan las que tienen mas Asientos Especiales.
							if (R.asientos_especiales.length > candidata.asientos_especiales.length) {
								candidata = R;
							}
						}
					}
				}
				// Retiramos del planificador la Reserva y la retornamos.
				//System.out.println("Saco: " + candidata.evento);
				lista_nivel_uno.remove(candidata);
				return candidata;
			}
			
			// Primer nivel Vacio, busco en el segundo.
			if (! lista_nivel_dos.isEmpty()) {
				for (Reserva R : lista_nivel_dos) {
					
					// Filtramos por Reserva de conjutno de Asientos.
					if (R.reserva_asientos) {
						if (candidata == null) candidata = R;
						
						// Se ponderan las que tienen mas Asientos Especiales.
						if (R.Cantidad_Especiales() > candidata.Cantidad_Especiales()) {
							candidata = R;
						}
					}
				}
				
				// Retiramos del planificador la Reserva y la retornamos.
				//System.out.println("Saco: " + candidata.evento);
				lista_nivel_dos.remove(candidata);
				return candidata;
			}
			
			// Primero y Segundo nivel vacio, busco en le ultimo nivel.
			if (! lista_nivel_tres.isEmpty()) {
				for (Reserva R : lista_nivel_tres) {
					
					// Filtramos por Reserva de Asientos especificos.
					if (! R.reserva_asientos) {
						if (candidata == null){
							candidata = R;
							
							// Si la primea Reserva ya es Especial, retiramos la Reserva y
							// se termina la busqueda.
							if (candidata.especial){
								//System.out.println("Saco: " + candidata.evento);
								lista_nivel_tres.remove(candidata);
								return candidata;
							}
						}
						
						// Se ponderan la que es Especial, se retira de la lista y se devuelve.
						if (R.especial) {
							//System.out.println("Saco: " + candidata.evento);
							candidata = R;
							lista_nivel_tres.remove(candidata);
							return candidata;
						}
					}
				}
			}
			
			// No hay nada en la lista.
			return null;
		}
	}

	@Override
	public String getName() {
		return this.nombre;
	}
}
