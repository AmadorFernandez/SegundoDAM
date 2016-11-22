import java.util.Random;

public class Envio extends Thread {

	//El objeto compartido
	private Almacen almacen;
	//Las cantidades mínimas y máximas exigidas en el enunciado
	public static final int MIN_CANTIDAD = 400;
	public static final int MAX_CANTIDAD = 1000;
    private	Random randon;
    
    public Envio(Almacen elAlmacen){
    	
    	this.almacen = elAlmacen;
    	this.randon = new Random();
    }
    
    @Override
    public void run(){
    	
    	//Realizara envíos mientra en el almacen no se genere un error
    	while (!this.almacen.hayError()) {
    		
    		   		
    		//Con esta operación se consigue que la cantidad de la retirada oscile entre los margenes requeridos por el enunciado
    		this.almacen.entrada(MIN_CANTIDAD + randon.nextInt(MAX_CANTIDAD - MIN_CANTIDAD));
					
		}
	
}}
