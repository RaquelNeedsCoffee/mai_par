import java.util.LinkedList;


public class GoalStack {

	LinkedList<StackElement> stack;
	
	public boolean empty() {
		return stack.isEmpty();
	}
	
	public StackElement pop() {
		return stack.removeFirst();
	}

	public void push(StackElement e) {
		stack.addFirst(e);
	}

	public void initializeStack(String[] steps) {
		
	}
	
}
