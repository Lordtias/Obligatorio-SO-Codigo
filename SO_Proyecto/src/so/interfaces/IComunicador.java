package so.interfaces;

import so.clases.Reserva;

public interface IComunicador {

	/**
	 * Envia a un Recepcionador la Reserva que finalizo la ejecucion.
	 * 
	 * @param unaReserva - Reserva la acual termino de ejecutar.
	 */
	void Comunicar_Reserva(Reserva unaReserva);

}
