package so.clases;
import java.util.ArrayList;
/**
 * @author usuario
 *
 */
public class Local{
	
	private String nombre;						// Nombre de local.
	private ArrayList<Sector> lista_sectores; 	// Lista de Sectores.
	
	/*Metodos get y set de los atributos del Local*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Sector> getListaSector(){
		return this.lista_sectores;
	}
	
	/**
	 * Constructor del Local.
	 * 
	 * @param nombre - Nombre del Local.
	 */
	public Local(String nombre){
		this.nombre=nombre;
		this.lista_sectores = new ArrayList<Sector>();
	}
	
	/**
	 * Agrega Sectores al Local.
	 * 
	 * @param unSector - Devuelve el Sector.
	 */
	public void addSector(Sector unSector){
		lista_sectores.add(unSector);
	}

	/**
	 * Busca un Sector.
	 * 
	 * @param nombre - Nombre del Sector.
	 * @return - El Sector.
	 */
	public Sector Buscar(String nombre){
		for (Sector s : lista_sectores) {
			if(s.getNombre().equals(nombre)) return s;
		}
		return null;
	}
}
