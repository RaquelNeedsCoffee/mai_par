import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class State {
	int numLines;
	int maxColumns;
	LinkedList<String>[] docks;
	LinkedList<String>[] ferry;
	HashSet<String> docksSet;
	HashSet<String> ferrySet;
	
	
/******************************************************************************************
 * CONSTRUCTOR
 ******************************************************************************************/
	
	@SuppressWarnings("unchecked")
	public State(int numLines, int maxColumns) {
		this.numLines = numLines;
		this.maxColumns = maxColumns;
		docks = new LinkedList[numLines];
		for (int i = 0; i < docks.length; i++) {
			docks[i] = new LinkedList<String>();
		}
		ferry = new LinkedList[numLines];
		for (int i = 0; i < ferry.length; i++) {
			ferry[i] = new LinkedList<String>();
		}	
	}
	
/******************************************************************************************
 * PRIVATE METHODS
 ******************************************************************************************/
	
	//Only used in initialization
	private boolean moveToDocks(String car, int line) {
		if (line < 0 || line >= numLines ) return false;
		if (docks[line].size() + 1 > maxColumns) return false;
		docks[line].addLast(car);
		return true;
	}
	
	private boolean initializationFirst(String car) {
		for (int i = 0; i < docks.length; i++) {
			if (docks[i].size() == 0) {
				if (moveToDocks(car,i)) return true;
			}
		}
		return false;
	}
	
	private  boolean initializationNextTo(String newCar, String lastCar) {
		for (int i = 0; i < docks.length; i++) {
			if (docks[i].getLast().equals(lastCar)) {
				if (docks[i].size() + 1 <= maxColumns) {
					docks[i].addLast(newCar);
					return true;
				}
			}
		}
		return false;
	}

/******************************************************************************************
 * USEFUL PUBLIC METHODS 
 ******************************************************************************************/

	public void initializeState(String[] steps) {
		//I have assumed that all 
		for (String step : steps) {
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
	
	public void printState() {
		System.out.println("(Leftmost positions are closer to the ferry)");
		System.out.println("Docks:");
		for (int i = 0; i < docks.length; i++) {
			String cars = "";
			for (int j = 0; j < docks[i].size(); j++) {
				cars += docks[i].get(j);
			}
			System.out.println("Line" + i + ": " + cars);
		}
	}
	
	
/******************************************************************************************
 * PLANNER OPERATORS 
 ******************************************************************************************/
	
	public boolean changeLine(String car, int line) {
		//TODO 
		return false;
	}
	
	//Only used in initialization
	public boolean moveToFerry(String car, int line) {
		//TODO 
		return false;
	}
	
	
}
