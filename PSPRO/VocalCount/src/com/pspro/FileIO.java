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
					
	}
	

	public String readFile(){
		
		//The usual read
		String text = "";
		String line = "";
		File file = new File(this.path, this.fileName);
		BufferedReader bf = null;
		
		try {
			
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));			
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
			} catch (Exception e) {
				
				
			}
		}
				
		return text;
		
	}

}
