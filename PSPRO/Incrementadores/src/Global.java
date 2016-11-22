import java.util.concurrent.atomic.AtomicInteger;

public class Global {

	public static AtomicInteger cont = new AtomicInteger(0);
	
	public static void incrementar(int n){
		
		for (int i = 0; i < n; i++) {
			
			cont.getAndIncrement();
		}
		
		
	}
	
}
