package so.clases;

import java.util.ArrayList;

/**
 * @author usuario
 *
 */
public class Fila {
	private String nombre;						// Nombre de la Fila.
	private String Sector;						// Sector al que pertenece.
	private int precio;							// Precio de la Fila
	private ArrayList<Asiento> lista_asientos;	// Lista de Asientos.
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSector() {
		return Sector;
	}
	public void setSector(String sector) {
		Sector = sector;
	}
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public ArrayList<Asiento> getLista_asientos() {
		return lista_asientos;
	}
	public void setLista_asientos(ArrayList<Asiento> lista_asientos) {
		this.lista_asientos = lista_asientos;
	}
	/** Constructor de Fila
	 */
	public Fila(String nombre, String sector, int precio) {
		this.nombre = nombre;
		Sector = sector;
		this.precio = precio;
	}
	
	public void addAsiento(Asiento unAsiento){
		lista_asientos.add(unAsiento);
	}
}
