package com.pspro;

import java.io.File;
import java.util.concurrent.Semaphore;

import javax.print.DocFlavor.STRING;

public class FirstClass {
	
	
	public final static char[][] vocals = new char[5][4];
	private static String pathApplication = "";
	public final static String FILE_NAME = "file_test.txt";
	
	//Entry point
	public static void main(String[] args) {
		
		
		extractApplicationPath();
		startUI();
		
	}
	
	//Starts interface
	private static void startUI() {
		
		FileIO fIo = new FileIO(pathApplication, FILE_NAME);
		String text = fIo.readFile(); //Read file
		
		//Possible errors are controlled and have not acted
		switch (text) {
		case FileIO.FILE_NOT_FOUND_MSG:
			System.out.println(FileIO.FILE_NOT_FOUND_MSG);			
			break;
		case FileIO.IO_ERROR_MSG:
			System.out.println(FileIO.FILE_NOT_FOUND_MSG);
			break;
		default:
			initArrays();
			startThreads(text);
			msgForUser();
			break;
		}
		
		
	}
	//The informacion for User (YOU)
	private static void msgForUser() {
		
		System.out.println("They found a total of "+ SafeCount.totalVocals + " vocal in the text read.");
		
		for (int i = 0; i < SafeCount.countVocals.length; i++) {
			
			System.out.print("There are: "+SafeCount.countVocals[i]+" [");
	
			for (int j = 0; j < vocals[i].length ; j++) {
				
				System.out.print(vocals[i][j]+" ");
				
			}
			System.out.print("]");
			System.out.println();
		}
		
			
	}
	// Initializes the array of vocals and the array that keeps track
	private static void initArrays() {
		
		vocals[0][0] = 'a';
		vocals[0][1] = 'A';
		vocals[0][2] = 'á';
		vocals[0][3] = 'Á';
		vocals[1][0] = 'e';
		vocals[1][1] = 'E';
		vocals[1][2] = 'é';
		vocals[1][3] = 'É';
		vocals[2][0] = 'i';
		vocals[2][1] = 'I';
		vocals[2][2] = 'í';
		vocals[2][3] = 'Í';
		vocals[3][0] = 'o';
		vocals[3][1] = 'ó';
		vocals[3][2] = 'O';
		vocals[3][3] = 'Ó';
		vocals[4][0] = 'u';
		vocals[4][1] = 'ú';
		vocals[4][2] = 'ú';
		vocals[4][3] = 'Ú';
		
		for (int i = 0; i < SafeCount.countVocals.length; i++) {
			
		  SafeCount.countVocals[i] = 0;
		}
		
	}

	//Initializes and starts the threads
	private static void startThreads(String text) {
		
		CountVocal[] counters = new CountVocal[5];
	
				
		for (int i = 0; i < counters.length; i++) {
			//The text is passed, the array must have vocal and the corresponding index where it needs
			//to increase the individual account of their assigned vocals.
			counters[i] = new CountVocal(text, vocals[i], i);
			counters[i].start();			
		}
		
		//The join is executed after starting the 5 wires so that there is parallelism.
		for (int i = 0; i < counters.length; i++) {
			
			try {
				counters[i].join();
			} catch (InterruptedException e) {
				
			}
		}
		
			
	}

	//Get the application directory
	private static void extractApplicationPath(){
		
		File file = new File(".");
		pathApplication = file.getAbsolutePath();
		
	}

}
