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

	public static StackElement FirstFerry(String x) {
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		StackElement e = new StackElement("condition","FirstFerry",args);
		return e;
	}

	public static StackElement NextToDock(String x, String y) {
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		StackElement e = new StackElement("condition","NextToDock",args);
		return e;
	}

	public static StackElement LastFerry(String x) {
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		StackElement e = new StackElement("condition","LastFerry",args);
		return e;
	}

	public static StackElement NextToFerry(String x, String y) {
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		StackElement e = new StackElement("condition","NextToFerry",args);
		return e;
	}

	public static StackElement FreeLine(String x) {
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		StackElement e = new StackElement("condition","FreeLine",args);
		return e;
	}

	public static StackElement ExistsEmptyLine() {
		StackElement e = new StackElement("condition","ExistsEmptyLine",null);
		return e;
	}
	
	
}
