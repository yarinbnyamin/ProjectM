package algorithms.search;

import java.util.ArrayList;
import java.util.Objects;

enum State {white, grey, black, goal}

public abstract class AState {
    protected State state=State.white;
    protected double cost;
    protected AState cameFrom;

    public AState(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public boolean equals(AState state) {
        return this.state == state.getState();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }
}
