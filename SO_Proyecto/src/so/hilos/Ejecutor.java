package so.hilos;

import java.util.ArrayList;

import so.clases.Evento;
import so.clases.Reserva;
import so.interfaces.IComunicador;
import so.interfaces.IPlanificador;

public class Ejecutor extends Thread {
	
	IPlanificador planificador;								// Planificador de las Reservas.
	IComunicador comunicador;
	ArrayList<Evento> lista_eventos;						// Lista de los eventos en los que trabaja.
	
	
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
				comunicador.Comunicar_Reserva(aux);
			}
		}
	}
	
	private Reserva Ejecutar(Reserva unaReserva){
		if (unaReserva == null) {
			return null;
		}
		else{
			
			return unaReserva;
		}
	}
}
