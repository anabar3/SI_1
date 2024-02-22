package es.udc.intelligentsystems;

public abstract class State {
    /* Any State class must override these methods to be correctly shown and allow comparisons. */

    @Override
    public abstract java.lang.String toString();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

}
