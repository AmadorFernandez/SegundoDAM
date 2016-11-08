import java.util.Random;

public class Coche extends Thread {
	int id;
	Parking parking;
	
	public Coche(int numero, Parking parking) {
		this.id = numero;
		this.parking = parking;
	}
	
	public void run() {
		while (!Parking.cerrado) {
			System.out.println("El coche " + id + " se dirige al parking...");
			Random rnd = new Random();
			try {
				parking.entrada(id);
				Thread.sleep(2000 + rnd.nextInt(2001));
			} catch (InterruptedException e) {e.printStackTrace();}
			parking.salida(id);
			try {
				Thread.sleep(2000 + rnd.nextInt(2001));
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
