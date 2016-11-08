
public class PruebaBufferCircular {

	public static void main(String[] args) {
		
		IBuffer elBuffer = new BufferCircular();
		
		Productor prod = new Productor(elBuffer);
		Consumidor consu = new Consumidor(elBuffer);
		
		prod.start();
		consu.start();
		
	
		try{
			prod.join(); 
			consu.join();
		}catch(InterruptedException ex){
			
		}

	}

}
