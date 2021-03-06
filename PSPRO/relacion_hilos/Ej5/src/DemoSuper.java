
public class DemoSuper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int nClientes = 100000;
		int nCajas = 2;
		long ini = 0;
		long fin = 0;
		double total = 0;
		
		SuperModerno superModerno = new SuperModerno(nCajas, nClientes);
		Cliente[] clientes = new Cliente[nClientes];
		
		ini = System.currentTimeMillis();
		for (int i = 0; i < clientes.length; i++) {
			
			clientes[i] = new Cliente(i+1, superModerno);
			clientes[i].start();
			
		}
		
		for (int i = 0; i < clientes.length; i++) {
			
			try {
				clientes[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		fin = System.currentTimeMillis();
		total = fin - ini;
		System.out.println("Se han atendido a un total de "+nClientes+" con "+nCajas+" cajas\n con una media de atención por cliente de "
				+ total/nClientes+" milisegundos. con este algoritmo" );
		System.out.println("Se ha recaudado un total de "+superModerno.getResultado());
		System.out.println("El super cierra sus puertas por el día de hoy");

	}

}
