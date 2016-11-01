import java.util.Random;

public class Cliente extends Thread {
	
	
	private int id;
	private Random alea;
	private Object mutex;
	private boolean miTurno;
	
	
	
	
	public boolean isMiTurno() {
		return miTurno;
	}




	public void setMiTurno(boolean miTurno) {
		this.miTurno = miTurno;
	}




	public int getIdCliente() {
		return id;
	}




	public Object getMutex() {
		return mutex;
	}




	public Cliente(int id, Object mutex) {
		this.id = id;
		this.mutex = mutex;
		this.alea = new Random();
		this.miTurno = false;
	}


	private void comprar(){
		
		try {
			Thread.sleep(alea.nextInt(5000)+1000);
		} catch (InterruptedException e) {
			
		}
	}

	public void run(){
		
		comprar();
		
		while (!miTurno) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				//???
				
			}
			
		}
		
	}




	@Override
	public boolean equals(Object obj) {
		
		boolean resultado = false;
		
		if(obj != null){
			
			if(obj instanceof Cliente){
				
				resultado = ((Cliente)obj).getIdCliente() == this.id;
			}
		}
		
		return resultado;
	}
	
	

}
