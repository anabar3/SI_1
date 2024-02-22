package es.udc.intelligentsystems;


public abstract class SearchProblem {
    private State initialState;
    public State getInitialState() {
        return initialState;
    }

    public SearchProblem(State initialState) {
        this.initialState = initialState;
    }

    /**
     * Indicates whether "st" is a solution to the problem
     * @param st State to check
     * @return True if the state is a goal or false otherwise
     */
    public abstract boolean isGoal(State st);

    /**
     * Returns a list of available actions for a given state
     * @param st State to which the actions can be applied
     * @return Lista de acciones aplicables
     */
    public abstract Action[] actions(State st);

    /**
     * Returns the result of applying a given action to a given state
     * @param st State to which the action will be applied
     * @param act Action to use
     * @return Resulting state
     */
    public State result(State st, Action act){
        return act.applyTo(st);
    }
}
