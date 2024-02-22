package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.State;

public class Node {
    State state;
    Node parent;
    Action action;

    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }
}
