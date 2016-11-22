
public class MultiplicadorSecuencial {

	
	public static int[][] multiplicador(int[][] a, int[][] b){
		
		int[][] resultado = new int[a.length][a[0].length];
		
		
		for (int i = 0; i < resultado.length; i++) {
			
			for (int j = 0; j < resultado[i].length; j++) {
				
				resultado[i][j] = a[i][j] * b[i][j];
			}
		}
		
		return resultado;
		
	}
	
}
