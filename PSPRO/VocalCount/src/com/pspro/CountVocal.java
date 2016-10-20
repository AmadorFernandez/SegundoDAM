package com.pspro;



public class CountVocal implements Runnable{
	
	private volatile char[] vocals;
	private volatile int count;
	private volatile String text;
	private volatile String bolt;
	private volatile int indexVocalAssigned;
	private Thread thread;
	
	 
	
	public CountVocal(String text, char[] vocals, String bolt, int indexVocalAssigned){
		
		this.text = text;
		this.vocals = vocals;
		this.bolt = bolt;
		this.indexVocalAssigned = indexVocalAssigned;
		this.thread = new Thread(this);
		
		
	}
	
	public void start() {
		
		this.thread.start();
	}
	
	public void join() throws InterruptedException{
		
		this.thread.join();
	}
	
	private void sumVocal(){
		
		synchronized (bolt) {
			
			FirstClass.totalVocal++;
		}
				
		
		
				
	}
	
	@Override
	public void run(){
		
		char vocal;
				
		 for (int i = 0; i < text.length(); i++) {
			 
			 vocal = text.charAt(i);
			 
			 for (int j = 0; j < vocals.length; j++) {
				
				 if(vocal == vocals[j]){
					 
					 sumVocal();
				 }
			}
			
		}
		
	}

}
