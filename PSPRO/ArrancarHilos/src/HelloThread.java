
public class HelloThread {
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	  CreateThread thread =	new CreateThread();
		System.out.println("Hola desde el main");
		System.out.println("Hilo finalizado");
		
		
		

	}

}

class CreateThread implements Runnable{

	
	Thread t;
	
	public CreateThread() {
		// TODO Auto-generated constructor stub
		
	    Thread	th = new Thread(this, "Nuevo thread");
	    th.start(); //Esto no garantiza la ejecucion del hilo, lo que hace es indicar que esta esperando un core de la cpu
		System.out.println("Creando el hilo");
	
	}
	
	
	public void run() {
		
		System.out.println("Hola desde el hilo");
		System.out.println("Hilo secundario finalizado");
		
	}
	
	
	
}
