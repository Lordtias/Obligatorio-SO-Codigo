package so.hilos;

import so.interfaces.IRecepcion;

public class Red_Cobranza extends Punto_De_Venta{
	
	private String tipos_reserva;								// Tipos de reservas que puede hacer.

	/*Metodos get y set de la Red de cobranza*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipos_reserva() {
		return this.tipos_reserva;
	}
	public void setTipos_reserva(String tipos) {
		this.tipos_reserva = tipos;
	}

	/**
	 * Constructor de Red Cobranza.
	 * 
	 * @param nombre - Nombre de la Red Cobranza.
	 * @param archivo_registros - Direccion al Archivo con los registros.
	 * @param recepcion - El recepcionador de las Reservas.
	 */
	public Red_Cobranza(String nombre, String archivo_registros, IRecepcion recepcion) {
		super(nombre, archivo_registros, recepcion);
		this.tipos_reserva = "3-4";
		super.leer_registros(this.tipos_reserva);
	}
}