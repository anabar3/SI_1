package es.udc.intelligentsystems;

public abstract class Action {
    @Override
    public abstract String toString();

    /**
     * Checks whether a state meets the preconditions of the action
     * @param st State being checked
     * @return True if "st" meets the preconditions and false otherwise
     */
    public abstract boolean isApplicable(State st);

    /**
     * Returns the resulting state of applying the action to the "st" state
     * @param st State on which the action is applied
     * @return Resulting state
     */
    public abstract State applyTo(State st);

    /**
     * Return the cost of applying the action which, by default, is 1.
     * @return Cost of applying the action
     */
    public float getCost(){ return 1; }
}
