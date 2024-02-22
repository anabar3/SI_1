package es.udc.intelligentsystems;

public interface SearchStrategy {
    /**
     * Solves a search problem, obtaining a goal state or throwing an exception if none is found
     * @param p Problem to solve
     * @return Goal state found
     */
    public abstract Node[] solve(SearchProblem p) throws Exception;
}
