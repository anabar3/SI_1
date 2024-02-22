package es.udc.intelligentsystems;

public interface InformedSearchStrategy {
    /**
     * Solves a search problem, obtaining a goal state or throwing an exception if none is found
     * @param p Problem to solve
     * @param h Heuristic that assigns a utility value to each state
     * @return Goal state found
     */
    public abstract State solve(SearchProblem p, Heuristic h) throws Exception;
}
