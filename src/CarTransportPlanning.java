import java.util.ArrayList;


public class CarTransportPlanning {

	public static void main(String[] args) {
		//2 arguments are required: inputFile and outputFile
		//TODO: At the moment only inputFile is implemented
		if (args.length != 1) {
			System.out.println("ERROR: Incorrect number of arguments.\nUsage: java CarTransportPlanning inputFilename outputFilename");
			return;
		}
		//Get initial and goal states from input file
		ArrayList<String>[] r = PlannerIOHelper.parseInputFile(args[0]);
		ArrayList<String> initial_predicates = r[0];
		
		ArrayList<String> final_predicates = r[1];

		
		GoalStack goalStack = new GoalStack();
		while(!goalStack.empty()) {
			StackElement e = goalStack.pop();
			if (e.isOperator()) {
				state.applyOperator(e);
			}
			else if (e.isCondition()) {
				//If state satisfies the condition, we don't do anything
				//If it doesn't, we look for an operator that has the condition in the AddList
				if (!state.satisfies(e)) {
					StackElement operator = getOperatorWithConditionInAddList(e);
					ArrayList<StackElement> preconditions = operator.getPreconditions();
					//preconditions should be already correctly ordered (heuristic)
					for (int i = 0; i < preconditions.size(); i++) {
						goalStack.push(preconditions.get(i));
					}
				}
			}
			else {
				throw new RuntimeException("Stack element type not recognized");
			}
		}
	}

	
	private static StackElement getOperatorWithConditionInAddList(StackElement e) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
