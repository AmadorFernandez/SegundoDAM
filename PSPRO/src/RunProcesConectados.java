import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class RunProcesConectados {

	public static void main(String[] args) {
		
		
		if(args.length <= 0){
			
			System.err.println("Falta el parametro");
			System.exit(-1);
			
			
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true); //Importante asignar los errores a la consola estandar
		
		
		try{
		Process proceso = pb.start();
		MostrarSalidaProceso(proceso);
		System.exit(0);
		
		}catch(IOException e){
			

			System.err.println("Error entrada salida");
			System.exit(-1);
			
		}
		

	}
	
	public static void MostrarSalidaProceso(Process pro){
		
		try {
			int retorno = pro.waitFor();
			System.out.println("La ejecucion devuelve: "+ retorno);
			
			InputStreamReader lector = new InputStreamReader(pro.getInputStream(), "UTF-8");
			
			BufferedReader br = new BufferedReader(lector);
			String linea;
			
			while((linea = br.readLine()) != null){
				
				System.out.println(linea);
				
				
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
