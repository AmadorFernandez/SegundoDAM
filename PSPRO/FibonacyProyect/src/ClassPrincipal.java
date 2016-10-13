
public class ClassPrincipal {

	int x;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calculadora cal = new Calculadora(8);
		cal.start();
		

	}
	
	class Calculadora extends Thread{
		
		int n;
		
		public Calculadora(int n) {
			
			this.n = n;
		}
		
		@Override
		public void run() {
			
			int suma = 0;
			int fibo1 = 1;
			int fibo2 = 1;
			
			
		
			for(int i = 0; i <= n; i++){
				
				System.out.print(fibo2+", ");
				fibo2 = fibo1 + fibo2;
				fibo1 = fibo2 - fibo1;	
								
			}
			
		}
		
		
	}


}

