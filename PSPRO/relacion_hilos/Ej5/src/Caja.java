

/*
 * La clase caja en este caso es mucho más simple, ya que solo 
 * hay que indicarle cuando esta ocupada y cuando no lo esta

*/
public class Caja {

	private boolean ocupada;
	private int nCaja;
	
	
	public Caja(int nCaja){
		
		this.nCaja = nCaja;
		this.ocupada = false;
		
	}
	
		
	public boolean isOcupada(){
		
		return this.ocupada;
	}
	
	public int getNCaja(){
		
		return this.nCaja;
	}
	
	public void setOcupada(boolean ocupada){
		
		//Cuando el cliente paga indicará que esta caja esta libre
		this.ocupada = ocupada;		
	}
	
}
