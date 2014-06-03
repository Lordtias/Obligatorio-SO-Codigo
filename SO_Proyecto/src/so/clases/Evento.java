package so.clases;

import java.util.Date;


public class Evento{
	
	private Date fecha_evento;	// Fecha del Evento.
	private String tipo;		// Tipo de Evento.
	private Local local;		// EL local en el cual se realiza un evento.
	
	/*M�todos get y set de los atributos del evento*/
	
	public String getTipo() {
		return tipo;
	}
	public Date getFecha_evento() {
		return fecha_evento;
	}
	public void setFecha_evento(Date fecha_evento) {
		this.fecha_evento = fecha_evento;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**Constructor del evento
	*/
	public Evento(Date fecha_evento, String tipo, Local local) {
		this.fecha_evento = fecha_evento;
		this.tipo = tipo;
		this.local = local;
	}
}
