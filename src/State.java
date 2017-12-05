public class State<T> {

    private T state;

    private int cost;

    private double heuristics;

    private int creation_time;

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setHeuristics(double heuristics) {
        this.heuristics = heuristics;
    }

    public void setCreation_time(int creation_time) {
        this.creation_time = creation_time;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    private int priority;

    public double getHeuristics() {
        return heuristics;
    }

    public int getCreation_time() {
        return creation_time;
    }

    public int getPriority() {
        return priority;
    }

    private State<T> _cameFrom;

    public State(T state)
    {
        this.state = state;
    }

    public double getCost()
    {
        return this.cost;
    }

    public State<T> getCameFrom()
    {
        return this._cameFrom;
    }

    public void setCameFrom(State<T> s)
    {
        this._cameFrom = s;
    }

    public T getState()
    {
        return this.state;
    }
}
