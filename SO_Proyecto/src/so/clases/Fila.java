package so.clases;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * @author usuario
 *
 */
public class Fila {
	private String nombre;						// Nombre de la Fila.
	private String Sector;						// Sector al que pertenece.
	private ArrayList<Asiento> lista_asientos;	// Lista de Asientos.
	
	/*Metodos get y set de los atributos de la Fila*/
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
	public ArrayList<Asiento> getLista_asientos() {
		return lista_asientos;
	}
	
	/**
	 * Constructor de la Fila.
	 * 
	 * @param nombre - Nombre de la Fila
	 * @param sector - Sector al  que pertenece.
	 * @param precio - Precio de la Fila.
	 */
	public Fila(String nombre, String sector) {
		this.nombre = nombre;
		Sector = sector;
		this.lista_asientos = new ArrayList<Asiento>();
	}
	
	/**
	 * Agrega Asientos a la fila.
	 * 
	 * @param unAsiento
	 */
	public void addAsiento(Asiento unAsiento){
		lista_asientos.add(unAsiento);
	}
	
	/**
	 * Verifica si esta libre la Fila.
	 * @return
	 */
	public Boolean Esta_libre(){
		Boolean chk = true;
		for (Asiento a : lista_asientos) {
			if (! a.Esta_libre()) {
				chk = false;
			}
		}
		return chk;
	}
	
	/**
	 * Pasa a vendida la Fila.
	 */
	public void Vender(){
		for (Asiento a : lista_asientos) {
			a.Vender();
		}
	}
	
	/**
	 * Vende una Fila con Asientos Especiales.
	 * 
	 * @param valores - Asientos Especiales.
	 */
	public void Vender(String[] valores){
		for (Asiento a : lista_asientos) {
			for (String string : valores) {
				if (a.getPosicion() == Integer.parseInt(string)) {
					a.Vender(true);
				}
			}
		}
	}

	/**
	 * Busca un Asiento en una Fila
	 * 
	 * @param posicion - Posicion del Asiento.
	 * @return - El Asiento.
	 */
	public Asiento Buscar(int posicion){
		for (Asiento a : lista_asientos) {
			if(a.getPosicion() == posicion) return a;
		}
		return null;
	}
	
	/**
	 * Verifica si esta libre un conjutno de Asientos en una Fila
	 * 
	 * @param asientos - Conjunto de Asientos.
	 * @return - Si esta libre.
	 */
	public Boolean Esta_libre(Hashtable<Integer, String> asientos){
		Boolean chk = true;
		for (Entry<Integer, String> e : asientos.entrySet()) {
			if (! Buscar(e.getKey()).Esta_libre()) {
				chk = false;
			}
		}
		return chk;
	}
	
	/**
	 * Vende un conjunto de Asientos.
	 * 
	 * @param valores - Asientos Especiales.
	 */
	public void Vender(Hashtable<Integer, String> asientos){
		for (Entry<Integer, String> e : asientos.entrySet()) {
			Buscar(e.getKey()).Vender(Boolean.parseBoolean(e.getValue()));
		}
	}
}
