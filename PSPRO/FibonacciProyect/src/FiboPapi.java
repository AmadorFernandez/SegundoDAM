import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

import javax.naming.ldap.StartTlsRequest;
import javax.sql.rowset.Joinable;
import javax.swing.text.Position;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.xml.stream.events.StartDocument;

public class FiboPapi {
	
	//Variable a travez que manipularan los tres hilos
	public static long[] sucesion;
	
	public static void main(String[] args) {
		
		  try{
			  
		    init();	
		    
		    
		  }catch(Exception e){
			  
		 
		  }
		   
		  System.out.println();
		  System.out.print("Fin del programa");
			  									
	}
	
	private static void mostrarSucesion(){
			
		for (int i = 0; i < sucesion.length; i++) {
			
			System.out.print(sucesion[i]+", ");			
		}
				
	}
	
	private static void init(){
		
		int n = 0; //Numero de sucesiones que queremos
		FiboNene1 operador1;
		FiboNene2 operador2;
		Scanner s = new Scanner(System.in);
		String op;
		String valor;
		Boolean valido = false;	
		
		//Comienza la interacción con el usuario
		System.out.println("Este ejercicio calcula la sucesion de Fibonacci y muestra él número de la posición indicada");
			
		//Pedimos el valor para N y validamos
		while (!valido) {			
			System.out.println();
			System.out.println("Introduzca el valor para N y pulse intro");
			System.out.println();
			valor = s.nextLine();
			valido = comprobarDato(valor);
			if(!valido){
				
				System.out.println("Eso no es un numero amigo");
				
			}else {
				
				n = Integer.parseInt(valor);
				sucesion = new long[n];
				valido = true;
				//Las tres primeras posiciones son sabidas
				sucesion[0] = 0;
				sucesion[1] = 1;				
				
				
			}
			
		}
		//Reiniciamos el comprobador
		valido = false;
				
		//Iniciamos el calculo de la sucesión		
		for (int i = 2; i < sucesion.length; i++) {
		
			operador1 = new FiboNene1(i);
			operador2 = new FiboNene2(i);
			operador1.start();
			
			try {
				operador1.join();
				operador2.start();
				operador2.join();
			} catch (InterruptedException e) {
				
			}
							
		}
		
		//Mostramos el número correspondiente a N
		System.out.println();
		System.out.println("El valor del número de Fibonacci en N es: "+sucesion[n-1]);
		
		//Preguntamos si desea ver la sucesión	
		while (!valido) {
			System.out.println();
			System.out.println("¿Desea ver la sucesión completa hasta N? [S/N] + intro");
			op = s.nextLine();
			
			switch (op){
				
				case "s":
				case "S":
					mostrarSucesion();
					valido = true;
					break;
				case "n":
				case "N":
					valido = true;
					break;
				default:
					System.out.println("Opción no conteplada por el sistema");
					break;
			
			}
			
		}
		
		
		
		
												
	}
	
	private static boolean comprobarDato(String dato){
		
		boolean resultado = false;
		int n;
		
		try {
			
			n = Integer.parseInt(dato);
			resultado = true;			
			
		} catch (NumberFormatException e) {
			
			resultado = false;
		}
		
		return resultado;
		
	}
	
}
	
	



//Esta se encarga de n - 1
class FiboNene1 implements Runnable{
	
	private int n;
	private Thread internal;
    
	public FiboNene1(int n){
	
		this.n = n;
		this.internal = new Thread(this);
	
		
	}
	
	public void start(){
		
		this.internal.start();	
	}
	
	public void join() throws InterruptedException{
		
		this.internal.join();
	}
	
	@Override
	public void run() {
		
					
		 FiboPapi.sucesion[n] = FiboPapi.sucesion[n - 1];
						       	
	}
	
}

//Esta de n - 2
class FiboNene2 implements Runnable{
			
	private int n;
	private Thread internal;
    
	public FiboNene2(int n){
	
		this.n = n;
		this.internal = new Thread(this);
		
		
	}
	
	public void start(){
		
		this.internal.start();	
	}
	
	public void join() throws InterruptedException{
		
		this.internal.join();
	}
	
	@Override
	public void run() {
		    
					
	   FiboPapi.sucesion[n] += FiboPapi.sucesion[n - 2];
		      			       	
	}
	
}
