
public class CarTransportPlanning {

	public static void main(String[] args) {
		//2 arguments are required: inputFile and outputFile
		//TODO: At the moment only inputFile is implemented
		if (args.length != 1) {
			System.out.println("ERROR: Incorrect number of arguments.\nUsage: java CarTransportPlanning inputFilename outputFilename");
			return;
		}
		//Get initial and goal states from input file
		State[] inputStates = PlannerIOHelper.parseInputFile(args[0]);
		State initialState = inputStates[0];
		State goalState = inputStates[1];
		
		//Print both states in console
		System.out.println("Initial State:");
		initialState.printState();
		System.out.println("Goal State:");
		goalState.printState();
	}
	
}
