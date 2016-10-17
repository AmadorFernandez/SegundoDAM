
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * For this example I used the synchronice block, but there are other ways to do that give much more play.
 * In Java, there is the Lock interface and ReentrantLock and Condition classes that would be interesting to look at.
 * */
public class Principal {
	
	public static volatile int contador = 0;

	public static void main(String[] args) throws InterruptedException {
		
		// Instantiate objects and the type of operation it indicated making
		Operation operator = new Operation();	  
		MyThread thread1;
		MyThread thread2; 
		int impresion = 0;
		
		
		
		for (int i = 0; i < 500; i++) {
			
		   thread1 = new MyThread(operator, 1);
		   thread2 = new MyThread(operator, 2);
		   thread1.start();
		   thread2.start();
		   thread1.join();
		   thread2.join();
			
		   if(contador == 500){
			   
			System.out.println("El contador vale: "+contador);
			impresion++;
			
		   }
			contador = 0;
		}
    	
		
		System.out.println("El número de impresiones correctas es: "+impresion);
		

	}
	
	

}

//Instance two methods one for increase and another to decrease the counter.
class Operation extends Thread{
	 
	public  void sum(){
		
		
		for (int i = 0; i < 1000; i++) {
		
			Principal.contador++;
							
	
		}
}
	
	public  void sub(){
		
		
		for (int i = 0; i < 500; i++) {
		
			Principal.contador--;
							
	      }
}
	
	
	
}

class MyThread extends Thread {
	
	private	Operation operation;
	private int tipoOperacion;
	
	// Constructor that gives the thread instance to work and the type of operation.
	public MyThread(Operation operadorcete, int tipoOperacion){
		
		this.operation = operadorcete;
		this.tipoOperacion = tipoOperacion;
	}
	
	
	public void run() {
		
		// When synchronizing the object thread appropriates it and not allow another touch it until the end.
		// If you remove the same results in class (bad)
				
		synchronized (operation) {
			
			switch (this.tipoOperacion) {
			
				case 1:
				this.operation.sum();
				break;
				case 2:
				this.operation.sub();
				break;
			
		    }
		}
           
		                    
				
	}
    
    
	
    
}
