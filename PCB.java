

public class PCB 
//data structure located in the kernel that contains the memory 
{
	int processId; 
	int arrivalOrder; 
	String state; 
	int[] Code; 
	int PositionOfNextInstruction; 
	long ProcessMade; 
	long ProcessDone; 
	
	public PCB(int processId, int arrivalOrder, int[] code)
	{
		this.processId = processId; 
		this.arrivalOrder = arrivalOrder; 
		this.Code = code; 
		this.state = "new"; 
		this.PositionOfNextInstruction= 0; 
		this.ProcessMade = System.currentTimeMillis(); 
	}
	
	public String getProcessState()
	{
		return this.state; 
	}
	
	public void setProcessState (String state)
	{
		this.state = state; 
	}
	
	public int getNextInstruction()
	{
		return this.PositionOfNextInstruction; 
	}
	
	public int[] getInatructions()
	{
		return this.Code; 
	}
	
	public void advanceInstruction()
	{
		this.PositionOfNextInstruction++; 
	}
}
