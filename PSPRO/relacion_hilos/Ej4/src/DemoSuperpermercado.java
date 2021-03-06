
public class DemoSuperpermercado {

	public static void main(String[] args) {
		
		int nCajas = 2;
		int nClientes = 100000;
		long inicio = 0;
		long fin = 0;
		double total = 0;
		Supermercado supermercado = new Supermercado(nCajas);
		Cliente[] clientes = new Cliente[nClientes];
		
		inicio = System.currentTimeMillis();
		for (int i = 0; i < nClientes; i++) {
			
			clientes[i] = new Cliente(i+1, supermercado.getCajas());
			clientes[i].start();
		}
		
		for (int i = 0; i < clientes.length; i++) {
			
			try {
				clientes[i].join();
			} catch (InterruptedException e) {
				
			}
		}
		
		fin = System.currentTimeMillis();
		
		total = fin - inicio;
		
		System.out.println("Se ha recaudado un total de "+Supermercado.setResultado());
		System.out.println("Se ha conseguido una media de atencion por cliente de  "+total/nClientes+" milisegundos\n en atender a "+nClientes+" clientes "
				+ "con "+nCajas+" cajas y el uso de este algoritmo");
		
		

	}

}
