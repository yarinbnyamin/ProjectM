package algorithms.search;

import java.util.ArrayList;
import java.util.Objects;


public abstract class AState {
    protected String state;
    protected double cost;
    protected AState cameFrom;

    public AState(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public boolean equals(AState state) {
        return this.state.equals(state.getState());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return Double.compare(aState.cost, cost) == 0 && Objects.equals(state, aState.state) && Objects.equals(cameFrom, aState.cameFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
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
