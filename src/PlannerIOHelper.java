import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlannerIOHelper {

	public static State getInitialState(String inputFilename) {
		State state = null;
		//Create Scanner object in order to read the contents of inputFilename
		Scanner s = null;
		try {
			s = new Scanner(new File(inputFilename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Create a list (ArrayList<String>) of containing the lines of the input file.
		ArrayList<String> inputList = new ArrayList<String>();
		while (s.hasNext()){
		    inputList.add(s.next());
		}
		s.close();
		//Get all the necessary information to create a State object (the initial state)
		//numLines
		int numLines;
		Matcher matcherNumLines = Pattern.compile("NumLines=(\\d+);").matcher(inputList.get(0));
		if (matcherNumLines.find()) {
			numLines = Integer.parseInt(matcherNumLines.group(1));
		}
		else {
			throw new RuntimeException("Incorrect input file. NumLines not found");
		}
		//MaxColumns
		int maxColumns;
		Matcher matcherMaxColumns = Pattern.compile("MaxColumns=(\\d+);").matcher(inputList.get(1));
		if (matcherMaxColumns.find()) {
			maxColumns = Integer.parseInt(matcherMaxColumns.group(1));
		}
		else {
			throw new RuntimeException("Incorrect input file. MaxColumns not found");
		}
		//Blocks
		//TODO: Question: can this input line be ignored?
		
		//Initial State
		State initialState = new State(numLines,maxColumns);
		Matcher matcherInitialState = Pattern.compile("InitialState=(.*);").matcher(inputList.get(3));
		if (matcherInitialState.find()) {
			String[] steps = matcherInitialState.group(1).split("\\.");
			initialState.initializeState(steps);
		}
		initialState.printState();
		
		//Goal State
		State goalState = new State(numLines,maxColumns);
		Matcher matcherGoalState = Pattern.compile("GoalState=(.*);").matcher(inputList.get(3));
		if (matcherGoalState.find()) {
			String[] steps = matcherGoalState.group(1).split("\\.");
			goalState.initializeState(steps);
		}
		goalState.printState();
		
		return state;
	}
	
}
