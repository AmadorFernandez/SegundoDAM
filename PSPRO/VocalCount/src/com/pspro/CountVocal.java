package com.pspro;

import java.util.concurrent.Semaphore;

public class CountVocal extends Thread {
	
	private volatile char[] vocals;
	private volatile int count;
	private volatile String text;
	private volatile String bolt;
	private volatile int indexVocalAssigned;
	
	 
	
	public CountVocal(String text, char[] vocals, String bolt, int indexVocalAssigned){
		
		this.text = text;
		this.vocals = vocals;
		this.bolt = bolt;
		this.indexVocalAssigned = indexVocalAssigned;
		
		
	}
	
	private void sumVocal(){
		
		synchronized (bolt) {
			
			FirstClass.totalVocal++;
		}
				
		
		
				
	}
	
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
