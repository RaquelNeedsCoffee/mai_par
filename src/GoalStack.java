import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GoalStack {

	LinkedList<StackElement> stack;
	
	public GoalStack() {
		stack = new LinkedList<StackElement>();
	}
	
	public boolean empty() {
		return stack.isEmpty();
	}
	
	public StackElement pop() {
		return stack.removeFirst();
	}
	
	public StackElement top() {
		return stack.getFirst();
	}

	public void push(StackElement e) {
		stack.addFirst(e);
	}

	//Right now I assume that the order in which steps are given is the right one.
	//That is, the steps in the firsts positions are the ones that should be handled first.
	public void initializeStack(String[] steps) {
		for (String step : steps) {
			Matcher matcherFirstFerry = Pattern.compile("FirstFerry\\((\\w+)\\)").matcher(step);
			if (matcherFirstFerry.find()) {
				stack.addLast(Conditions.FirstFerry(matcherFirstFerry.group(1)));
				continue;
			}
			
			Matcher matcherNextToFerry = Pattern.compile("NextToFerry\\((\\w+),(\\w+)\\)").matcher(step);
			if (matcherNextToFerry.find()) {
				ArrayList<String> args = new ArrayList<String>();
				args.add(matcherNextToFerry.group(1));
				args.add(matcherNextToFerry.group(2));
				StackElement e = new StackElement("condition","NextToFerry",args);
				stack.addLast(e);
				continue;
			}
			
			Matcher matcherLastFerry = Pattern.compile("LastFerry\\((\\w+)\\)").matcher(step);
			if (matcherLastFerry.find()) {
				ArrayList<String> args = new ArrayList<String>();
				args.add(matcherLastFerry.group(1));
				StackElement e = new StackElement("condition","LastFerry",args);
				stack.addLast(e);
				continue;
			}
		}
	}

	public int size() {
		return stack.size();
	}

	public boolean instantiateAndPropagate(State state) {
		//Instantiates all preconditions at once.
		int count = 0;
		while(top().isCondition()) {
			pop();
			count += 1;
		}
		StackElement operator = pop();
		StackElement instantiatedOperator = state.instantiateOperator(operator);
		if (instantiatedOperator == null) return false;
		System.out.println("Instantiated operator: " + instantiatedOperator.toString());
		push(instantiatedOperator);
		ArrayList<StackElement> preconditions = instantiatedOperator.getPreconditions();
		//preconditions should be already correctly ordered (heuristic)
		for (int i = 0; i <= count; i++) {
			push(preconditions.get(i));
		}
		return true;
	}
	
}
