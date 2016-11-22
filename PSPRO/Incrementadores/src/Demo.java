
public class Demo {

	public static void main(String[] args) {
		
		
		Incrementador[] incrementadors = new Incrementador[6];
		
		for (int i = 0; i < incrementadors.length; i++) {
			
			incrementadors[i] = new Incrementador();
			incrementadors[i].start();
		}
		
		for (int i = 0; i < incrementadors.length; i++) {
			
			try {
				incrementadors[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Resultado final: "+Global.cont);

	}

}
