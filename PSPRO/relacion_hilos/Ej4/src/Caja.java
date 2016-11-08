
public class Caja {

	private boolean ocupada;
	private int nCaja;
	private Supermercado supermercado;
	
	public Caja(int nCaja){
		
		this.nCaja = nCaja;
		this.ocupada = false;
		
	}
	
	public synchronized void atender(Cliente cliente){
		
		//Si la caja esta ocupada el cliente espera
		while (ocupada) {			
			try {
				System.out.println("El cliente "+cliente.getNcliente()+" esta en la cola de la caja "+this.nCaja);
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
	}
	
	public synchronized void setOcupada(boolean ocupada){
		
		//Cuando el cliente paga indicará que esta caja esta libre
		this.ocupada = ocupada;
		
		//Si la caja esta libre se notifica al siguiente cliente en la cola
		if(!ocupada){
			
			notify();
		}
	}
	
	
}
