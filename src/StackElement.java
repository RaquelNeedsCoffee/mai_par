import java.util.ArrayList;


public class StackElement {
	String type;
	String name;
	ArrayList<String> args;
	ArrayList<StackElement> preconditions;
	ArrayList<StackElement> addList;
	ArrayList<StackElement> deleteList;
	
	public StackElement(String type, String name, ArrayList<String> args) {
		this.type = type;
		this.name = name;
		this.args = args;
	}

	public boolean isOperator() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isCondition() {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<StackElement> getPreconditions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<StackElement> getAddList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<StackElement> getDeleteList() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getArgs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//only for conditions
	public boolean equals(StackElement e) {
		if (!type.equals(e.getType())) return false;
		if (!type.equals(e.getName())) return false;
		ArrayList<String> args2 = e.getArgs();
		if (args.size() != args2.size()) return false;
		for (int i = 0; i < args.size(); ++i) {
			if (args.get(i).equals(args2.get(i))) return false;
		}
		return true;
	}
	
}
