package com.pspro;



public class CountVocal implements Runnable{
	
	private volatile char[] vocals;
	private int count;
	private int indexVocalAssigned;
	private String text;	
	private Thread thread;
	
	 
	
	public CountVocal(String text, char[] vocals,int indexVocalAssigned){
		
		this.text = text; //Text to read
		this.vocals = vocals; //Vocals must identify and count.
		this.indexVocalAssigned = indexVocalAssigned; //The index corresponding to the position of his vocal in the individual count.
		this.thread = new Thread(this);
		
		
		
	}
	
	//The usual
	public void start() {
		
		this.thread.start();
	}
	
	public void join() throws InterruptedException{
		
		this.thread.join();
	}
	//=====================================================//
	//Incrementes the two count
	private void sumVocal(){
		
    	SafeCount.totalVocals.incrementAndGet();
    	SafeCount.countVocals[indexVocalAssigned]++;
					
	}
	
	@Override
	public void run(){
		
		char vocal;
		//Extract the vocal one to one
		 for (int i = 0; i < text.length(); i++) {
			 
			 vocal = text.charAt(i);
			 //Compares the extracted voice to be assigned and increases coincidence counters.
			 for (int j = 0; j < vocals.length; j++) {
				
				 if(vocal == vocals[j]){
					 
					 sumVocal();
				 }
			}
			
		}
		
	}

}
