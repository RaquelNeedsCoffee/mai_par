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
		docksSet = new HashSet<String>();
		ferrySet = new HashSet<String>();
	}
	
/******************************************************************************************
 * PRIVATE METHODS (At the moment only used for initialization)
 ******************************************************************************************/
	
	private boolean validDocksMove(String car, int line) {
		if (line < 0 || line >= numLines ) return false;
		if (docks[line].size() + 1 > maxColumns) return false;
		return true;
	}
	
	//TODO: ASK IF FERRY LINES ALSO HAVE A MAXCOLUMN RESTRICRION.
	//At the moment, I assume they do
	private boolean validFerryMove(String car, int line) {
		if (line < 0 || line >= numLines ) return false;
		if (ferry[line].size() + 1 > maxColumns) return false;
		return true;
	}
	
	private boolean initializationAtDock(String car) {
		if (ferrySet.contains(car)) return false;
		else {
			docksSet.add(car);
			return true;
		}
	}
	
	private boolean initializationAtFerry(String car) {
		if (docksSet.contains(car)) return false;
		else {
			ferrySet.add(car);
			return true;
		}
	}
	
	private boolean initializationFirst(String car) {
		if (docksSet.contains(car)) {
			for (int i = 0; i < docks.length; i++) {
				if (docks[i].size() == 0) {
					if (validDocksMove(car,i)) {
						docks[i].addLast(car);
						return true;
					}
				}
			}
			return false;
		}
		else if (ferrySet.contains(car)) {
			for (int i = 0; i < ferry.length; i++) {
				if (ferry[i].size() == 0) {
					if (validFerryMove(car,i)) {
						ferry[i].addLast(car);
						return true;
					}
				}
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	private  boolean initializationNextTo(String newCar, String lastCar) {
		if (docksSet.contains(lastCar)) {
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
		else if (ferrySet.contains(lastCar)) {
			for (int i = 0; i < ferry.length; i++) {
				if (ferry[i].getLast().equals(lastCar)) {
					if (ferry[i].size() + 1 <= maxColumns) {
						ferry[i].addLast(newCar);
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}
	
	
	
/******************************************************************************************
 * PREDICATE
 ******************************************************************************************/
	
	public boolean atDock(String car) {
		//TODO 
		return false;
	}
	
	public boolean atFerry(String car) {
		//TODO 
		return false;
	}
	
	public boolean first(String car) {
		//TODO 
		return false;
	}
	
	public boolean nextTo(String car) {
		//TODO 
		return false;
	}
	
	public boolean fullLine(String car) {
		//TODO 
		return false;
	}
	
	
/******************************************************************************************
 * OPERATORS 
 ******************************************************************************************/
	
	public boolean changeLine(String car) {
		//TODO 
		return false;
	}
	
	public boolean moveToFerry(String car) {
		//TODO 
		return false;
	}


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
	
	public void printState() {
		System.out.println("(Leftmost positions are the first in the lines)");
		System.out.println("Docks:");
		for (int i = 0; i < docks.length; i++) {
			String cars = "";
			for (int j = 0; j < docks[i].size(); j++) {
				cars += docks[i].get(j);
			}
			System.out.println("\tLine" + i + ": " + cars);
		}
		System.out.println();
		System.out.println("Ferry:");
		for (int i = 0; i < ferry.length; i++) {
			String cars = "";
			for (int j = 0; j < ferry[i].size(); j++) {
				cars += ferry[i].get(j);
			}
			System.out.println("\tLine" + i + ": " + cars);
		}
	}
	
}

