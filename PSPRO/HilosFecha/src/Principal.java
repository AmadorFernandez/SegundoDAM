import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class Principal {

	public static void main(String[] args) {
		
			Buffer elBuffer = new Buffer();
			Productor productor = new Productor("El productor", elBuffer);
			Consumidor consumidor = new Consumidor("El consumidor", elBuffer);
			
			productor.start();
			consumidor.start();

	}

}
