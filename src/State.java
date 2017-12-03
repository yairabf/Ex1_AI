public class State<T> {

    private T state;

    private int cost;

    private State<T> _cameFrom;

    public State(T state)
    {
        this.state = state;
    }

    public double GetCost()
    {
        return this.cost;
    }

    public void SetCost(int c)
    {
        this.cost = c;
    }

    public State<T> GetCameFrom()
    {
        return this._cameFrom;
    }

    public void SetCameFrom(State<T> s)
    {
        this._cameFrom = s;
    }

    public T GetState()
    {
        return this.state;
    }
}
