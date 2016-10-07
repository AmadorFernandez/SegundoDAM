import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



/**
 * 
 *  @author Amador Fernández González
 *  @version 0.00000000001 :)
 *  
 *  
	This class runs two different processes. The first is the ls command with the -la parameter. 
	The second is the grep command.

	First, it is run the ls command to the end, passing the data resulting from its output to
	the second process.
	
	Then by these data, the grep command makes filtering the information obtained based
 	on a parameter that previously has been given by the argument of the method main.

	Finally, the output of the second process is shown by the standard console.

	On the command line are to be passed two parameters. The first refers to
 	the path leading to the file and the second is the character you want to be filtered.
 *  
 *  
 *  */
public class ProcessUnited {
	
	
	public static final String[] FIRSTCOMAND = new String[] {"ls", "-la"};
	public static final String SECONDCOMAND = "grep";
	public static final String INCORRET_NUMBER_DATA = "folder path followed by the filter string is requested";
	public static final String MSG_ERROR_IO = "The following error occurred I / O:";
	public static final String MSG_ERROR_PROCESS = "The following error occurred completion of the program: ";
	public static final String MSG_RESUTL_LS = "ls process ended with the following result: ";
	public static final String MSG_RESUTL_GREP = "grep process ended with the following result: ";
	public static final String CODIFY = "UTF-8";
	
	
	//Entry point
	public static void main(String[] args) {
		
		//Verify that the number data is right
		if(args.length == 2){
			
			start(args);
			
			
		}else{
			
			System.out.println(INCORRET_NUMBER_DATA);
		}
		

	}


	private static void start(String[] args) {
		
		
		String resultA = "";
		String resultB = "";
		Process processA = null;
		Process processB = null;
		ProcessBuilder procesOne = null;
		ProcessBuilder procesTwo = null;
		BufferedReader exitOne = null;
		BufferedWriter entryTwo = null;
		BufferedReader exitTwo = null;
		int stateEndProcesA = 0;
		int stateEndProcesB = 0;
		
		
		// Allocate memory to processes
		procesOne = new ProcessBuilder(FIRSTCOMAND[0], FIRSTCOMAND[1], args[0]);
		procesTwo = new ProcessBuilder(SECONDCOMAND, args[1]);
		
		
		
	
		
		try {
			
			// the processes are started (The second you wait to enter data first) and
			// they are link flows reading Input Output
			
			processA = procesOne.start();
			processB = procesTwo.start();
			exitOne = new BufferedReader(new InputStreamReader(processA.getInputStream(), CODIFY));
			entryTwo = new BufferedWriter(new OutputStreamWriter(processB.getOutputStream(), CODIFY));
			
			
			
			
			
			//	Passes the data obtained the input of the second process
			
			while ((resultA = exitOne.readLine()) != null) {
				
				entryTwo.write(resultA);
				entryTwo.newLine();
				
			}
			
			//Close Stream
			exitOne.close();
			entryTwo.close();
			
			//Obtain the exit of processB
			exitTwo = new BufferedReader(new InputStreamReader(processB.getInputStream(), CODIFY));
			
			//Pass the obtained data to The console
			
			while ((resultB = exitTwo.readLine()) != null) {
				
				System.out.println(resultB);
						
			}
			
			System.out.println();
			
			//Close Stream
			exitTwo.close();
			
			
			//Obtain the end codes with which they have completed
			
			stateEndProcesA = processA.waitFor();
			stateEndProcesB = processB.waitFor();
			
			System.out.println(MSG_RESUTL_LS + stateEndProcesA);
			System.out.println(MSG_RESUTL_GREP + stateEndProcesB);
			
			
			
			
			
		} catch (IOException e) {
			
			System.err.println(MSG_ERROR_IO + e.getMessage());
			
		} catch (InterruptedException e) {
			
			System.err.println(MSG_ERROR_PROCESS + e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
	}

}
