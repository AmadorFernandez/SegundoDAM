import java.util.Queue;

public class SuperModerno {

	
	private Caja[] cajas;
	private double resultado;
	private static int nClientes;
	private int clientesEnCola;
	private int pos;
	
	public SuperModerno(int nCjas, int nClientes){
		
		this.cajas = new Caja[nCjas];
		SuperModerno.nClientes = nClientes;
		
		
		for (int i = 0; i < cajas.length; i++) {
			
			this.cajas[i] = new Caja(i+1);
						
		}
	}
	
	public double getResultado(){
		
		return this.resultado;
	}
	
    //Se sincroniza todo el método ya que otro hilo podría que este en ejecutando
	//el metodo encolar podria obtener una lectura erronea
	public synchronized void cobrar(double dinero, Caja caja){
		
		caja.setOcupada(false); //La caja que en la que estaba el cliente queda libre
		this.resultado += dinero; //Se suma el dinero pagado por el usuario
		System.out.println("La caja "+caja.getNCaja()+" queda libre");
		notify(); //Le toca al siguiente
	}
	
	public synchronized void encolar(Cliente cliente){
		
		//Permitira saber si el cliente ha sido atendido
		boolean atendido = false; 
		
		//Mientras no este atendido
		while(!atendido){
		
			//Busca una caja libre
			for (pos = 0; pos < cajas.length; pos++) {
			
				//Si la encuentra
				if(!cajas[pos].isOcupada()){
					System.out.println("La caja "+cajas[pos].getNCaja()+" esta atendiendo al cliente "+cliente.getNcliente());
					cajas[pos].setOcupada(true); //La maraca como ocupada
					cliente.setCaja(cajas[pos]); //Le indica al cliente que caja le toca
					atendido = !atendido; //El cliente esta siendo atendido
					break; //Se que no se debe abusar de saltos incondicionales, pero en este caso creo que esta justificado, ya que si es atendido ¿para que seguir?
				}
			}
			
			//Si no habia una caja para este cliente le toca esperar
			if(!atendido){
				
				try {
					System.out.println("El cliente "+cliente.getNcliente()+" se pone a la cola");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			 		
		}
	}
		
}
