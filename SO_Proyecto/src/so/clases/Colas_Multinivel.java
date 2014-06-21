package so.clases;

import java.util.ArrayList;
import so.interfaces.IPlanificador;

public class Colas_Multinivel implements IPlanificador {
	
	private ArrayList<Reserva> lista_nivel_uno;
	private ArrayList<Reserva> lista_nivel_dos;
	private ArrayList<Reserva> lista_nivel_tres;

	public Colas_Multinivel() {
		this.lista_nivel_uno = new ArrayList<Reserva>();
		this.lista_nivel_dos = new ArrayList<Reserva>();
		this.lista_nivel_tres = new ArrayList<Reserva>();
	}

	@Override
	public void Planificar(Reserva unaReserva) {
		lista_nivel_uno.add(unaReserva);

	}

	@Override
	public Reserva Obtener() {
		if (! lista_nivel_uno.isEmpty()) {
			return null;
		}
		
		if (! lista_nivel_dos.isEmpty()) {
			return null;
		}
		
		if (! lista_nivel_tres.isEmpty()) {
			return null;
		}
		return null;
	}

}
