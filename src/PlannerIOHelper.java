import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlannerIOHelper {

	public static State getInitialState(String inputFilename) {
		
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
		
		/**Get all the necessary information to create a Initial and Goal states **/
		//NumLines
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
		String[] blocks = null;
		//TODO: Question: can this input line be ignored?
		Matcher matcherBlocks = Pattern.compile("Blocks=(.*);").matcher(inputList.get(2));
		if (matcherBlocks.find()) {
			blocks = matcherBlocks.group(1).split("\\.");
		}
		else {
			throw new RuntimeException("Incorrect input file. Blocks not found");
		}
		
		//Initial State
		State initialState = new State(numLines,maxColumns,blocks);
		Matcher matcherInitialState = Pattern.compile("InitialState=(.*);").matcher(inputList.get(3));
		if (matcherInitialState.find()) {
			String[] steps = matcherInitialState.group(1).split("\\.");
			initialState.initializeState(steps);
		}
		
		//Return initial states
		return initialState;
	}
	
	public static GoalStack getInitialGoalStack(String inputFilename) {
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
		
		//Goal Stack
		GoalStack goalStack = new GoalStack();
		Matcher matcherGoalState = Pattern.compile("GoalState=(.*);").matcher(inputList.get(4));
		if (matcherGoalState.find()) {
			String[] steps = matcherGoalState.group(1).split("\\.");
			goalStack.initializeStack(steps);
		}
		return goalStack;
	}

	public static void outputFinalPlan(ArrayList<StackElement> plan, String outputFilename) {
		FileWriter fw = null;
		try 
		{
			
			fw = new FileWriter(outputFilename);
			String s = "";
			for (int i = 0; i < plan.size(); i++) {
				StackElement op = plan.get(i);
				if (i != 0) s += ",";
				s += op.getName() + "(" + String.join(",", op.getArgs()) + ")";
				for (int j = 0; j < op.getArgs().size(); ++j) {
					op.getArgs().get(j);
				}
			}
			s += "\n";
			fw.write(s);
			fw.close();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
}
