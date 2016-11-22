package practica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PaqueteInstalado {

	public static void main(String[] args) {
	
		ProcessBuilder dpkg;
		ProcessBuilder grep;
		Process dpkgProces = null;
		Process grepProces = null;
		BufferedReader lectorDpkg;
		BufferedReader lectorGrep;
		BufferedWriter escritorDpkg;
		String salidaDpkg = "";
		String salidaGrep = "";
		final String CODIFICACION = "UTF-8";
		int resultadoDpkg;
		int resultadoGrep;
		
		if(args.length < 1){
			
			System.err.println("No se han pasado argumentos");
			
		}else{
			
			dpkg = new ProcessBuilder("dpkg", "--get-selections");
			grep = new ProcessBuilder("grep", args[0]);
			
			
			try {
				
				dpkgProces = dpkg.start();
				grepProces = grep.start();
				
				lectorDpkg = new BufferedReader(new InputStreamReader(dpkgProces.getInputStream(), CODIFICACION));
				escritorDpkg = new BufferedWriter(new OutputStreamWriter(grepProces.getOutputStream(), CODIFICACION));
				
				while ((salidaDpkg = lectorDpkg.readLine()) != null) {
					
					escritorDpkg.write(salidaDpkg);
					escritorDpkg.newLine();
					
				}
				
				
				lectorDpkg.close();
				escritorDpkg.close();
				
				lectorGrep = new BufferedReader(new InputStreamReader(grepProces.getInputStream(), CODIFICACION));
				
				while ((salidaGrep = lectorGrep.readLine()) != null) {
					
					System.out.println(salidaGrep);
										
				}
				
				lectorGrep.close();
				
				resultadoDpkg = dpkgProces.waitFor();
				resultadoGrep = grepProces.waitFor();
				
				System.out.println("dpkg termino con un resultado de: "+resultadoDpkg);
				System.out.println("grep termino con un resultado de: "+resultadoGrep);
				
				
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		
		}

	}

}
