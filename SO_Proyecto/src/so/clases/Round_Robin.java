package so.clases;

import java.util.ArrayList;

import so.interfaces.IPlanificador;

public class Round_Robin implements IPlanificador {
	
	private String nombre;					// Nombre del Planificador.
	private ArrayList<Reserva> lista;		// Lista con las Reservas.
	private String[] etiquetas;				// Lista con las etiquetas que tiene que iterar. 
	private String etiqueta;				// Etiqueta actual.
	private int iteracion;					// Iteracion en la que se encuentra.
	private int contador_limite;			// Cantidad de Reservas por Etiqueta.
	private int contador;					// Contador de Reservas.

	/**
	 * Constructor de Round_Robin.
	 * 
	 * @param nombre - Nombre del Planificador.
	 * @param etiquetas - Etiquetas con las que Iterar.
	 * @param contador - Cantidad de Reservas por Etiqueta.
	 */
	public Round_Robin(String nombre, String[] etiquetas,	int contador) {
		this.nombre = nombre;
		this.lista = new ArrayList<Reserva>();
		this.etiquetas = etiquetas;
		this.etiqueta = this.etiquetas[0];
		this.iteracion =0;
		this.contador_limite = contador;
		this.contador = 1;
	}
	
	/**
	 * Logica del Round Robin.
	 * Itero un conjunto de etiquetas y llevo una cuenta de cuantas reservas se enviaron de ella.
	 * En vez de darle un Tiempo se le da una cantidad de reservas que puede enviar antes que las otras.
	 */
	private void Iterar(){
		contador++;
		if (contador > contador_limite) {
			contador=0;
			iteracion++;
			if (iteracion < etiquetas.length) {
				etiqueta = etiquetas[iteracion];
			}else{
				etiqueta = etiquetas[0];
				iteracion=0;
			}
		}
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
			
			Reserva candidata = null;
			
			// Recorre la lista.
			for (Reserva R : lista) {
				
				// Verifica que esdel tipo de la Etiqueta. De un Punto de Venta.
				if (R.punto_de_venta.equals(etiqueta)) {
					
					// Verifica si es del tipo Sector.
					if (R.reserva_sector) {
						// Coloca la primera como candidata.
						if (candidata == null) candidata = R;
						
						// Se ponderan las que tienen mas Asientos Especiales.
						if (R.Cantidad_Especiales_Sector() > candidata.Cantidad_Especiales_Sector()) {
							candidata = R;
						}
					}
					
					// Verifica si no se encontro candidata y es la Reserva del tipo Fila.
					if (candidata == null && R.reserva_fila) {
						// Coloca la primera como candidata.
						if (candidata == null) candidata = R;
						
						// Se ponderan las que tienen mas Asientos Especiales.
						if (R.asientos_especiales.length > candidata.asientos_especiales.length) {
							candidata = R;
						}
					}
					
					// Verifica si no se encontro candidata y es la Reserva del tipo Conjunto de Asientos.
					if (candidata == null && R.reserva_asientos) {
						// Coloca la primera como candidata.
						if (candidata == null) candidata = R;
						
						// Se ponderan las que tienen mas Asientos Especiales.
						if (R.Cantidad_Especiales() > candidata.Cantidad_Especiales()) {
							candidata = R;
						}
					}
					
					// Verifica si no se encontro candidata y es la Reserva del tipo Asiento.
					if (candidata == null && ! R.reserva_asientos) {
						if (candidata == null){
							candidata = R;
							
							// Si la primea Reserva ya es Especial, retiramos la Reserva y
							// se termina la busqueda.
							if (candidata.especial){
								//System.out.println("Saco: " + candidata.evento);
								lista.remove(candidata);
								Iterar();
								return candidata;
							}
						}
						
						// Se ponderan la que es Especial, se retira de la lista y se devuelve.
						if (R.especial) {
							//System.out.println("Saco: " + candidata.evento);
							candidata = R;
							lista.remove(candidata);
							Iterar();
							return candidata;
						}
					}	
				}
			}
			// Devuelve la candidata.
			lista.remove(candidata);
			Iterar();
			return candidata;	
		}	
	}

	@Override
	public String getName() {
		return this.nombre;
	}
}