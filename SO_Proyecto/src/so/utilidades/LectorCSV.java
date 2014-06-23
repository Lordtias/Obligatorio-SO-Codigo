package so.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorCSV {
	
	/**
	 * Leo un archivo para obtener las lineas.
	 * @param archivo - Nombre del archivo
	 * @return - Retorna una lista de lineas del archivo.
	 */
	public static ArrayList<String> leer(String archivo){
		ArrayList<String> lineas = new ArrayList<String>();
		BufferedReader br = null;
	    try {
	    	br = new BufferedReader(new FileReader(archivo));
	        String line = br.readLine();
	        // Sobrescribimosla primera lineaque esel cabezal.
	        line = br.readLine();
	        
	        while (line != null) {
	        	lineas.add(line);
	            line = br.readLine();
	        }
	        
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return lineas;
	}
	
	/**
	 * Separo la linea en funcion del caracter ";"
	 * @param linea - Linea con los datos a separar.
	 * @return - Array de los datos separados.
	 */
	public static String[] separar_lineas(String linea){
		String[] palabras = linea.split(";");
		return palabras;
	}
}