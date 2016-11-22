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
	
	/*Este método actualizar los envios recibidos, reiniciará el contador al llegar a 3 y
	 * aumentará el contador de los días en uno. De este modo se consigue simular un nuevo
	 * día.
	 * 
	 * No es necesario sincronizarlo porque la llamada a este método se hace desde el metodo
	 * (entrada) que si lo esta. Así mismo, el metodo (salida) también lo esta y por lo tanto,
	 * ningun hilo puede hacer uso de el, ya que al llamar a (entrada) el resto de metodos
	 * que hacen uso de sycronized quedan bloqueados hasta que se libere el recurso compartido.
	 * Entre esto y los tiempos dados a los sleep en los hilos, es "imposible" que pueda salir
	 * más de un pedido al día. 	 * 
	 *	 * 
	 * */
	private void actualizarLlegadas(){
		
		this.nEnvios++;
		if(this.nEnvios == 3){
			
			this.nEnvios = 0;
			this.dia++;
			System.out.println("\nDía "+this.dia+"\n");
			
		}
	}
	
	/*
	 * Es el metodo al que llaman los envios para pasarle la cantidad de piezas que han generado
	 * se sincroniza para evitar el aceso simultaneo a la variable stock de this y para evitar que
	 * mientras se esta incrementando el contador de envios y el de dias pueda entrar un pedido.
	 * */
	public synchronized void entrada(int cantidad){
		
		System.out.println("Llegan "+cantidad+" piezas"); //Informo
		//Hago un preconteo porque no me gusta meter operaciones en las guardas de las instrucciones de control y
		//porque la usare dos veces y odio repetir calculos de forma innecesaria
		int preConteo = cantidad + this.stock;
		
		//Verifica que no se supere la capacidad máxima
		if(preConteo > CAPACIDAD_MAXIMA){
			//Si se ha superado informamos del error poniendo el error a true
			this.error = true;
			//Informo de que nos hemos pasado
			System.out.println("La suma es de "+preConteo+" piezas y supera la capacidad del almacen");
			
		}else {
			//Si se el envio cabe en el almacen pa entro
			this.stock += cantidad;
			System.out.println("Hay "+this.stock+" piezas en el almacen"); //Informo
			actualizarLlegadas();//Actualizo
			
		}
		
	}
	
	/*
	 * Metodo para las salidas y sincronizado para evitar errores en el conteo del stock
	 * */
	public synchronized void salida(int nPiezas){
				
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
