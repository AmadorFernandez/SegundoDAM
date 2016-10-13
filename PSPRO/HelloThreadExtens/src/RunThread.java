
public class RunThread {

	public static void main(String[] args) {
          
		HelloThread helloThread = new HelloThread();
		helloThread.start();
		System.out.println("Hola desde el main");
		System.out.println("Adios desde el main");
		

	}

}

class HelloThread extends Thread{
	
	
	@Override
	public void run() {
		
		super.run();
		System.out.println("Hola desde el hilo creado");
	}
	
}
