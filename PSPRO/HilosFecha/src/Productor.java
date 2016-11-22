import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Productor extends Thread {

	private Buffer buffer;
	
	public Productor(String name, Buffer elBuffer){
		
		super(name);
		this.buffer = elBuffer;
	}
	

	@Override
	public void run() {
		
		Random random = new Random();
		
		for (int i = 0; i < 20; i++) {
			
			Calendar calendar = new GregorianCalendar();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ms");
			String fecha = simpleDateFormat.format(calendar.getTime());
			buffer.escribir(fecha);
			try {
				sleep(random.nextInt(3000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}