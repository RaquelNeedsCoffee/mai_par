import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarTransportPlanning {

	
	public static void main(String[] args) {
		//2 arguments are required: inputFile and outputFile
		if (args.length != 1) {
			System.out.println("ERROR: Incorrect number of arguments.\nUsage: java CarTransportPlanning inputFilename outputFilename");
			return;
		}
		String mydata = "NumLines=33243;dfasdfa";
		Matcher matcher = Pattern.compile("NumLines=(\\d+);").matcher(mydata);
		

		State initialState = PlannerIOHelper.getInitialState(args[0]);
		}
	
}
