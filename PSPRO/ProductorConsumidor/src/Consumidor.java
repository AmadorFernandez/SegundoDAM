
public class Consumidor extends Thread {
	
private IBuffer compartido;

	
	public Consumidor(IBuffer bufer){
		super("Consumidor");
		compartido = bufer;
	}
	
	@Override
	public void run(){
		
		int suma = 0;
		
		for (int i = 0; i<20; i++){
			try{
				Thread.sleep((int)Math.random() * 3001);
				suma += compartido.leer();
			}catch(InterruptedException ex){
				
			}
			
		}
		
		System.out.println(getName()+" terminó de consumir datos");
		System.out.println("Suma: "+suma);
	}

}
