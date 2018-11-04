import java.util.ArrayList;


public class CarTransportPlanning {

	public static void main(String[] args) {
		//2 arguments are required: inputFile and outputFile
		//TODO: At the moment only inputFile is implemented
		if (args.length != 2) {
			System.out.println("ERROR: Incorrect number of arguments.\nUsage: java CarTransportPlanning inputFilename outputFilename");
			return;
		}
		/**
		ArrayList<String> a = new ArrayList<String>();
		a.add("ei");
		a.add(null);
		a.add("au");
		System.out.println(String.join(",", a));
		int b = 4;
		System.out.println(b == 4);
		**/
		//Get initial and goal states from input file
		State state = PlannerIOHelper.getInitialState(args[0]);
		GoalStack goalStack = PlannerIOHelper.getInitialGoalStack(args[0]);
		ArrayList<StackElement> plan = new ArrayList<StackElement>();
		while(!goalStack.empty()) {
			//Prevent infinite recursion
			if (goalStack.size() > 1000) {
				System.out.println("Possible goal stack overflow (more than 1000 elements). Execution stopped.");
				break;
			}
			StackElement e = goalStack.pop();
			if (e.isOperator()) {
				state.applyOperator(e);
				System.out.println("[STACK] APPLY OPERATOR TO STATE: Operator " + e.toString());
				plan.add(e);
			}
			else if (e.isCondition()) {
				//If state satisfies the condition, we don't do anything
				//If it doesn't, we look for an operator that has the condition in the AddList
				System.out.println("[STACK] CHECK CONDITION: " + e.toString());
				if (!state.satisfies(e)) {
					System.out.println("Condition NOT satisfied.");
					StackElement operator = getOperatorWithConditionInAddList(state,e);
					goalStack.push(operator);
					ArrayList<StackElement> preconditions = operator.getPreconditions();
					//preconditions should be already correctly ordered (heuristic)
					for (int i = 0; i < preconditions.size(); i++) {
						goalStack.push(preconditions.get(i));
					}
				}
				else {
					System.out.println("Condition satisfied.");
				}
			}
			else {
				System.out.println(e.getType());
				throw new RuntimeException("Stack element type not recognized.");
			}
		}
		System.out.println("Algorithm has finished! Found plan with " + plan.size() + " operators.");
		PlannerIOHelper.outputFinalPlan(plan,args[1]);
		
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
		
		/**
		else if (name.equals("LastFerry")) {
			//TODO: Is this necessary?
			System.out.println("Last Ferry on top of the stack");
		}
		else if (name.equals("FirstDock")) {
		}
		**/
		//System.out.println("PUSH OPERATOR TO STACK: " + operator.toString());
		//return operator;
		
		//at the end instantiate operator
		System.out.println("SELECTED OPERATOR: " + operator.toString());
		StackElement instantiatedOperator = state.instantiateOperator(operator);
		System.out.println("PUSH INSTANTIATED OPERATOR: " + instantiatedOperator.toString());
		//System.out.println(instantiatedOperator == null);
		return instantiatedOperator;
	}

	
}
