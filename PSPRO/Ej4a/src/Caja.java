
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

public class Caja {

	private Queue<Cliente> cola;
	int nCaja;
	Supermercado supermercado;
	
	
	public Queue<Cliente> getCola(){
		
		return this.cola;
	
	}
	
	public Caja(int nCaja, Supermercado s){

		this.cola = new LinkedList<Cliente>();
	    this.nCaja = nCaja;
	    this.supermercado = s;
	}
	
	public void atenderClientes(){
		
		Cliente cliente;
		long timeEntrada;
		long timeSalida;
		
		if(this.cola.size() > 0){
			
			timeEntrada = System.currentTimeMillis();
			cliente = this.cola.poll();
			cliente.pagar();
			supermercado.clientes.remove(cliente);
			timeSalida = System.currentTimeMillis();			
			supermercado.tiempoDeAtencion += (timeSalida - timeEntrada);
		//	System.out.println("Cliente atendido y me he gastado "+cliente.getGastado()+" "+cliente.getIdCliente()+ "He sido atendido or la caja"+this.nCaja);
			cliente.setAtendido(true);
			
		}
	}
	
	
}
