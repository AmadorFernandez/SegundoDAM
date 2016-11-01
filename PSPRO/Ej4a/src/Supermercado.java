import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.monitor.Monitor;

class Supermercado {
	
	public  double valor = 0.0;	
	public  Caja[] cajas;
	public  ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public  long tiempoDeAtencion = 0;
	public  double gastoClientes = 0;
	
	
	public double getValor(){
		
		return valor;
	}
	
	public void sumValor(double v){
		  valor += v;			
	}

	
	
}
