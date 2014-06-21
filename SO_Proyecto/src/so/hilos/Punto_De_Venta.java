/**
 * 
 */
package so.hilos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import so.clases.Reserva;
import so.interfaces.IRecepcion;
import so.utilidades.LectorCSV;


public abstract class Punto_De_Venta extends Thread {
	protected String nombre;								// Nombre del Punto de Venta
	protected String archivo_registros;						// Direccion del archivo con los regstros.
	protected ArrayList<Reserva> lista_reservas;			// Lista de las reservas.
	protected IRecepcion pivote;							// Clase conectaro con el Planificador.
	
	/**
	 * Contstructor de la clase abstracta Putno de Venta.
	 * 
	 * @param nombre - Nombre del Punto de Venta.
	 * @param archivo_registros - Donde obtener los registros.
	 */
	public Punto_De_Venta(String nombre, String archivo_registros, IRecepcion recepcion) {
		this.nombre = nombre;
		this.archivo_registros = archivo_registros;
		this.lista_reservas = new ArrayList<Reserva>();
		this.pivote = recepcion;
	}

	/**
	 * Funcion que lee de los registros y en funcion del "tipo" selecciona que Reserva generar.
	 * 
	 * @param tipos - Especificacion de que tipos de Reserva debe Generar.
	 */
	protected void leer_registros(String tipos){
		for (String linea : LectorCSV.leer(archivo_registros)) {
			
			String[] dato = LectorCSV.separar_lineas(linea);
			
			// Verifica si el registro obtenido es del tipo especificado.
			if (! tipos.contains(dato[0])) {
				dato[0] = "0";
			}
			
			switch (Integer.parseInt(dato[0])) {
			
			case 1:// Reserva de Sector.
				lista_reservas.add(new Reserva(Long.parseLong((dato[1])),
						dato[2],
						Boolean.valueOf(dato[3]),
						dato[4],
						Obtener_Valores(dato[7])));				
				break;
				
			case 2:// Reserva de una Fila en un Sector.
				lista_reservas.add(new Reserva(Long.parseLong((dato[1])),
						dato[2],
						Boolean.valueOf(dato[3]),
						dato[4],
						Boolean.valueOf(dato[5]),
						dato[6],
						Obtener_Valores(dato[7])));
				break;
				
			case 3:// Reserva de un conjunto de Asientos.
				lista_reservas.add(new Reserva(Long.parseLong((dato[1])),
						dato[2],
						Boolean.valueOf(dato[3]),
						dato[4],
						Boolean.valueOf(dato[5]),
						dato[6],
						Boolean.valueOf(dato[8]),
						Conjunto_Asientos(dato[9])));	
				break;
				
			case 4:// Reserva de una Asiento especifico.
				lista_reservas.add(new Reserva(Long.parseLong((dato[1])),
						dato[2],
						Boolean.valueOf(dato[3]),
						dato[4],
						Boolean.valueOf(dato[5]),
						dato[6],
						Boolean.valueOf(dato[8]),
						Integer.parseInt(dato[10]),
						Boolean.valueOf(dato[11])));
				break;
			default:// Ninguna Reserva se realiza.
				break;
			}
		}
	}

	/**
	 * De un String se obtiene un objeto Date especificandole el formato en que
	 * los valores del String tiene que ubicarse.
	 * 
	 * @param fecha - String con la fecha detallada.
	 * @return - Retorna el Objeto Date.
	 */
	protected Date Obtener_fecha(String fecha){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			return df.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Separa un estring en fragmentos. La division es en base a los "-"
	 * 
	 * @param valor String que se desea fragmentar.
	 * @return - Lista de los fragmentos.
	 */
	protected String[] Obtener_Valores(String valor){
		if (valor.equals("null")) {
			String[] nulo = {};
			return nulo;
		}else return valor.split("-");
	}

	/**
	 * En funcion de un String genera un diccionario que corresponde a el Asiento y si es Especial.
	 * 
	 * @param conjunto - String a fragmentar.
	 * @return - Devuelve un diccionario con Asientos y su valor correspondiente a si es Especial.
	 */
	protected Hashtable<Integer, String> Conjunto_Asientos(String conjunto){
		Hashtable<Integer, String> tabla = new Hashtable<Integer, String>();
		for (String par : conjunto.split(",")) {
			String[] num_es = par.split("-");
			tabla.put(Integer.parseInt(num_es[0]), num_es[1]);
		}
		return tabla;
	}

	/**
	 * Muestra por consola las Reservas enla lista de Reservas.
	 */
	public void Ver_reservas(){
		for (Reserva a : lista_reservas) {
			a.Ver();
			System.out.println("----------------------------------------------------------------------");
		}
	}

	
	/**
	 * Run del Hilo.
	 */
	@Override
	public void run() {
		while(! lista_reservas.isEmpty()){
			for (Reserva R : lista_reservas) {
				if (R.tiempo >= System.currentTimeMillis()) {
					pivote.Enviar_Reserva(R);
					lista_reservas.remove(R);
					break;
				}
			}
		}
	}
}
