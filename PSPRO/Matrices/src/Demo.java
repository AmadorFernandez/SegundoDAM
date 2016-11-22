import java.util.Random;

public class Demo {
	
	private static int[][] a = new int[8][8];
	private static int[][] b = new int[8][8];
	
	public static void main(String[] args){
		
		
		inicializar();
		pintar();
		System.out.println();
		System.out.println("Resultado a por b de forma secuencial:");
		pintar(MultiplicadorSecuencial.multiplicador(a, b));
		
		
	}
	
	public static void inicializar(){
		
		Random random = new Random();
		
		for (int i = 0; i < a.length; i++) {
			
			for (int j = 0; j < a[i].length; j++) {
				
				a[i][j] = random.nextInt(100);
				b[i][j] = random.nextInt(100);
			}
		}
	} 
	
	public static void pintar(){
		
		System.out.println("Matriz a:");
		for (int i = 0; i < a.length; i++) {
			
			for (int j = 0; j < a[i].length; j++) {
				
				System.out.print(a[i][j]+" ");
			}
			
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Matriz b:");
		for (int i = 0; i < b.length; i++) {
			
			for (int j = 0; j < b[i].length; j++) {
				
				System.out.print(b[i][j]+" ");
			}
			
			System.out.println();
		}
	}
	
	public static void pintar(int[][] a){
		
		
		for (int i = 0; i < a.length; i++) {
			
			for (int j = 0; j < a[i].length; j++) {
				
				System.out.print(a[i][j]+" ");
			}
			
			System.out.println();
		}
	}

}
