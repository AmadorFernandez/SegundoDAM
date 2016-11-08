
public class Parking {
	int coches;
	int[] plazas;
	int plazasDisponibles;
	public static boolean cerrado;
	
	String cerrojo = "";
	
	public Parking(int[] plazas, int cantidad) {
		this.coches = cantidad;
		this.plazas = plazas;
		this.plazasDisponibles = plazas.length;
		Parking.cerrado = false;
		System.out.println("El parking está abierto");
	}
	
	public synchronized void entrada(int coche) throws InterruptedException {
		while (plazasDisponibles <= 0) {
			System.out.println("No hay plazas disponibles para el coche " + coche);
			wait();
		}
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == 0) {
				plazas[i] = coche;
				System.out.println("El coche " + coche + " ha entrado en la plaza " + i);
				plazasDisponibles--;
				break;
			}
		}
	}
	
	public synchronized void salida(int coche) {
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == coche && !cerrado) {
				plazas[i] = 0;
				System.out.println("El coche " + coche + " ha salido de la plaza " + i);
				plazasDisponibles++;
				notifyAll();
				return;
			}
		}
	}
	
	public int plazasDisponibles() {
		String cadena = "";
		plazasDisponibles = 0;
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == 0) {
				cadena += i + " ";
				plazasDisponibles++;
			}
		}
		System.out.println("Plazas disponibles: " + cadena);
		return plazasDisponibles;
	}
}
