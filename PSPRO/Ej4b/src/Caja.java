import java.util.Random;

public class Caja extends Thread {
	
	private int id;
	private Object cerrojo;
	private Random alea;
	private boolean miTurno;
	
	//Getters and Setters
	public boolean isMiTurno() {
		return miTurno;
	}
	public void setMiTurno(boolean miTurno) {
		this.miTurno = miTurno;
	}
	public int getIdCliente() {
		return id;
	}
	public Object getCerrojo() {
		return cerrojo;
	}
	
	//Constructor
	public Caja(int id, Object cerrojo) {
		super();
		this.id = id;
		this.cerrojo = cerrojo;
		this.alea = new Random();
		this.miTurno = false;
	}
	
   public void run() {};

	@Override
	public boolean equals(Object obj) {
		
		Cliente c;
		boolean resultado = false;
		
		if(obj != null){
			
			if(obj instanceof Cliente){
				
				c = (Cliente)obj;
				resultado = c.getIdCliente().equals(this.id);
			}
		}
		
		return resultado;
	}
	
	

}
