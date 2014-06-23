package so.hilos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import so.clases.Asiento;
import so.clases.Evento;
import so.clases.Fila;
import so.clases.Reserva;
import so.clases.Sector;
import so.interfaces.IComunicador;
import so.interfaces.IPlanificador;

public class Ejecutor extends Thread {
	
	private String nombre;										// Nombre del Planificador.
	private IPlanificador planificador;							// Planificador de las Reservas.
	private IComunicador comunicador;							// Comunicador de la Reserva.
	private ArrayList<Evento> lista_eventos;					// Lista de los eventos en los que trabaja.
	private long tiempo_ejecutanto;								// Tiempo que esta ejecutando.
	private boolean detalle;									// Ver Detalle de las reservas.
	private Hashtable<String, Integer> cantidad_reservas_fail;	// Cantidad de Reservas fallidas.
	private File file;											// Escritura de Archivos
	
	/**
	 * Constructor del Ejecutor.
	 * 
	 * @param planificador - El Planificador del cual obtener Reservas.
	 * @param comunicador - El comunicador el cual comunica de las Reservas Ejecutadas.
	 * @param evento - El evento el cual va a gestionar.
	 */
	public Ejecutor(String nombre, IPlanificador planificador, IComunicador comunicador, Evento evento, boolean detalle, boolean salida_archivo) {
		this.nombre = nombre;
		this.planificador = planificador;
		this.comunicador = comunicador;
		this.lista_eventos = new ArrayList<Evento>();
		this.lista_eventos.add(evento);
		this.tiempo_ejecutanto = 0;
		this.detalle = detalle;
		this.cantidad_reservas_fail = new Hashtable<String, Integer>();
		this.cantidad_reservas_fail.put("Reserva Sector", 0);
		this.cantidad_reservas_fail.put("Reserva Fila", 0);
		this.cantidad_reservas_fail.put("Reserva Asientos", 0);
		this.cantidad_reservas_fail.put("Reserva Asiento", 0);
		
		if (salida_archivo)	this.file = new File(nombre+"_"+planificador.getName());
		else this.file = null;
	}
	
	/**
	 * Escribe un texto en un Archivo.
	 * 
	 * @param texto
	 */
	private void Escribir(String texto){
		try {
			FileWriter w = new FileWriter(this.file, true);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw); 
			wr.append(texto);
			wr.append("\n");
			wr.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Incremento la cantidad de fallos de un tipo especifico de Reservas.
	 * @param key - Tipo de Reserva
	 */
	private void inc_fallo(String key){
		this.cantidad_reservas_fail.put(key,cantidad_reservas_fail.get(key)+1);
	}
	
	@Override
	public void run() {
		while (true) {
			Reserva aux = Ejecutar(planificador.Obtener());
			if (aux != null) {
				if(tiempo_ejecutanto == 0) tiempo_ejecutanto = System.currentTimeMillis();
				
				// Minimo detalle en consola.
				System.out.println("EL MENSAJE ES: " + aux.getMensage());
				System.out.println("El tiempo que lleba en ejecucion "+this.nombre+" es: " + (System.currentTimeMillis() - tiempo_ejecutanto));
				System.out.println("CANTIDAD DE FALLOS AL RESERVAR : "+cantidad_reservas_fail.toString());
				System.out.println(" ");
				
				// Escribe en un Archivo.
				if (this.file != null) {
					Escribir("EL MENSAJE ES: " + aux.getMensage());
					Escribir("El tiempo que lleba en ejecucion "+this.nombre+" es: " + (System.currentTimeMillis() - tiempo_ejecutanto));
					Escribir("CANTIDAD DE FALLOS AL RESERVAR : "+cantidad_reservas_fail.toString());
					Escribir("\n");
				}
				
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

			if (detalle) unaReserva.Ver();
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
							inc_fallo("Reserva Sector");
							return unaReserva;	
						}
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				inc_fallo("Reserva Sector");
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
							inc_fallo("Reserva Fila");
							return unaReserva;
						}
							
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				inc_fallo("Reserva Fila");
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
							inc_fallo("Reserva Asientos");
							return unaReserva;
						}
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				inc_fallo("Reserva Asientos");
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
							inc_fallo("Reserva Asiento");
							return unaReserva;
						}
					}
				}
				// Genera el mensaje.
				unaReserva.Generar_Mensaje(1);
				inc_fallo("Reserva Asiento");
				return unaReserva;
			}
			return unaReserva;
		}
	}
}