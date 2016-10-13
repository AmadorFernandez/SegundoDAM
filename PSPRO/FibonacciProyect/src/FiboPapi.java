import javax.swing.text.Position;

public class FiboPapi {
	
	//Variable a travez que manipularan los tres hilos
	public static long[] sucesion;
	
	public static void main(String[] args) {
		
		
		int n = 200; //Numero de sucesiones que queremos
		sucesion = new long[n];
		FiboNene[] hilos = new FiboNene[2]; //Agrupa los dos hilos
		
		//Las dos primeras pocisiones son sabidas
		sucesion[0] = 0;
		sucesion[1] = 1;
		
		
		for (int i = 2; i < sucesion.length; i++) {
			
			//Recorre el numero de sucesiones instanciando los hilos y ejecutandolos
			hilos[0] = new FiboNene(i, i - 1);
			hilos[1] = new FiboNene(i, i - 2);
			//A currar
			hilos[0].start();
			hilos[1].start();
			
			try {
				//Con esto el HILO PADRE esperará a que los otros dos terminen
				//evitando que se escriba antes de que los hilos realicen los calculos	
				hilos[0].join();
				hilos[1].join();
			} catch (InterruptedException e) {
				// Aqui ahora mismo nada
				
			}
			
			//A escribir el resultado
			System.out.print(sucesion[i]+", ");
			
		}
		
	}

}

class FiboNene extends Thread{
	

	//pos indica la posicion del numero que estamos calculando
	//n le indica la posicion del numero que le tiene que sumar	
    private	int pos;
	private int n;
    
	public FiboNene(int pos, int n){
	
		this.pos = pos;
		this.n = n;
		
	}
	
	@Override
	public void run() {
		
		//Suma las pocisiones indicadas
		FiboPapi.sucesion[pos] += FiboPapi.sucesion[n];
	     	
	}
	
}
