import javax.sql.rowset.Joinable;

public class HelloRunable implements Runnable {

	private int id;
	private Thread thread;
	
	public HelloRunable(int id){
		
		this.id = id;
		this.thread = new Thread(this);
		thread.start();
	}
	
	public void join(){
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(id*1000);
			System.out.println("HOLA MUNDOOOOO!!! Soy el hilo "+id+" y implemento Runable");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}