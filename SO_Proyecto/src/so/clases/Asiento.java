package so.clases;
/**
 * Clase Asiento
 * @author usuario
 *
 */
public class Asiento {
	private Boolean especial; 			// Determina si un Asiento es utilizado por un discapasitado.
	private Boolean vendido; 			// Determina si el Asiento fue vendido.
	private String nombre_fila; 		// Fila a la que pertenece el asiento
	private int posicion;				// Posición del asiento en la fila
	private String nombre_sector;		// Sector del lugar en donde se realiza el evento, al que pertenece el asiento
	private String nombre;				// Nombre del asiento
	
	/*Métodos get y set para cada atributo del Asiento*/
	public Boolean getEspecial() {
		return especial;
	}
	public void setEspecial(Boolean especial) {
		this.especial = especial;
	}
	public Boolean getVendido() {
		return vendido;
	}
	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}
	public String getFila_que_pertenece() {
		return nombre_fila;
	}
	public void setFila_que_pertenece(String fila_que_pertenece) {
		this.nombre_fila = fila_que_pertenece;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public String getSector_del_lugar() {
		return nombre_sector;
	}
	public void setSector_del_lugar(String sector_del_lugar) {
		this.nombre_sector = sector_del_lugar;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Constructor del Asiento.
	 * 
	 * @param fila_que_pertenece - Fila a la que pertenece el Asiento.
	 * @param posicion - Posicion respecto a la Fila. Numero de Asiento.
	 * @param sector_del_lugar - Nombre del Sector al que pertenese el Asiento.
	 * @param precio - Precio del Asiento.
	 */
	public Asiento(String fila_que_pertenece, int posicion, String sector_del_lugar) {
		this.especial = false;
		this.vendido = false;
		this.nombre_fila = fila_que_pertenece;
		this.posicion = posicion;
		this.nombre_sector = sector_del_lugar;
		this.nombre = fila_que_pertenece+""+posicion+"-"+sector_del_lugar;
	}
	
	/**
	 * Verifica si esta libre el Asiento.
	 * @return
	 */
	public Boolean Esta_libre(){
		return this.vendido;
	}
	
	/**
	 * Pasa a vendido el Asiento
	 */
	public void Vender(){
		this.vendido = true;
	}

	/**
	 * Vender un Asiento Especial.
	 * 
	 * @param especial
	 */
	public void Vender(Boolean especial){
		this.vendido = true;
		this.especial = especial;
	}
}
