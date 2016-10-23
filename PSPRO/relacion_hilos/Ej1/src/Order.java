
public class Order {
	
	
	private static HelloThread threadOne;
	private static HelloThread threadTwo;

	public static void main(String[] args) {
		
		 instanceThread();
		 startUI();

	}

	private static void startUI() {
		
		
		
		
		try {
			
				//The thread 2 starts and immediately tells the main thread to wait
				threadTwo.start();
				threadTwo.join();
				//Once the thread finished the first two will do the same.
				threadOne.start();
				threadOne.join();
				
			System.out.println("Finished program.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void instanceThread() {
		//Stay threads
		threadOne = new HelloThread(1);
		threadTwo = new HelloThread(2);
		
	}

}

class HelloThread extends Thread{
	
	
	private int nThread;
	
	//The number of yarn is given in the constructor
	public HelloThread(int nThread){
		
		this.nThread = nThread;
		
	}
	
	public void run(){
		
		//The thread greets indicating who is
		System.out.println("Hello, I am number "+this.nThread+".");
		
	}
	
	
}
