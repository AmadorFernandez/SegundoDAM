import java.util.Random;

public class Vehiculo extends Thread {
	int nombre;
	int nPlazas;
	Parking p;
	
	public Vehiculo(int nombre, int nPlazas, Parking p){
		this.nombre =nombre;
		this.nPlazas = nPlazas;
		this.p = p;
	}
	
	
	public void run(){
		while(true){
			try {
				Random random = new Random();
				System.out.println("El vehículo "+nombre+" está esperando fuera");
				Aparcar();
				Salir();
				Thread.sleep((long)1500+random.nextInt(1501));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void Aparcar() throws InterruptedException{
		p.solicitarAparcamiento(nombre,nPlazas);
		Random random = new Random();
		Thread.sleep((long)3000+random.nextInt(3001));
	}
	
	public void Salir(){
		p.abandonarParking(nombre);
	}

}
