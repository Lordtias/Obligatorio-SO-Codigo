package so.hilos;

import java.util.ArrayList;

import so.clases.Asiento;
import so.clases.Evento;
import so.clases.Fila;
import so.clases.Reserva;
import so.clases.Sector;
import so.interfaces.IComunicador;
import so.interfaces.IPlanificador;

public class Ejecutor extends Thread {
	
	private IPlanificador planificador;								// Planificador de las Reservas.
	private IComunicador comunicador;								// Comunicador de la Reserva.
	private ArrayList<Evento> lista_eventos;						// Lista de los eventos en los que trabaja.
	
	/**
	 * Constructor del Ejecutor.
	 * 
	 * @param planificador - El Planificador del cual obtener Reservas.
	 * @param comunicador - El comunicador el cual comunica de las Reservas Ejecutadas.
	 * @param evento - El evento el cual va a gestionar.
	 */
	public Ejecutor(IPlanificador planificador, IComunicador comunicador, Evento evento) {
		this.planificador = planificador;
		this.comunicador = comunicador;
		this.lista_eventos = new ArrayList<Evento>();
		this.lista_eventos.add(evento);
	}

	
	@Override
	public void run() {
		while (true) {
			Reserva aux = Ejecutar(planificador.Obtener());
			if (aux != null) {
				//aux.Ver();
				System.out.println("EL MENSAJE ES: " + aux.getMensage());
				System.out.println(" ");
				comunicador.Comunicar_Reserva(aux);
			}
		}
	}
	
	/**
	 * Ejecuta una Reserva.
	 * 
	 * @param unaReserva - Reserva a ejecutar.
	 * @return - Reserva Ejecutada.
	 */
	private Reserva Ejecutar(Reserva unaReserva){
		if (unaReserva == null) {
			return null;
		}
		else{

			unaReserva.Ver();
			// Verifica el tipo de Reserva.
			if (unaReserva.reserva_sector) {
				
				// Verifica de que Evento es la Reserva.
				for (Evento e : lista_eventos) {					
					if (e.getNombre().equals(unaReserva.evento)) {
						
						//Busca el Sector en el Local del Evento.
						Sector s = e.getLocal().Buscar(unaReserva.sector);
						
						// Verifica si esta libre.
						if (s.Esta_libre()) {
							
							// Verifica si tiene Asientos Especiales o no y vende.
							if (unaReserva.fila_esp == null) s.Vender();
							else s.Vender(unaReserva.fila_esp);
							
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(0);
							
							return unaReserva;							
						}else{
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(2);
							
							return unaReserva;	
						}
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				
				return unaReserva;
			}
			// Verifica el tipo de Reserva.
			if (unaReserva.reserva_fila) {
				
				// Verifica de que Evento es la Reserva.
				for (Evento e : lista_eventos) {
					if (e.getNombre().equals(unaReserva.evento)) {
						
						//Busca el Sector en el Local para encontrar la Fila.
						Fila f = e.getLocal().Buscar(unaReserva.sector).Buscar(unaReserva.fila);
						
						// Verifica si esta libre.
						if (f.Esta_libre()) {
							
							// Verifica si tiene Asientos Especiales o no y vende.
							if (unaReserva.asientos_especiales == null) f.Vender();
							else f.Vender(unaReserva.asientos_especiales);
							
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(0);
							
							return unaReserva;							
						}else{
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(3);
							
							return unaReserva;
						}
							
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				
				return unaReserva;	
			}
			// Verifica el tipo de Reserva.
			if (unaReserva.reserva_asientos) {
				
				// Verifica de que Evento es la Reserva.
				for (Evento e : lista_eventos) {
					if (e.getNombre().equals(unaReserva.evento)) {
						
						//Busca el Sector en el Local para encontrar la Fila.
						Fila f = e.getLocal().Buscar(unaReserva.sector).Buscar(unaReserva.fila);
						
						// Verifica si el conjunto de Asientos estan Libres y vende.
						if (f.Esta_libre(unaReserva.asientos)) {
							f.Vender(unaReserva.asientos);
							
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(0);
							
							return unaReserva;							
						}else{
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(4);
							
							return unaReserva;
						}
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				
				return unaReserva;
			}
			// Verifica el tipo de Reserva.
			if (! unaReserva.reserva_asientos) {
				
				// Verifica de que Evento es la Reserva.
				for (Evento e : lista_eventos) {
					if (e.getNombre().equals(unaReserva.evento)) {
						
						//Busca la Fila del sector del Local para encontrar el Asiento.
						Asiento a = e.getLocal().Buscar(unaReserva.sector).Buscar(unaReserva.fila).Buscar(unaReserva.asiento);

						// Verifica si el Asiento esta Libre y vende.
						if (a.Esta_libre()) {
							a.Vender(unaReserva.especial);
							
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(0);
							
							return unaReserva;							
						}else{
							// Genera el mensaje.
							unaReserva.Generar_Mensaje(5);
							
							return unaReserva;
						}
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				
				return unaReserva;
			}
			return unaReserva;
		}
	}
}
