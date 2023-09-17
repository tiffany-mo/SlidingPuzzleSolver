package project6;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;


public class AStarSearch implements GraphSearchAlgorithm {
	PathPriorityQueue queue = new PathPriorityQueue();
	List<State> discovered = new LinkedList<>();
	
	 public Path search(State start, State goal) {
		 Path startV = new Path(start);
		 queue.add(startV, 0);
		 discovered.add(start);
		 
	  
		 while (!queue.isEmpty()) {
			 startV = queue.poll();
			 if (startV.getLastState().equals(goal)) {
		    	 return startV;
		     } 
		     
			 
			 List<Action> actions = startV.getLastState().getActions();
			 for (int i = 0; i < actions.size(); i++) {    			
				 Path path = new Path(startV, actions.get(i));
				 if (!discovered.contains(path.getLastState())) {
					 queue.add(path, i);
					 discovered.add(path.getLastState());
				 }
			 }
		 }
	     
		 
		 return null;
		 
	 }

}