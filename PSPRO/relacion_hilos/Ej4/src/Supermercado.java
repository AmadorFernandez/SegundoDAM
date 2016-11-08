
public class Supermercado {
	
	private Caja[] cajas;
	private static double resultado;

	
	public Caja[] getCajas(){
		
		return cajas;
	}
	
	public synchronized static void ingresar(double ingreso){
		
		resultado += ingreso;
	}
	
	public static double setResultado(){
		
		return resultado;
	}
	
	public Supermercado(int nCajas) {
		
		this.cajas = new Caja[nCajas];
		
		for (int i = 0; i < cajas.length; i++) {
			
			cajas[i] = new Caja(i+1);
		}
		
	}

}
