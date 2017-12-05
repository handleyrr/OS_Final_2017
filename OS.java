

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OS 
{
	public static void main(String[] args) throws IOException
	{
		System.out.println(myPCB);
	}
	public static ArrayList<PCB> Ready = new ArrayList<>(); 
		// to keep track of all the processes waiting to run 
	public static ArrayList<PCB> IO = new ArrayList<>(); 
		// to keep track of the processes waiting to I/O 
	public static String line; 
	public static int ID; 
	public static int arrival; 
	public static String instruction; 
	static PCB myPCB= new PCB(); 
	
	public static PCB CreateProcess(String file, String line, int ID, int arrival, String instruction) throws IOException
	{
		
		
		PCB myPCB = new PCB(); 
		
		BufferedReader read = new BufferedReader (new FileReader(file));
			//gets the file from the user 
		
		while ((line = read.readLine()) != null)
		{
			//TODO Fix this so the line increases 
			String[] words = line.split(" , " ); 
			ID = Integer.parseInt(words[0]); 
			arrival = Integer.parseInt(words[1]); 
			instruction = words[3];  
			int[] InstructionArray = new int[instruction.length()];
			char[] Instructions = instruction.toCharArray(); 
			for (int i=0; i<instruction.length(); i++)
			{
				InstructionArray[i] = Character.getNumericValue(Instructions[i]); 
			}
			
			System.out.println("The Id is " + ID);
			System.out.println("The Arrival number is " + arrival);
			System.out.println("The instruction is " + instruction);


		}
		return myPCB;
	}
}
