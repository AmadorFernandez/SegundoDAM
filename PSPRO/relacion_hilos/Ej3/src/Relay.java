import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Baton {
	
	//Object that relay in the race
	private volatile boolean haveBaton = true;
	
	public boolean batonState(){
		
		return this.haveBaton;
	}
	
	public void chageBatonState(boolean state){
		
		this.haveBaton = state;
	}
	
}

public class Relay {
	
	//Array of runners
	private static Runner[] runners = new Runner[4];
    

	//Entry point
	public static void main(String[] args) {
		
		startUI();
	}

	private static void startUI() {
	
		Baton baton = new Baton();
		ArrayList<String> runnersName = new ArrayList<String>();
		Random random = new Random();
		String nameRunner;
		int numberRandom;
		Scanner scanner = new Scanner(System.in);
		runnersName.add("Pepe");
		runnersName.add("Juanito");
		runnersName.add("Ernesto");
		runnersName.add("Juaquin");
		
		System.out.println("Enter any key to start the race.");
		scanner.nextLine();
		System.out.println();
		
		for (int i = 0; i < runners.length; i++) {
			
			//Gets as a random list broker and then removed to avoid repetition
			numberRandom = random.nextInt(runnersName.size());
			nameRunner = runnersName.get(numberRandom);
			runnersName.remove(numberRandom);
			runners[i] = new Runner(baton, nameRunner);
			runners[i].start();
		}
		
		try{
			
			//Tells the main thread to wait for the other threads before finishing.
			for (int i = 0; i < runners.length; i++) {
					runners[i].join();
				
				}
		
		
		System.out.println("End of race");
		
		}catch (InterruptedException e) {
			
			
		}
	}
		
	
	
}



class Runner extends Thread{
	
	private Baton baton; //The shared object as a baton
	private String nameRunner;
	
	public Runner(Baton b, String nameRunner){
		
		this.baton = b;	
		this.nameRunner = nameRunner;
	}
	
	//Atomic operations 
	public void runnig(){
		//Block the baton for the runnig
		synchronized (this.baton) {
			
			this.baton.chageBatonState(false);			
		}
		
	}
	
	public void endturn(){
		
		//Unblock baton
		synchronized (this.baton) {
			
			this.baton.chageBatonState(true);
		}
	}
	
	public boolean botonState(){
		
		//Ask for baton state
		synchronized (this.baton) {
			
		    return this.baton.batonState();
			
		}
	}
	//======================================//
	public void run(){
		
		try{
					
			    //Asked if the thread that has the relay it is leaking
				while (!botonState()) {
					Thread.sleep(1); //If you have not let go sleep
				}
				//If he can run it does and meets the requirements of the exercise
				runnig();
				System.out.println("[Runner "+this.nameRunner+"]"+"End of my time");
				Thread.sleep(2000);				
				endturn();				
					
		}catch(IllegalArgumentException | InterruptedException e){
			
			//????
		}
		
	}
	
	
}
