
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Buffer elBuffer = new Buffer();
		Productor productor = new Productor(elBuffer, 5);
		Consumidor[] consumidors = new Consumidor[1];
		
		
		productor.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < consumidors.length; i++) {
			
			consumidors[i] = new Consumidor(elBuffer);
			consumidors[i].start();
		}
		
		
	}

}
