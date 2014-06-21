package so.hilos;

import so.interfaces.IRecepcion;

public class Agencia extends Punto_De_Venta {
	
	private String tipos_reserva;								// Tipos de reservas que puede hacer.
	
	/*Metodos get y set de la Agencia*/
	public String getNombre() {
		return super.nombre;
	}
	public void setNombre(String nombre) {
		super.nombre = nombre;
	}
	public String getTipos_reserva() {
		return this.tipos_reserva;
	}
	public void setTipos_reserva(String tipos) {
		this.tipos_reserva = tipos;
	}
	
	/*Constructor de la Agencia*/
	public Agencia(String nombre, String archivo_registros, IRecepcion recepcion, String tipos_reserva) {
		super(nombre, archivo_registros, recepcion);
		this.tipos_reserva = tipos_reserva;
		super.leer_registros(this.tipos_reserva);
	}
	
	@Override
	public void run() {
		while (! super.lista_reservas.isEmpty()) {
			super.pivote.Enviar_Reserva(super.lista_reservas.remove(0));
		}
	}
	
	
}
