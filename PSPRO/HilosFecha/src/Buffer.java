
public class Buffer {
	
	private String[] fechas = {"","",""};
	private int posEscritura = 0;
	private int posLectura = 0;
	private int contOcupado = 0;
	
	public synchronized void escribir(String fecha){
		
		while (contOcupado == fechas.length) {
			
			System.out.println(Thread.currentThread().getName()+"Trata de escribir la fecha");
			System.out.println(Thread.currentThread().getName()+"Espera");
			mostrarSalida();
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		fechas[posEscritura] = fecha;
		posEscritura = (posEscritura+1)%fechas.length;
		contOcupado++;
		System.out.println(Thread.currentThread().getName()+"Consigue escribir la fecha "+fecha);
		mostrarSalida();
		notify();
		
	}
	
	public synchronized void leer(){
		
		String fecha;
		
	    while (contOcupado == 0) {
	    	
	    	System.out.println(Thread.currentThread().getName()+"Trata de leer la fecha");
			System.out.println(Thread.currentThread().getName()+"Espera");
			mostrarSalida();
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	    
	    fecha = fechas[posLectura];
	    posLectura = (posLectura+1)%fechas.length;
	    contOcupado--;
	    System.out.println(Thread.currentThread().getName()+"Consigue leer la fecha "+fecha);
		mostrarSalida();
		notify();
	    
	}
	
	
	
	private synchronized void mostrarSalida(){
		
		for (int i = 0; i < fechas.length; i++) {
			
			System.out.println(fechas[i]);
		}
	}
	
	
	

}