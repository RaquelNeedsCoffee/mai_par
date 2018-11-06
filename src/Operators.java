import java.util.ArrayList;


public class Operators {

	/**
	 * FreeLine and NumEmptyLine are not included in addLists or deleteLists in the implementation.
	 * They are maintained dynamically.
	 * 
	 */
	
	public static StackElement BoardFirst1(String x) {
		//Arguments
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		//initialize Stack Element
		StackElement e = new StackElement("operator","BoardFirst1",args);
		//preconditions
		ArrayList<StackElement> preconditions = new ArrayList<StackElement>();
		preconditions.add(Conditions.FirstDock(x));
		preconditions.add(Conditions.LastDock(x));
		e.setPreconditions(preconditions);
		//Add List
		ArrayList<StackElement> addList = new ArrayList<StackElement>();
		addList.add(Conditions.FirstFerry(x));
		addList.add(Conditions.LastFerry(x));
		e.setAddList(addList);
		//Delete List
		ArrayList<StackElement> deleteList = new ArrayList<StackElement>();
		deleteList.add(Conditions.FirstDock(x));
		deleteList.add(Conditions.LastDock(x));
		e.setDeleteList(deleteList);
		return e;
	}
	
	public static StackElement BoardFirst2(String x, String y) {
		//Arguments
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		//initialize Stack Element
		StackElement e = new StackElement("operator","BoardFirst2",args);
		//preconditions
		ArrayList<StackElement> preconditions = new ArrayList<StackElement>();
		preconditions.add(Conditions.NextToDock(y,x));
		preconditions.add(Conditions.FirstDock(x));
		e.setPreconditions(preconditions);
		//Add List
		ArrayList<StackElement> addList = new ArrayList<StackElement>();
		addList.add(Conditions.FirstFerry(x));
		addList.add(Conditions.LastFerry(x));
		addList.add(Conditions.FirstDock(y));
		e.setAddList(addList);
		//Delete List
		ArrayList<StackElement> deleteList = new ArrayList<StackElement>();
		deleteList.add(Conditions.FirstDock(x));
		deleteList.add(Conditions.NextToDock(y,x));
		e.setDeleteList(deleteList);
		return e;
	}

	public static StackElement BoardNextTo1(String x, String y) {
		//Arguments
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		//initialize Stack Element
		StackElement e = new StackElement("operator","BoardNextTo1",args);
		//preconditions
		ArrayList<StackElement> preconditions = new ArrayList<StackElement>();
		preconditions.add(Conditions.FirstDock(x));
		preconditions.add(Conditions.LastDock(x));
		preconditions.add(Conditions.LastFerry(y));
		e.setPreconditions(preconditions);
		//Add List
		ArrayList<StackElement> addList = new ArrayList<StackElement>();
		addList.add(Conditions.LastFerry(x));
		addList.add(Conditions.NextToFerry(x,y));
		e.setAddList(addList);		
		//Delete List
		ArrayList<StackElement> deleteList = new ArrayList<StackElement>();
		deleteList.add(Conditions.FirstDock(x));
		deleteList.add(Conditions.LastDock(x));
		deleteList.add(Conditions.LastFerry(y));
		e.setDeleteList(deleteList);
		return e;
	}

	public static StackElement BoardNextTo2(String x, String y, String z) {
		//Arguments
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		args.add(z);
		//initialize Stack Element
		StackElement e = new StackElement("operator","BoardNextTo2",args);
		//preconditions
		ArrayList<StackElement> preconditions = new ArrayList<StackElement>();
		preconditions.add(Conditions.FirstDock(x));
		preconditions.add(Conditions.NextToDock(y,x));
		preconditions.add(Conditions.LastFerry(z));
		e.setPreconditions(preconditions);
		//Add List
		ArrayList<StackElement> addList = new ArrayList<StackElement>();
		addList.add(Conditions.LastFerry(x));
		addList.add(Conditions.FirstDock(y));
		addList.add(Conditions.NextToFerry(x,z));
		e.setAddList(addList);		
		//Delete List
		ArrayList<StackElement> deleteList = new ArrayList<StackElement>();
		deleteList.add(Conditions.FirstDock(x));
		deleteList.add(Conditions.NextToDock(y,x));
		deleteList.add(Conditions.LastFerry(z));
		e.setDeleteList(deleteList);
		return e;
	}
	
