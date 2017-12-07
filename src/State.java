import java.awt.*;

public class State<T> {

    private T state;

    private State<T> _cameFrom;

    private int cost;

    private double heuristics;

    private int creation_time;

    private Point point;

    private int priority;

    public State(T state, int cost, int creation_time, Point point, int priority) {
        this.state = state;
        this.cost = cost;
        this.creation_time = creation_time;
        this.point = point;
        this.priority = priority;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setHeuristics(double heuristics) {
        this.heuristics = heuristics;
    }

    public void setCreation_time(int creation_time) {
        this.creation_time = creation_time;
    }

    public void setCameFrom(State<T> s)
    {
        this._cameFrom = s;
    }

    public Point getPoint() {
        return point;
    }


    public double getHeuristics() {
        return this.heuristics;
    }

    public int getCreation_time() {
        return creation_time;
    }

    public int getPriority() {
        return priority;
    }



    public int getCost()
    {
        return this.cost;
    }

    public State<T> getCameFrom()
    {
        return this._cameFrom;
    }


    public T getState()
    {
        return this.state;
    }
}
