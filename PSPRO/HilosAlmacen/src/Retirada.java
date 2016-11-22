import java.util.Random;

public class Retirada extends Thread {
	//El objeto compartido
	private Almacen almacen; 
	//Las cantidades mínimas y máximas exigidas en el enunciado
	public static final int MIN_CANTIDAD = 2000;
	public static final int MAX_CANTIDAD = 2500;
    private	Random randon;
    
    public Retirada(Almacen elAlmacen){
    	
    	this.almacen = elAlmacen;
    	this.randon = new Random();
    }
    
    @Override
    public void run(){
    	
    	//Realizara retiradas mientra en el almacen no se genere un error
    	while (!this.almacen.hayError()) {
    		
    		
    		try {
    			//Con esta operación se consigue que la cantidad de la retirada oscile entre los margenes requeridos por el enunciado
    			this.almacen.salida(MIN_CANTIDAD + randon.nextInt(MAX_CANTIDAD - MIN_CANTIDAD));
				sleep(2400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    }
	
	
	

}