	public static StackElement ChangeLine1(String x, String y) {
		//Arguments
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		//initialize Stack Element
		StackElement e = new StackElement("operator","ChangeLine1",args);
		//preconditions
		ArrayList<StackElement> preconditions = new ArrayList<StackElement>();
		preconditions.add(Conditions.FirstDock(x));
		preconditions.add(Conditions.LastDock(x));
		preconditions.add(Conditions.FirstDock(y));
		preconditions.add(Conditions.FreeLine(y));
		e.setPreconditions(preconditions);
		//Add List
		ArrayList<StackElement> addList = new ArrayList<StackElement>();
		addList.add(Conditions.NextToDock(y,x));
		e.setAddList(addList);		
		//Delete List
		ArrayList<StackElement> deleteList = new ArrayList<StackElement>();
		deleteList.add(Conditions.LastDock(x));
		deleteList.add(Conditions.FirstDock(y));
		e.setDeleteList(deleteList);
		return e;
	}
	
	public static StackElement ChangeLine2(String x, String y, String z) {
		//Arguments
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		args.add(z);
		//initialize Stack Element
		StackElement e = new StackElement("operator","ChangeLine2",args);
		//preconditions
		ArrayList<StackElement> preconditions = new ArrayList<StackElement>();
		preconditions.add(Conditions.FirstDock(z));
		preconditions.add(Conditions.FreeLine(z));
		preconditions.add(Conditions.FirstDock(x));
		preconditions.add(Conditions.NextToDock(y,x));
		e.setPreconditions(preconditions);
		//Add List
		ArrayList<StackElement> addList = new ArrayList<StackElement>();
		addList.add(Conditions.FirstDock(y));
		addList.add(Conditions.NextToDock(z,x));
		e.setAddList(addList);		
		//Delete List
		ArrayList<StackElement> deleteList = new ArrayList<StackElement>();
		deleteList.add(Conditions.NextToDock(y,x));
		deleteList.add(Conditions.FirstDock(z));
		e.setDeleteList(deleteList);
		return e;
	}
	
	public static StackElement ChangeToEmptyLine(String x, String y) {
		//Arguments
		ArrayList<String> args = new ArrayList<String>();
		args.add(x);
		args.add(y);
		//initialize Stack Element
		StackElement e = new StackElement("operator","ChangeToEmptyLine",args);
		//preconditions
		ArrayList<StackElement> preconditions = new ArrayList<StackElement>();
		preconditions.add(Conditions.ExistsEmptyLine());
		preconditions.add(Conditions.FirstDock(x));
		preconditions.add(Conditions.NextToDock(y,x));
		e.setPreconditions(preconditions);
		//Add List
		ArrayList<StackElement> addList = new ArrayList<StackElement>();
		addList.add(Conditions.FirstDock(y));
		addList.add(Conditions.LastDock(x));
		e.setAddList(addList);		
		//Delete List
		ArrayList<StackElement> deleteList = new ArrayList<StackElement>();
		deleteList.add(Conditions.NextToDock(y,x));
		e.setDeleteList(deleteList);
		return e;
	}
	
	public static StackElement getOperator(String name, ArrayList<String> args) {
		if (name.equals("BoardFirst1")) return BoardFirst1(args.get(0));
		else if (name.equals("BoardFirst2")) return BoardFirst2(args.get(0),args.get(1));
		else if (name.equals("BoardNextTo1")) return BoardNextTo1(args.get(0),args.get(1));
		else if (name.equals("BoardNextTo2")) return BoardNextTo2(args.get(0),args.get(1),args.get(2));
		else if (name.equals("ChangeLine1")) return ChangeLine1(args.get(0),args.get(1));
		else if (name.equals("ChangeLine2")) return ChangeLine2(args.get(0),args.get(1),args.get(2));
		else if (name.equals("ChangeToEmptyLine")) return ChangeToEmptyLine(args.get(0),args.get(1));
		else return null;
	}
}
