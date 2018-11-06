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
		this.preconditions = null;
		this.addList = null;
		this.deleteList = null;
	}

	public boolean isOperator() {
		return (type.equals("operator"));
	}
	
	public boolean isCondition() {
		return (type.equals("condition"));
	}

	public ArrayList<StackElement> getPreconditions() {
		return preconditions;
	}
	
	public ArrayList<StackElement> getAddList() {
		return addList;
	}
	
	public ArrayList<StackElement> getDeleteList() {
		return deleteList;
	}

	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<String> getArgs() {
		return args;
	}
	
	public void setArgs(ArrayList<String> args) {
		this.args = args;
	}
	
	public void setPreconditions(ArrayList<StackElement> preconditions) {
		this.preconditions = preconditions;
	}

	public void setAddList(ArrayList<StackElement> addList) {
		this.addList = addList;
	}

	public void setDeleteList(ArrayList<StackElement> deleteList) {
		this.deleteList = deleteList;
	}
	
	public String toString() {
		if (args == null) return name + "()";
		return name + "(" + String.join(",", args) + ")";
	}
	
	public boolean isInstantiated() {
		if (args == null) return true;
		for (int i = 0; i < args.size(); ++i) {
			if (args.get(i) == null) return false;
		}
		return true;
	}
	
	//only used for conditions
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StackElement)) return false;
        StackElement e = (StackElement) o;
		if (!type.equals(e.getType())) return false;
		if (!name.equals(e.getName())) return false;
		ArrayList<String> args2 = e.getArgs();
		if (args.size() != args2.size()) return false;
		for (int i = 0; i < args.size(); ++i) {
			if (!args.get(i).equals(args2.get(i))) return false;
		}
		return true;
	}
	


}
