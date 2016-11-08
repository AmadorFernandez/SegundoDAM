
public class Parking {
	
	int[] plazas;
	boolean cerrado;

	
	public Parking(int[] plazas){
		this.plazas = plazas;
		this.cerrado = false;
	}

	public synchronized void solicitarAparcamiento(int nombre, int nPlazas){
		try{
			if(nPlazas == 2){//Camion
				while(!buscarPlazaCamion()){
					wait();
				}
				asignarPlaza(nPlazas, nombre);
			}
			
			if(nPlazas == 1){//Coche
				while(!buscarPlazaCoche()){
					wait();
				}
				asignarPlaza(nPlazas, nombre);
			}
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized boolean buscarPlazaCamion(){
		for (int i = 1; i < plazas.length; i++) {
			if(plazas[i] == 0 && plazas[i-1] == 0){
				notifyAll();
				return true;
			}		
		}
		return  false;
		
		
	}
	
	public synchronized boolean buscarPlazaCoche(){
		for (int i = 1; i < plazas.length; i++) {
			if(plazas[i] == 0){
				notifyAll();
				return  true;
			}		
		}
		return  false;
	}
	
	public void asignarPlaza(int nPlaza, int nombre){
		if(nPlaza == 2){
			for (int i = 1; i < plazas.length; i++) {
				if(plazas[i] == 0 && plazas[i-1] == 0){
					plazas[i] = nombre;
					plazas[i-1] = nombre;
					System.out.println("El camión "+nombre+" aparca en la plaza "+i);
					mostrarPlazas();
					return;
				}
			}
		}else{
			for (int i = 0; i < plazas.length; i++) {
				if(plazas[i] == 0){
					plazas[i] = nombre;
					System.out.println("El coche "+nombre+" aparca en la plaza "+i);
					mostrarPlazas();
					return;
				}
			}
		}
	}
	
	public void abandonarParking(int nombre){
		for (int i = 1; i < plazas.length; i++) {
			if(plazas[i] == nombre){
				plazas[i] = 0;
				System.out.println("El vehículo "+nombre+" ha abandonado el parking");
			}
		}
		

	}
	
	public void mostrarPlazas(){
		for (int i = 0; i < plazas.length; i++) {
			System.out.print("["+this.plazas[i]+"] ");
		}
		System.out.println("\n");
	}
}
