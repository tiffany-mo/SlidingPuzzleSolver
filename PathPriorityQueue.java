package project6;
import java.lang.Comparable;
import java.util.PriorityQueue;

public class PathPriorityQueue {

    class Priority implements Comparable<Priority> {

        Path path;
        int priority;

        public Priority(Path path, int priority) {
            this.path = path;
            this.priority = priority;
        }

        public int compareTo(Priority other) {
            if (this.priority != other.priority) {
                return this.priority - other.priority;
            } else {
                return this.path.getLastState().toString().compareTo(other.path.getLastState().toString());
            }
        }

    }

    private PriorityQueue<Priority> pq;

    public PathPriorityQueue() {
        this.pq = new PriorityQueue<Priority>();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.pq.size();
    }

    public void add(Path path, int priority) {
        this.pq.add(new Priority(path, priority));
    }

    public Path poll() {
        if (this.pq.isEmpty()) {
            return null;
        }
        return pq.poll().path;
    }

    public Path peek() {
        if (this.pq.isEmpty()) {
            return null;
        }
        return pq.peek().path;
    }
    
}