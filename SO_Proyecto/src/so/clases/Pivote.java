package so.clases;

import java.util.ArrayList;
import java.util.UUID;

import so.interfaces.IComunicador;
import so.interfaces.IPlanificador;
import so.interfaces.IRecepcion;

public class Pivote implements IRecepcion, IComunicador {
	
	private IPlanificador planificador;				// Planificador al cual le manda las Reservas.
	private ArrayList<Reserva> lista_finalizado;	// Lista de Reservas Finalizadas.

	/**
	 * Constructor del Pivote.
	 * 
	 * @param planificador - El Planificador.
	 */
	public Pivote(IPlanificador planificador) {
		this.planificador = planificador;
		this.lista_finalizado = new ArrayList<Reserva>();
	}

	@Override
	public void Enviar_Reserva(Reserva unaReserva) {
		synchronized (planificador) {
			planificador.Planificar(unaReserva);
		} 
	}

	@Override
	public Reserva Consultar_Reserva(UUID id) {
		synchronized (lista_finalizado) {
			for (int i = 0; i < lista_finalizado.size(); i++) {
				if (lista_finalizado.get(i).id.compareTo(id) == 0){
					return lista_finalizado.remove(i);
				}
			}
		}
		return null;
	}

	@Override
	public void Comunicar_Reserva(Reserva unaReserva) {
		synchronized (lista_finalizado) {
			lista_finalizado.add(unaReserva);
		}
	}
}
