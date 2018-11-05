import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class State {
	int numLines;
	int maxColumns;
	String[] blocks;
	int numEmptyLines;
	ArrayList<StackElement> predicates;

	
	
/******************************************************************************************
 * CONSTRUCTOR
 ******************************************************************************************/
	
	public State(int numLines, int maxColumns, String[] blocks) {
		this.numLines = numLines;
		this.maxColumns = maxColumns;
		this.blocks = blocks;
		//We start at numEmptyLines = numLines. Every time we encounter a FirstDocks() in the initial state, we subtract 1.
		this.numEmptyLines = numLines;
		this.predicates = new ArrayList<StackElement>();
	}
	
/******************************************************************************************
 *  METHODS 
 ******************************************************************************************/
	
	public void initializeState(String[] steps) {
		//I have assumed that all 
		for (String step : steps) {
			Matcher matcherFirstDock = Pattern.compile("FirstDock\\((\\w+)\\)").matcher(step);
			if (matcherFirstDock.find()) {
				ArrayList<String> args = new ArrayList<String>();
				args.add(matcherFirstDock.group(1));
				StackElement e = new StackElement("condition","FirstDock",args);
				predicates.add(e);
				numEmptyLines = numEmptyLines - 1;
				continue;
			}
			
			Matcher matcherNextToDock = Pattern.compile("NextToDock\\((\\w+),(\\w+)\\)").matcher(step);
			if (matcherNextToDock.find()) {
				ArrayList<String> args = new ArrayList<String>();
				args.add(matcherNextToDock.group(1));
				args.add(matcherNextToDock.group(2));
				StackElement e = new StackElement("condition","NextToDock",args);
				predicates.add(e);
				continue;
			}
			
			Matcher matcherLastDock = Pattern.compile("LastDock\\((\\w+)\\)").matcher(step);
			if (matcherLastDock.find()) {
				ArrayList<String> args = new ArrayList<String>();
				args.add(matcherLastDock.group(1));
				StackElement e = new StackElement("condition","LastDock",args);
				predicates.add(e);
				continue;
			}
		}
	}

	public void applyOperator(StackElement e) {
		//Be aware of updates in NumEmptyLines()
		//FreeLine(X) is not maintained by an internal data structure, it is checked dynamically every time
		String name = e.getName();
		if (name.equals("BoardFirst1") || name.equals("BoardNextTo1") || name.equals("ChangeLine1")) {
			numEmptyLines += 1;
		}
		if (name.equals("ChangeToEmptyLine")) {
			numEmptyLines -= 1;
		}
		// Add and remove from "predicates" according to add and delete list
		for (StackElement c : e.getAddList()) predicates.add(c);
		for (StackElement c : e.getDeleteList()) predicates.remove(c);
		
	}
	
	//make it recursive. Try all combinations on non-instantiated variables
	public StackElement instantiateOperator(StackElement operator) {
		//Base case: If it is instantiated -> if satisfied return the operator, if not return null
		if (operator.isInstantiated()) {
			if (satisfied_preconditions(operator)) return operator;
			else {
				return null;
			}
		}
		ArrayList<String> args = operator.getArgs();
		//select first non-instantiated variable index (those with value null)
		//If we get here we are sure that there is at least one non instantiated variable
		int null_index = 0;
		for (int i = 0; i < args.size(); ++i) {
			if (args.get(i) == null) null_index = i;
		}
		//Try all combinations of blocks for that variable
		for (int j = 0; j < blocks.length; ++j) {
			args.set(null_index, blocks[j]);
			StackElement result = instantiateOperator(Operators.getOperator(operator.getName(), args));
			if (result != null) return result;
		}
		return null;
	}
	
	private boolean satisfied_preconditions(StackElement operator) {
		//all arguments must to be different from one another
		HashSet<String> argsSet = new HashSet<String>(operator.getArgs());
		if (argsSet.size() < operator.getArgs().size()) return false;
		//Then check if conditions are satisfied by the state object.
		ArrayList<StackElement> preconditions = operator.getPreconditions();
		for (int i = 0; i < preconditions.size(); ++i) {
			if (!satisfies(preconditions.get(i))) {
				return false; 
			}
		}
		return true;
	}
	
	public boolean satisfies(StackElement e) {
		String condition = e.getName();
		ArrayList<String> args = e.getArgs();
		if (condition.equals("FreeLine")) {
			return isFreeLine(args.get(0));
		}
		if (condition.equals("ExistsEmptyLine")) {
			return checkExistsEmptyLine();
		}
		return predicates.contains(e);
	}

	private boolean checkExistsEmptyLine() {
		return (numEmptyLines > 0);
	}

	private boolean isFreeLine(String c) {
		//I assume if FreeLine(c) is called, FirstDock(c) is true 
		//Inefficient
		String currentCar = c;
		int numCarsLine = 1;
		while (true) {
			String next = getDockCarAfter(currentCar);
			if (next == null) break;
			else {
				numCarsLine += 1;
				currentCar = next;
			}
		}
		return (numCarsLine < maxColumns);
	}
	
	
	//returns the block (car) that is after c if it exists. Returns null otherwise
	public String getDockCarAfter(String c) {
		for (StackElement e: predicates) {
			if (e.getName().equals("NextToDock") && e.getArgs().get(1).equals(c)) {
				return e.getArgs().get(0);
			}
		}
		return null;
	}
	
	public String getDockCarBefore(String c) {
		for (StackElement e: predicates) {
			if (e.getName().equals("NextToDock") && e.getArgs().get(0).equals(c)) {
				return e.getArgs().get(1);
			}
		}
		return null;
	}

	public int getPositionInLine(String x) {
		if (satisfies(Conditions.FirstDock(x))) return 1;
		int pos = 1;
		String carInFront = getDockCarBefore(x);
		while(carInFront != null) {
			pos += 1;
			carInFront = getDockCarBefore(carInFront);
		}
		return pos;
	}



}

