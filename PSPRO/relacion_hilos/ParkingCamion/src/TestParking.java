

public class TestParking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] garaje = new int[6];
		Parking parking = new Parking(garaje);
		
		Vehiculo coche1 = new Vehiculo(1, 1, parking);
		Vehiculo coche2 = new Vehiculo(2, 1, parking);
		Vehiculo coche3 = new Vehiculo(3, 1, parking);
		Vehiculo camion1 = new Vehiculo(101, 2, parking);
		Vehiculo camion2 = new Vehiculo(102, 2, parking);
		
		coche1.start();
		coche2.start();
		coche3.start();
		camion1.start();
		camion2.start();
		
		try {
			Thread.sleep(10000);
			parking.cerrado = true;
			coche1.join();
			coche2.join();
			coche3.join();
			camion1.join();
			camion2.join();
			System.out.println("El parquin ha cerrado");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
