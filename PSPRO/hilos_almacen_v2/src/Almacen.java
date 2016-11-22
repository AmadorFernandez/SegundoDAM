import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadataFormat;

public class Almacen {

	//La capacidad máxima exigida en el enunciado
	public static final int CAPACIDAD_MAXIMA = 20000;
	//Llevará la cuenta del número de envíos que entrán en el almacen
	private int nEnvios;
	//Llevará la cuenta de los días transcurridos
	private int dia;
	//Pos la cuenta de las piezas
	private int stock;
	//La banderita para que los hilos no esten en un bucle infinito
	private boolean error;
	
	
	
	
	public Almacen(){
		
		this.dia = 1; //Primer dia de curro
		this.error = false; //¿Como va ha haber errores si no hemos hecho namas que empezar?
		this.stock = 8000; //Cantidad inicial de stock exigida
		this.nEnvios = 0; //Acabos de abrir por dios
		System.out.println("\nDía "+this.dia+"\n"); //Ala al curro
	}
	
	private void actualizarLlegadas(){
		
		this.nEnvios++;
		if(this.nEnvios == 3){
			
			this.nEnvios = 0;
			notifyAll();
			System.out.println("\nDía "+this.dia+"\n");
			
		}
	}
	

	public synchronized void entrada(int cantidad){
		
		//Si ya se han realizado 3 envios no entran mas en el dia de hoy
		while (nEnvios == 3) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("Llegan "+cantidad+" piezas"); 
		int preConteo = cantidad + this.stock;		
		if(preConteo > CAPACIDAD_MAXIMA){
			
			this.error = true;
			
			System.out.println("La suma es de "+preConteo+" piezas y supera la capacidad del almacen");
			
		}else {
			
			this.stock += cantidad;
			System.out.println("Hay "+this.stock+" piezas en el almacen"); 
			actualizarLlegadas();
			
		}
		
	}
	
	/*
	 * Metodo para las salidas y sincronizado para evitar errores en el conteo del stock
	 * */
	public synchronized void salida(int nPiezas){
				
		while (this.nEnvios != 0) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Pedido de "+nPiezas+" piezas");
		
		//Si no hay stock para cubrir el pedido
		if(this.stock < nPiezas){
			
			this.error = true; //Informamos a los envios en espera (1 en este caso)
			System.out.println("Hay "+this.stock+" piezas en el almacen y no se puede cubrir el pedido"); //Informo
			
			
		}else {
			
			//Por el contrario, si el almacen puede hacer frente al pedido se le resta al stock
			this.stock -= nPiezas;
			System.out.println("Hay "+this.stock+" piezas en el almacen"); //Informo
			
		}
		
		
		
	}
	
	public synchronized boolean hayError(){
		
		return this.error;
	    
	}
	
 
	
	
	
	
	
}
