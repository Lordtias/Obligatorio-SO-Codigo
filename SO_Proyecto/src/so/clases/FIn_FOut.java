package so.clases;

import java.util.ArrayList;

import so.interfaces.IPlanificador;

public class FIn_FOut implements IPlanificador {
	private ArrayList<Reserva> lista;						// Lista de Reservas.
	private String nombre;									// Nombre del Planificador.

	/**
	 * Constructor de Fin_FOut.
	 * 
	 * @param nombre - Nombre del Planificador.
	 */
	public FIn_FOut(String nombre) {
		this.nombre = nombre;
		this.lista = new ArrayList<Reserva>();
	}

	@Override
	public void Planificar(Reserva unaReserva) {
		synchronized (this) {
			lista.add(unaReserva);
		}

	}

	@Override
	public Reserva Obtener() {
		synchronized (this) {
			if (! lista.isEmpty() ) {
				return lista.remove(0);
			}
			return null;
		}
	}

	@Override
	public String getName() {
		return this.nombre;
	}

}
