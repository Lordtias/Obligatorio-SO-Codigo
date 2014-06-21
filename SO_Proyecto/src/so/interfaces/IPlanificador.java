package so.interfaces;

import so.clases.Reserva;

public interface IPlanificador {

	/**
	 * Envia una Reserva al planificador.
	 * 
	 * @param unaReserva - Reserva a planificar.
	 */
	void Planificar(Reserva unaReserva);

	/**
	 * Obtiene la Reserva que el planificador considera de mayor prioridad.
	 * 
	 * @return - Reserva de mayor prioridad.
	 */
	Reserva Obtener();

}
