package so.clases;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;


/**
 * @author usuario
 *
 */
public class Sector {
	private String nombre;				// Nombre del Sector.
	private ArrayList<Fila> lista_fila;	// Lista de Filas del Sector.
	
	/*Metodos get y set de los atributos del Sector*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Fila> getLista_fila() {
		return lista_fila;
	}
	
	/**
	 * Contructor de Sector.
	 * 
	 * @param nombre - Nombre del Sector.
	 */
	public Sector(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Agregar una fila al Sector.
	 * 
	 * @param unaFila - Fila para aregar.
	 */
	public void addFila(Fila unaFila){
		lista_fila.add(unaFila);
	}
	
	/**
	 * Verifica si esta libre el Sector.
	 * @return
	 */
	public Boolean Esta_libre(){
		Boolean chk = true;
		for (Fila f : lista_fila) {
			if (! f.Esta_libre()) {
				chk = false;
			}
		}
		return chk;
	}
	
	/**
	 * Pasa a vendida el Sector.
	 */
	public void Vender(){
		for (Fila f : lista_fila) {
			f.Vender();
		}
	}
	
	/**
	 * Vende un Sector con Asientos Especiales.
	 * 
	 * @param valores - tabla de Asientos Especiales.
	 */
	public void Vender(Hashtable<String, String[]> tabla){
		for (Fila f : lista_fila) {
			for (Entry<String, String[]> e : tabla.entrySet()) {
				if (f.getNombre().equals(e.getKey())) {
					f.Vender(e.getValue());
				}
			}
		}
	}

	/**
	 * Busca una fila en un Sector.
	 * 
	 * @param nombre - Nombre de la Fila.
	 * @return - La Fila.
	 */
	public Fila Buscar(String nombre){
		for (Fila f : lista_fila) {
			if(f.getNombre().equals(nombre)) return f;
		}
		return null;
	}
}
