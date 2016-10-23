import java.util.ArrayList;


public class Demo {

	public static void main(String[] args) {
		
		Supermercado supermercado = new Supermercado();
		
		supermercado.cajas = new Caja[5];
		
		for(int i = 0; i < supermercado.cajas.length; i++){
			
			supermercado.cajas[i] = new Caja(i+1, supermercado);
		}
		
		for(int i = 0; i < 255; i++){
			
			supermercado.clientes.add(new Cliente(i+1, supermercado));
			supermercado.clientes.get(i).start();
		}
		
		
		
		
	   while (supermercado.clientes.size() > 0) {
		   
		     for (int i = 0; i < supermercado.cajas.length; i++) {
				
		    	 supermercado.cajas[i].atenderClientes();
			}
		
	   	}
	   
	   
	   System.out.println("Los clientes han gastado: "+supermercado.gastoClientes);
	   System.out.println("La recaudacion ha sido de: "+supermercado.valor);
	   System.out.println("El tiempo de atencion ha sido de: "+supermercado.tiempoDeAtencion);
	
		
		
	}
	
	
	

	
}
