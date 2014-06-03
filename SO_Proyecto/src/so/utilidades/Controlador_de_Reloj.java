package so.utilidades;

import java.util.Date;

public class Controlador_de_Reloj extends Thread {
	Date tiempo = new Date();
	
	@Override
	public void run() {
		while (true){
			
			try {
				Thread.sleep(1000); // Aca mando a dormir este hilo por un segundo.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tiempo.setTime(System.currentTimeMillis());	// Le asigno la fecha-hora de este momento.
			
			System.out.println(tiempo.toString());
			System.out.println(tiempo.getTime());
		}
	}

}
