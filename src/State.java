import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class State {
	int numLines;
	int maxColumns;
	HashSet<StackElement> predicates;

	
	
/******************************************************************************************
 * CONSTRUCTOR
 ******************************************************************************************/
	
	@SuppressWarnings("unchecked")
	public State(int numLines, int maxColumns) {
		this.numLines = numLines;
		this.maxColumns = maxColumns;
		this.predicates = new HashSet<StackElement>();
	}
	
/******************************************************************************************
 * PRIVATE METHODS (At the moment only used for initialization)
 ******************************************************************************************/
	

/******************************************************************************************
 * USEFUL PUBLIC METHODS 
 ******************************************************************************************/

	public void initializeState(String[] steps) {
		//I have assumed that all 
		for (String step : steps) {
			Matcher matcherAtDock = Pattern.compile("AtDock\\((\\w+)\\)").matcher(step);
			if (matcherAtDock.find()) {
				if (!initializationAtDock(matcherAtDock.group(1))) {
					throw new RuntimeException("Wrong initialization of state");
				}
				continue;
			}
			Matcher matcherAtFerry = Pattern.compile("AtFerry\\((\\w+)\\)").matcher(step);
			if (matcherAtFerry.find()) {
				if (!initializationAtFerry(matcherAtFerry.group(1))) {
					throw new RuntimeException("Wrong initialization of state");
				}
				continue;
			}
			Matcher matcherFirst = Pattern.compile("First\\((\\w+)\\)").matcher(step);
			if (matcherFirst.find()) {
				if (!initializationFirst(matcherFirst.group(1))) {
					throw new RuntimeException("Wrong initialization of state");
				}
				continue;
			}
			Matcher matcherNextTo = Pattern.compile("NextTo\\((\\w+),(\\w+)\\)").matcher(step);
			if (matcherNextTo.find()) {
				if (!initializationNextTo(matcherNextTo.group(1),matcherNextTo.group(2))) {
					throw new RuntimeException("Wrong initialization of state");
				}
				continue;
			}
		}
	}

	public void applyOperator(StackElement e) {
		// TODO Auto-generated method stub
		for (StackElement c : e.getAddList()) {
			predicates.add(c);
		}
		
		for (StackElement c : e.getDeleteList()) {
			predicates.remove(c);
		}
		
	}

	public boolean satisfies(StackElement e) {
		String condition = e.getName();
		ArrayList<String> args = e.getArgs();
		
		return false;
	}
	
}

