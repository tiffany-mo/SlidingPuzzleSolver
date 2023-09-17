package project6;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

public class BreadthFirstSearch implements GraphSearchAlgorithm {
	LinkedList<Path> frontier = new LinkedList<>();
	List<State> discovered = new LinkedList<>();
	
	 public Path search(State start, State goal) {
		 
		 Path startV = new Path(start);
		 frontier.add(startV);
		 discovered.add(start);
		 
	  
    	 while (!frontier.isEmpty()) {
    		 startV = frontier.poll();
    		 if (startV.getLastState().equals(goal)) {
    	    	 return startV;
    	     } 
    	     
    		 
    		 List<Action> actions = startV.getLastState().getActions();
    		 for (int i = 0; i < actions.size(); i++) {    			
    			 Path path = new Path(startV, actions.get(i));
    			 if (!discovered.contains(path.getLastState())) {
    				 frontier.add(path);
    				 discovered.add(path.getLastState());
    			 }
    		 }
    	 }
	     
		 
		 return null;
		 
		 
	 }
 	/*
 	 * diff from DFS:
 	 * change stack part 
 	 * pull out from 
 	 * no recursion needed, just while loop
 	 * 	-stacking
 	 * use loop as a while loop instead of recursion
 	 * dont need to update location
 	 * update path 
 	 * store in queue
 	 * when last state == goal --> end 
 	 */

}