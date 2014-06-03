package so.hilos;

public class Boleteria extends Thread{

	private String nombre;	//Nombre de la Boleteria.

	/*Metodos get y set de la Boleteria*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/*Constructor de la Boleteria*/
	public Boleteria(String nombre){
		this.nombre=nombre;
	}	
}
