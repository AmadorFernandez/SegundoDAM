package com.pspro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class FileIO {
	
	private String path;
	private String fileName;
	private static char[][] vocals = new char[5][4];
	public static final String FILE_NOT_FOUND_MSG = "The file could not be found";
	public static final String IO_ERROR_MSG = "An error occurred while reading the file";
	
	
	public FileIO(String path, String fileName){

		
		this.path = path;
		this.fileName = fileName;
		initArray();
		writeText();
				
	}
	
	
	private void writeText(){
		
		Random rnd = new Random();
		File file = new File(path, fileName);
		BufferedWriter bWriter = null;
	    int f;
	    int c;
	    
	    try{
		
	    	bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		
	    	for (int i = 0; i < 10000 ; i++) {
			
	    		f = rnd.nextInt(5);
	    		c = rnd.nextInt(4);
	    		bWriter.write(vocals[f][c]);
			
	    	}
		
	    }catch(FileNotFoundException e){}
	    catch (IOException e) {
			
		}finally{
			
			try {
				bWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void initArray() {
		
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
		
	}

	public String readFile(){
		
		String text = "";
		String line = "";
		File file = new File(this.path, this.fileName);
		BufferedReader bf = null;
		
		try {
			
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			while ((line = bf.readLine()) != null) {
				
				text += line;
				text += "\r\n";
			
			}
			
		} catch (FileNotFoundException e) {
			
			text = FILE_NOT_FOUND_MSG;
			
		} catch (IOException e) {
			
			text = IO_ERROR_MSG;
			
		}finally{
			
			try {
				bf.close();
			} catch (IOException e) {
				
				
			}
		}
				
		return text;
		
	}

}