import java.util.Random;

public class Cliente extends Thread {

	private int nCliente;
	private SuperModerno superm;
	private Random random;
	private Caja cajaAsignada;
	
	public Cliente(int nCliente, SuperModerno superm){
		
		this.nCliente = nCliente;
		this.superm = superm;
		this.random = new Random();
		
	}
	
	public int getNcliente(){
		
		return this.nCliente;
	}
	
	private void comprar(){
		
		//Simula un tiempo aleatorio comprando
		System.out.println("El cliente "+nCliente+" esta comprando");
		try {
			Thread.sleep((int)Math.random()*5001);
		} catch (InterruptedException e) {
			
		}
		
	}
	
	public void setCaja(Caja caja){
		//Le indica al cliente a que caja tiene que ir
		this.cajaAsignada = caja;
	}
	
	private void aLaCaja(){
		
		superm.encolar(this);		
	}
	
	private void pagar(){
		//Vuelvo a poner una cantidad constante para que se puede comprobar que se controlan las condiciones de carrera
		//y indica en que caja fue atendido para que sea puesta de nuevo en activa
		superm.cobrar(100, this.cajaAsignada);
	}
	
	@Override
	public void run(){
		
		//La secuencia de acciones que se espera que haga el cliente
		comprar(); 
		aLaCaja();
		pagar();
		System.out.println("El cliente "+this.nCliente+" sale del super cantado bajito");
	
	}
	
}
