package so.clases;
import java.util.ArrayList;



/**
 * @author usuario
 *
 */
public class Local{
	
	private String nombre;						// Nombre de local.
	private int cantidad_sectores;				// La cantidad de Sectores.
	private int cantidad_filas;					// La cantidad de Filas por Sector.
	private int cantidad_asientos;				// La cantidad de Asientos por Fila.
	private ArrayList<Sector> lista_sectores; 	// Lista de Sectores.
	
	/*M�todos get y set de los atributos del local*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCantidad_sectores() {
		return cantidad_sectores;
	}
	
	public int getCantidad_filas() {
		return cantidad_filas;
	}
	
	public int getCantidad_asientos() {
		return cantidad_asientos;
	}
	
	public ArrayList<Sector> getListaSector(){
		return this.lista_sectores;
	}
	
	/**Constructor del Local
	*/
	public Local(String nombre){
		this.nombre=nombre;
	}
	
	public void addSector(Sector unSector){
		lista_sectores.add(unSector);
	}
}
