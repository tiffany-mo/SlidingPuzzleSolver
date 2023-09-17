package project6;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

public class DepthFirstSearch implements GraphSearchAlgorithm {
	 List<State> discovered = new LinkedList<State>();
	 public Path search(State start, State goal) {	     
	   
	    Path path = new Path(start);
		if (start.equals(goal)) {
			return path;
		} else {
			return search(goal, start, path);
		
		}
	     
	 }
	 
	 public Path search(State goal, State current, Path path) {
	    
		 
		 if (current.equals(goal)) {
	    	 return path;
	     } else if (discovered.contains(current)) {
	    	 return null;
	     } else {
	    	 discovered.add(current);
	    	 List<Action> list = current.getActions();
	    	 Stack<Action> stack = new Stack<Action>();
	    	 
	    	 for (int i = 0; i < list.size(); i ++) {
	    		stack.push(list.get(i));
	    	 }
	    	 
	    	while (!stack.isEmpty()) {
	    		
	    			Action action = stack.pop();
	    			Path newPath = new Path(path, action);
	    			State newState = action.getNextState();
	    			Path nextSolution = search(goal,newState, newPath);
	    			if (nextSolution != null) {
	    				return nextSolution;
	       	 	}
	    	 }
	    	 
	     }
		 return null;
	 }


}