import java.util.ArrayList;


public class Conditions {
	
	public static StackElement FirstDock(String x) {
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		StackElement e = new StackElement("condition","FirstDock",args);
		return e;
	}
	
	public static StackElement LastDock(String x) {
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		StackElement e = new StackElement("condition","LastDock",args);
		return e;
	}
	
	
}
