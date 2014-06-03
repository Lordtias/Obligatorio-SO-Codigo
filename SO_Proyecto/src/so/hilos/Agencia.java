package so.hilos;

public class Agencia extends Thread {
	
	private String nombre;	//Nombre de la Agencia
	
	/*Metodos get y set de la Agencia*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/*Constructor de la Agencia*/
	public Agencia(String nombre){
		this.setNombre(nombre);
	}

}
