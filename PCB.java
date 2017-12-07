class PCB {
	//parts of a process
	int ProcessID;
	int ArrivalTime;
	String State;
	int[] Code;
	//position in burst sequence
	int PositionOfNextInstructionToExecute;
	//priority integer
	int Priority;
	//variables for time calculation later
	long ProcessCreated;
	long ProcessFinished;
	long ProcessIOComplete;
	long Latency;
	long Response;

	public PCB (int processId, int arrivalTime, int[] code){
		this.ProcessID = processId;
		this.ArrivalTime = arrivalTime;
		this.Code = code;
		this.State = "New";
		this.PositionOfNextInstructionToExecute = 0;
		this.ProcessCreated = System.currentTimeMillis();
	}
	//helpful return methods (can add more later)
	public String getProcessState(){
		return this.State;
	}
	
	public void setProcessState(String state){
		this.State = state;
	}
	
	//next instruction
	public int getNextInstruction(){
		return this.PositionOfNextInstructionToExecute;
	}
	
	public int[] getInstruction(){
		return this.Code;
	}
	
	//go to next instruction
	public void advanceInstruction(){
		this.PositionOfNextInstructionToExecute++;
	}
	
	public int getPriority(){
		return this.Priority;
	}
	
	//TODO make more methods for acquiring data
}
