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
		preconditions.add(Conditions.FirstDock(x));
		preconditions.add(Conditions.NextToDock(y,x));
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
		addList.add(Conditions.LastFerry(y));
		e.setDeleteList(deleteList);
		return e;
	}

	public static StackElement BoardNextTo2(String x, Object object, String y) {
		// TODO Auto-generated method stub
		return null;
	}
}
