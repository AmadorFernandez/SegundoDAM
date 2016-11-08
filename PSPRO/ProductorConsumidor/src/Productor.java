
public class Productor extends Thread {
	
private IBuffer compartido;
	
	public Productor(IBuffer bufer){
		super("Productor");
		compartido = bufer;
	}
	
	@Override
	public void run(){
		for (int i = 0; i<10; i++){
			try{
				Thread.sleep((int)Math.random() * 3001);
				compartido.escribir(i);
			}catch(InterruptedException ex){
				
			}
			
		}
		
		System.out.println(getName()+" terminó de producir datos");
	}

}
