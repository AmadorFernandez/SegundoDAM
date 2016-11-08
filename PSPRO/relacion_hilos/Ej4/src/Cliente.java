import java.awt.PageAttributes;
import java.util.Random;

public class Cliente extends Thread {
	
	private int nCliente;
	private Caja[] cajas;
	private Random random;
	private int cajaEscojida;
	
	public Cliente(int nCliente, Caja[] cajas){
		
		this.nCliente = nCliente;
		this.cajas = cajas;
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
	
	private void aLaCaja(){
		
		//El cliente escoje una caja
	    this.cajaEscojida = random.nextInt(cajas.length);
	    System.out.println("El cliente "+this.nCliente+" se pone a la cola de la caja "+this.cajaEscojida);
	    //Indica que quiere ser atendido a la caja y alli se encotrar con el wait si la caja esta ocupada
		this.cajas[cajaEscojida].atender(this);
		
		
	}
	
	private void pagar(){
		
		//he puesto una cantidad fija para que se pueda calcual previamente el dinero que se espera optener
		//y de ese modo comprabar que este ejercicio tambien controla las condiciones de carrera
		Supermercado.ingresar(100);
		//Como ya ha pagado se simula que dejo la caja libre
		this.cajas[cajaEscojida].setOcupada(false);
	}
	
	@Override
	public void run(){
		
		//La secuencia de acciones que se espera que haga el cliente
		comprar(); 
		aLaCaja();
		//Si llega a este punto es que salio del bucle o no tuvo necesidad de entrar y se simula que la caja quedo libre
		this.cajas[cajaEscojida].setOcupada(true);
		pagar();
		System.out.println("El cliente "+this.nCliente+" ha pagado");
	}
	

	

}
