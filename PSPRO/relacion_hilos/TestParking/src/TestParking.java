
public class TestParking {
	
	static int[] Plazas = new int[] { 0, 0, 0, 0, 0 };

	public static void main(String[] args) {
		Parking parking = new Parking(Plazas, 10);
		Coche c1 = new Coche(1, parking);
		Coche c2 = new Coche(2, parking);
		Coche c3 = new Coche(3, parking);
		Coche c4 = new Coche(4, parking);
		Coche c5 = new Coche(5, parking);
		Coche c6 = new Coche(6, parking);
		Coche c7 = new Coche(7, parking);
		Coche c8 = new Coche(8, parking);
		Coche c9 = new Coche(9, parking);
		Coche c10 = new Coche(10, parking);
		
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		c6.start();
		c7.start();
		c8.start();
		c9.start();
		c10.start();
		
		try {
			Thread.sleep(10000);
			Parking.cerrado = true;
			System.out.println("El parking ha cerrado");
			c1.join();
			c2.join();
			c3.join();
			c4.join();
			c5.join();
			c6.join();
			c7.join();
			c8.join();
			c9.join();
			c10.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
