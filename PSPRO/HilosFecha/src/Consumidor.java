import java.util.Random;

public class Consumidor extends Thread {
	
private Buffer buffer;
	
	public Consumidor(String name, Buffer elBuffer){
		
		super(name);
		this.buffer = elBuffer;
	}
	
	
	
	@Override
	public void run() {
		
		Random random = new Random();
		
		for (int i = 0; i < 20; i++) {
			
			buffer.leer();
			try {
				sleep(random.nextInt(3000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
