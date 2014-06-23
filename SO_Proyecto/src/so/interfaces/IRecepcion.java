package so.interfaces;

import java.util.UUID;

import so.clases.Reserva;

public interface IRecepcion {
	
	/**
	 * Se envia una Reserva al Recepcionador. Este metodo se sincronizara para que varios
	 * hilos hagan uso de el.
	 * 
	 * @param unaReserva - Reserva enviada al Recepcionador.
	 */
	void Enviar_Reserva(Reserva unaReserva);

	/**
	 * Se consulta sobre una reserva en una lista de Reservas procesadas.
	 * 
	 * @param id - Identificador de la Reserva.
	 * @return - Devuelve la Reserva de la lista de procesadas.
	 */
	Reserva Consultar_Reserva(UUID id);

}