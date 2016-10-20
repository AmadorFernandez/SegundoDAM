package com.pspro;

import java.io.File;
import java.util.concurrent.Semaphore;

import javax.print.DocFlavor.STRING;

public class FirstClass {
	
	public static volatile int totalVocal = 0;
	public static int[] countVocals = new int[5];
	private static String pathApplication = "";
	public final static String FILE_NAME = "file_test.txt";
	private static char[][] vocals = new char[5][4];
	

	public static void main(String[] args) {
		
		extractApplicationPath();
		startUI();
		

	}
	
	private static void startUI() {
		
		FileIO fIo = new FileIO(pathApplication, FILE_NAME);
		String text = fIo.readFile();
		
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
	
	private static void msgForUser() {
		
		System.out.println("They found a total of "+ totalVocal + " vocal in the text read.");
		
			
	}

	// Initializes the array of vowels and the array that keeps track
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
		
		for (int i = 0; i < countVocals.length; i++) {
			
			countVocals[i] = 0;
		}
		
	}

	private static void startThreads(String text) {
		
		CountVocal[] counters = new CountVocal[5];
		String bolt = " ";
		
		
		for (int i = 0; i < counters.length; i++) {
			
			counters[i] = new CountVocal(text, vocals[i], bolt, i);
			counters[i].start();			
		}
		
		for (int i = 0; i < counters.length; i++) {
			
			try {
				counters[i].join();
			} catch (InterruptedException e) {
				
			}
		}
		
			
	}

	private static void extractApplicationPath(){
		
		File file = new File(".");
		pathApplication = file.getAbsolutePath();
		
	}

}
