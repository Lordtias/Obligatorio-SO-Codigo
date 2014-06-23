package so.clases;

import java.util.UUID;

public class Evento{
	
	private final UUID id;				// Identificador.
	private String nombre;				// Nombre del Evento.
	private Local local;				// El local en el cual se realiza un evento.
	
	/*Metodos get y set de los atributos del evento*/
	public UUID getid() {
		return this.id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return this.nombre;
	}
	public Local getLocal() {
		return local;
	}

	/**
	 * Constructor del Evento.
	 * 
	 * @param nombre - Nombre del Evento.
	 */
	public Evento(String nombre) {
		this.id = UUID.randomUUID();
		this.nombre = nombre;
	}
	
	/**
	 * Se genera un Local.
	 * 
	 * @param sectores - Nombre de los sectores.
	 * @param filas - Nombres de las filas.
	 * @param asientos - Cantidad de asientos. Determina las posiciones tambien.
	 * @return - Un Local.
	 */
	public void Generador_Local(String[] sectores, String[] filas, int asientos){
		this.local = new Local("Local");
		
		for (String string : sectores) {
			this.local.addSector(new Sector(string));
		}
		
		for (Sector s : this.local.getListaSector()) {
			for (String string : filas) {
				s.addFila(new Fila(string, s.getNombre()));
			}
		}
		
		for (Sector s : this.local.getListaSector()) {
			for (Fila f : s.getLista_fila()) {
				for (int i = 1; i <= asientos; i++) {
					f.addAsiento(new Asiento(f.getNombre(), i, s.getNombre()));
					
				}
			}
		}
	}
}