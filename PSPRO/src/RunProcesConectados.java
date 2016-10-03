import java.io.IOException;

public class RunProcesConectados {

	public static void main(String[] args) {
		
		
		if(args.length <= 0){
			
			System.err.println("Falta el parametro");
			System.exit(-1);
			
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		
		
		try{
		Process proceso = pb.start();
		MostrarSalidaProceso(proceso);
		System.exit(0);
		
		}catch(IOException e){
			
			
			
		}
		

	}
	
	public static void MostrarSalidaProceso(Process pro){
		
		try {
			int retorno = pro.waitFor();
			System.out.println("La ejecucion devuelve: "+ retorno);
			
			InputStreamReader lector = new InputStreamReader(pro.getInputStream());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
