import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class OS
{
	public CPU cpu; 
	public static boolean isCPUAvailable = true; 
	//initializing the ready and waiting queues
	public static ArrayList<PCB> Ready = new ArrayList<PCB>();
	public static ArrayList<PCB>Waiting = new ArrayList<PCB>(); 
	
	
	//creates a PCB for each line read 
	//takes each line
	//parses the data
	//and returns it to the PCB class
	public static PCB CreateProcess(String file) throws NumberFormatException, IOException
	{
		PCB myPCB = null; 
		String line;
		int ID; 
		int arrival; 
		
		BufferedReader read = new BufferedReader(new FileReader(file));	
			//reads the file
		
		while ((line = read.readLine()) !=null)
				//while each ine is read, the document will be inserted into an
				// array each time the .csv shows a comma
				// the data is then parsed into variables
				// the Instructions come in a sinlge line, so 
				// in oder to get the instruction sequence, we have to break
				// the instruction line apart
				
		{
			String[] words = line.split(","); 
			ID = Integer.parseInt(words[0]); 
			arrival = Integer.parseInt(words[1]); 
			String InstructionLine = words[2]; 
			
			int[] instructionArray = new int[InstructionLine.length()]; 
			char  [] instructions = InstructionLine.toCharArray(); 
			for (int i=0; i<InstructionLine.length(); i++)
			{
				instructionArray[i] = Character.getNumericValue(instructions[i]); 
			}
			myPCB = new PCB (ID, arrival,instructionArray ); 
				//sending the new PCB into the PCB class
			Ready.add(myPCB); 
				//adds the new PCB into the ready_queue
		}
		return myPCB; 
	}

	public static void FCFS(PCB process)
	{
		int timeslice = 99999; 
		boolean done = false; 
		boolean firstIOcheck = true; 
		CPU cpu = new CPU(timeslice); 
		
		
		while(!done)
		{
			String state = "New"; 
			switch (state)
			{
			case "New": 
				//process being created
				System.out.println("Creating the Process now");
				state = "Ready"	; 
				process.setProcessState("Ready");
				
			case "Ready" : 
				//the process is ready to be executed
				System.out.println("the Process is Ready to be executed");
				if (isCPUAvailable == true)
				{
					state= "Running";
					process.setProcessState("Running"); 
				}
				else
					System.out.println("The CPU is Busy. Please Wiat. ");
				
			case "Running":
				//the process is being executed
				System.out.println("Executing the Process Now..."); 
				isCPUAvailable = false; 
				for (int i=0; i<process.getInstruction()[process.getNextInstruction()]; i++);
				{
					CPU.BubbleSort();
				}
				//add the process to the waiting queue and 
				//remove it from the ready queue
				Waiting.add(Ready.get(0)); 
				popShift(Ready); 
				if (process.getNextInstruction() == process.getInstruction().length)
				{
					Waiting.remove(0); 
					state = "Terminated"; 
					process.setProcessState("Terminated");
				}
				state = "Waiting";
				process.setProcessState("Waiting");
				process.advanceInstruction();				
				
			case "Waiting": 
				//the process is waiting on the IO
				System.out.println("Waiting on the IO");
				for (int i =0; i<process.getInstruction()[process.getNextInstruction()]; i++)
				{
					IO.BubbleSort();
				}
				Ready.add(Waiting.get(0)); 
				popShift(Waiting); 
				if(firstIOcheck== true)
				{
					process.setProcessIOComplete();
					firstIOcheck = false; 
				}
				state = "Running"; 
				process.setProcessState("Running");
				process.advanceInstruction();
				
			case "Terminated" : 
				//process finished executing 
				process.setProcessFinished();
				Ready.remove(0);
				if (Ready==null)
				{
					done = true; 
				}
				System.out.println("Finished Executing");
				state = "ready"; 
				process.setProcessState("Ready");
				break; 
			}
		}
		System.out.println("All processes have finshed executing");
	}

	private static void popShift(ArrayList<PCB> arrayList) 
	{
		arrayList.remove(0); 
		int times = arrayList.size(); 
		for(int i = 1; i < times; i++)
		{
			arrayList.add(i - 1, arrayList.get(i));
		}
	}
	
	
	
}