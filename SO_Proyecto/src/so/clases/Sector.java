package so.clases;
import java.util.ArrayList;


/**
 * @author usuario
 *
 */
public class Sector {
	private String nombre;				// Nombre del Sector.
	private int precio;					// Precio del Sector.
	private ArrayList<Fila> lista_fila;	// Lista de Filas del Sector.
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public ArrayList<Fila> getLista_fila() {
		return lista_fila;
	}
	public void setLista_fila(ArrayList<Fila> lista_fila) {
		this.lista_fila = lista_fila;
	}
	
	/**Constructor Sector
	 */
	public Sector(String nombre, int precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public void addFila(Fila unaFila){
		lista_fila.add(unaFila);
	}

}
