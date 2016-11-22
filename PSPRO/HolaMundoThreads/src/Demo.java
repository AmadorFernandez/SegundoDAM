
public class Demo {

	public static void main(String[] args){
		
		HelloThread[] threads = new HelloThread[6];
		HelloRunable[] runables = new HelloRunable[6];
		
		
		for (int i = 0; i < runables.length; i++) {
			
			runables[i] = new HelloRunable(i+1);
		}
		
		for (int i = 0; i < runables.length; i++) {
			
			runables[i].join();
		}
		
		for (int i = 0; i < threads.length; i++) {
			
			threads[i] = new HelloThread(i+1);
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
