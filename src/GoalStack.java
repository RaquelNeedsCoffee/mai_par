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
		for (int i = 0; i < steps.length; ++i) {
			System.out.println(steps[i]);
		}
		ArrayList<StackElement> firsts = new ArrayList<StackElement>();
		ArrayList<StackElement> nextTos = new ArrayList<StackElement>();
		ArrayList<StackElement> lasts = new ArrayList<StackElement>();
		//Store steps into StackElement objects (conditions).
		for (String step : steps) {
			Matcher matcherFirstFerry = Pattern.compile("FirstFerry\\((\\w+)\\)").matcher(step);
			if (matcherFirstFerry.find()) {
				firsts.add(Conditions.FirstFerry(matcherFirstFerry.group(1)));
				continue;
			}
			
			Matcher matcherNextToFerry = Pattern.compile("NextToFerry\\((\\w+),(\\w+)\\)").matcher(step);
			if (matcherNextToFerry.find()) {
				nextTos.add(Conditions.NextToFerry(matcherNextToFerry.group(1), matcherNextToFerry.group(2)));
				continue;
			}
			
			Matcher matcherLastFerry = Pattern.compile("LastFerry\\((\\w+)\\)").matcher(step);
			if (matcherLastFerry.find()) {
				lasts.add(Conditions.LastFerry(matcherLastFerry.group(1)));
				continue;
			}
		}
		//Push them in the stack in the right order
		System.out.println(nextTos.size());
		
		for (int i = 0; i < firsts.size(); ++i) {
			StackElement condition = firsts.get(i);
			String carInFront = condition.getArgs().get(0);
			while (condition != null) {
				carInFront = condition.getArgs().get(0);
				stack.addLast(condition);
				condition = getNextCondition(carInFront,nextTos);
			}
		}
		//add LastFerrys()
		for (StackElement car: lasts) {
			stack.addLast(car);
		}
		for (int i = 0; i < stack.size(); ++i) {
			System.out.println(stack.get(i).toString());
		}
	}
	
	

	private StackElement getNextCondition(String carInFront, ArrayList<StackElement> nextTos) {
		for (int i = 0; i < nextTos.size(); ++i) {
			if (nextTos.get(i).getArgs().get(1).equals(carInFront)) {
				return nextTos.get(i);
			}
		}
		return null;
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
