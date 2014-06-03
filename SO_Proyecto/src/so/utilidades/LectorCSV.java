package so.utilidades;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LectorCSV {
	
	public void leer() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("Texto.txt"));
	    try {
	        String line = br.readLine();

	        while (line != null) {
	        	System.out.println(line);
	        	String[] palabras = line.split(";");
	        	for (String pal : palabras) {
					System.out.println(pal);
				}
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	}
	
	public void Leer_confi() throws FileNotFoundException{
		Properties prop = new Properties();
	    String fileName = "Conf.txt";
	    InputStream is = new FileInputStream(fileName);

	    try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    System.out.println(prop.getProperty("Primera.Fecha"));
	    System.out.println(prop.getProperty("Segunda.Fecha"));
	}
}
