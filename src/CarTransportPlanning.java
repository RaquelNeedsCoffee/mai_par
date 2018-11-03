import java.util.ArrayList;


public class CarTransportPlanning {

	public static void main(String[] args) {
		//2 arguments are required: inputFile and outputFile
		//TODO: At the moment only inputFile is implemented
		if (args.length != 1) {
			System.out.println("ERROR: Incorrect number of arguments.\nUsage: java CarTransportPlanning inputFilename outputFilename");
			return;
		}
		ArrayList<String> a = new ArrayList<String>();
		a.add(null);
		a.add("hola");
		a.add(null);
		System.out.println(a.get(5));
		//Get initial and goal states from input file
		State state = PlannerIOHelper.getInitialState(args[0]);
		GoalStack goalStack = PlannerIOHelper.getInitialGoalStack(args[0]);
		
		while(!goalStack.empty()) {
			StackElement e = goalStack.pop();
			if (e.isOperator()) {
				state.applyOperator(e);
			}
			else if (e.isCondition()) {
				//If state satisfies the condition, we don't do anything
				//If it doesn't, we look for an operator that has the condition in the AddList
				if (!state.satisfies(e)) {
					StackElement operator = getOperatorWithConditionInAddList(state,e);
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

	
	private static StackElement getOperatorWithConditionInAddList(State state,StackElement condition) {
		StackElement operator;
		//example
		if (condition.getName().equals("FirstFerry")) {
			String x = condition.getArgs().get(0);
			operator = Operators.BoardFirst1(x);
		}
		// provisional
		else {
			String x = condition.getArgs().get(0);
			operator = Operators.BoardFirst1(x);
		}
		/**************/
		String name = condition.getName();
		//There is always one argument at least and two arguments at most
		String x = condition.getArgs().get(0);
		String y = null;
		if (condition.getArgs().size() > 1) {
			y = condition.getArgs().get(1);
		}
		//case FirstFerry(X)
		if (name.equals("FirstFerry")) {
			if (state.satisfies(Conditions.LastDock(x))) {
				operator = Operators.BoardFirst1(x);
			}
			else {
				operator = Operators.BoardFirst2(x,null);
			}
		}
		//case NextToFerry
		else if (name.equals("NextToFerry")) {
			if (state.satisfies(Conditions.LastDock(x))) {
				operator = Operators.BoardNextTo1(x,y);
			}
			else {
				operator = Operators.BoardNextTo2(x,null,y);
			}
		}
		//case LastFerry(X)
		//Will it be necessary to select an operator when LastFerry(X) is at the top?
		else if (name.equals("LastFerry")) {
			//TODO: Is this necessary?
			System.out.println("Last Ferry on top of the stack");
		}
		else if (name.equals("FirstDock")) {
			
		}
		
		
		

			/**************/
		//at the end instantiate operator
		StackElement instantiatedOperator = state.instantiateOperator(operator);
		
		return instantiatedOperator;
	}

	
}
