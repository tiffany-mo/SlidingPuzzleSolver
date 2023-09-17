package project6;
import java.util.List;

public abstract class State {

    public boolean equals(Object obj) {
        return (
            obj != null
            && this.getClass().equals(obj.getClass())
            && this.toString().equals(obj.toString())
        );
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public abstract List<Action> getActions();

    public abstract int heuristicTo(State goal);

    public abstract String toString();

    public abstract void print();
}