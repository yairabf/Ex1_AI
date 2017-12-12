import java.awt.*;

/**
 * class the represent a state in search algorithm
 */
public class State {

    //the string that represent the state
    private String state;
    // the father that create this state
    private State _cameFrom;
    //the cost to go to this state
    private int cost;
    //the heuristics of this state
    private double heuristics;
    // the creation time of this state.
    private int creation_time;
    //the location of this state
    private Point point;
    //the priority of this state.
    private int priority;

    /**
     *
     * @param state the string that represent the state
     * @param cost of the state
     * @param creation_time of the state
     * @param point location of the state
     * @param priority of the state.
     */
    public State(String state, int cost, int creation_time, Point point, int priority) {
        this.state = state;
        this.cost = cost;
        this.creation_time = creation_time;
        this.point = point;
        this.priority = priority;
    }

    /**
     * setter
     * @param cost we want to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * setter
     * @param heuristics we want to set
     */
    public void setHeuristics(double heuristics) {
        this.heuristics = heuristics;
    }

    /**
     * setter
     * @param creation_time we want to set
     */
    public void setCreation_time(int creation_time) {
        this.creation_time = creation_time;
    }

    /**
     * setter
     * @param s the father of the state
     */
    public void setCameFrom(State s)
    {
        this._cameFrom = s;
    }

    /**
     * getter
     * @return location of the state
     */
    public Point getPoint() {
        return point;
    }

    /**
     * getter
     * @return the heuristics
     */
    public double getHeuristics() {
        return this.heuristics;
    }

    /**
     * getter
     * @return creation time
     */
    public int getCreation_time() {
        return creation_time;
    }

    /**
     * getter
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * getter
     * @return the cost
     */
    public int getCost()
    {
        return this.cost;
    }

    /**
     * getter
     * @return cameFrom
     */
    public State getCameFrom()
    {
        return this._cameFrom;
    }

    /**
     * getter
     * @return the state.
     */
    public String getState()
    {
        return this.state;
    }
}
