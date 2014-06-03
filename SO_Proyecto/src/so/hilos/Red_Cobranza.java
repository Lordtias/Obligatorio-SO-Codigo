package so.hilos;

public class Red_Cobranza {
	
	private String nombre;	//Nombre de la Red de Cobranza

	/*Metodos get y set de la Red de cobranza*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/*Constructor de la Red de Cobranza*/
	public Red_Cobranza(String nombre){
		this.nombre=nombre;
	}

}
