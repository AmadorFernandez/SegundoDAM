
import java.util.Random;

import javax.management.monitor.Monitor;

public class Cliente extends Thread  {

	private int id;
	private Random alea;
	private boolean atendido;
	private double gastado;
	Supermercado supermercado;
	
	
	public void setId(int value){
		
		this.id = value;
	}
	
	public int getIdCliente(){
		
		return this.id;
	}
	
	
	
	
	public void setAtendido(boolean value){		
		this.atendido = value;		
	}
	
	
	
	public boolean isAtendido(){
		
		return atendido;
	}
	
	public double getGastado(){
		
		return this.gastado;
	}
	
	
	
	public Cliente(int id, Supermercado s){
		
		
		this.alea = new Random();
		this.atendido = false;
		this.id = id;
		this.gastado = 0;
		supermercado = s;
		
	}
	
	private void comprar() {
		
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			//???
		}
	}
	
	public void pagar(){
				
		this.gastado =  100;			
		supermercado.sumValor(this.gastado);
				
	}
	
	public void enColar(){
		
		int caja = alea.nextInt(supermercado.cajas.length);
		
		synchronized (supermercado.cajas) {
			
			supermercado.cajas[caja].getCola().add(this);	
		}
			
	}
	
	public void run(){
		
		comprar();
		enColar();
	//	pagar();									
	}
	
	@Override
	public boolean equals(Object obj) {
		
	     boolean resultado = false;
	     
	     if(obj != null){
	    	 
	    	 if(obj instanceof Cliente){
	    		 
	    		 resultado = ((Cliente)obj).getIdCliente()== this.id;
	    	 }
	     }
	     
	     return resultado;
	}
		
}
