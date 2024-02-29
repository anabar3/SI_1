package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class GraphSearchStrategy implements SearchStrategy {

    public GraphSearchStrategy() {
    }

    ArrayList<Node> successors (SearchProblem p, Node node ){
        ArrayList<Node> succ = new ArrayList<Node>();
        for (Action acc : p.actions(node.getState())){
            succ.add(new Node (p.result(node.getState(), acc), node, acc));
        }
        return succ;
    }
    private Node[] reconstruct_sol(Node node){
        ArrayList<Node> sol= new ArrayList<Node>();
        while (node.getParent()!=null){
            sol.add(node);
            node = node.getParent();
        }
        return sol.reversed().toArray(new Node[0]);
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        ArrayList<Node> explored = new ArrayList<>();
        ArrayList<Node> frontier = new ArrayList<>();

        Node currentNode = new Node(p.getInitialState(), null, null);
        State currentState = currentNode.getState();
        frontier.add (currentNode);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);

        while (!frontier.isEmpty()) {
            currentNode = frontier.getFirst();
            currentState = currentNode.getState();
            System.out.println((i++) + " - Current state changed to " + currentState);

            //System.out.println((i++) + " - RESULT(" + currentNode.getParent().getState() + "," + currentNode.getAction() + ")=" + currentState);

            if (!p.isGoal(currentState)) {
                System.out.println( " - " + currentNode.getState() + " is not a goal");

                explored.add(currentNode); //INSERT (N,E)

                ArrayList<Node> succ = successors(p, currentNode); //SUCCESSORS

                ArrayList<Node> nodesToRemove = new ArrayList<>();

                for (Iterator<Node> iterator = succ.iterator(); iterator.hasNext();) {
                    Node n = iterator.next();
                    boolean leave = false;
                    for (Node m : frontier) {
                        if (n.getState().equals(m.getState())) {
                            System.out.println(currentState + " already in the frontier");
                            leave = true;
                            break;
                        }
                    }
                    if (leave) {
                        nodesToRemove.add(n);
                        continue;
                    }
                    for (Node m : explored) {
                        if (n.getState().equals(m.getState())) {
                            System.out.println(currentState + " already explored");
                            nodesToRemove.add(n);
                            break;
                        }
                    }
                }

                succ.removeAll(nodesToRemove); //remove from successor not valid
                frontier.addAll(succ); //Insert in frontier all valid successors
            } else break;
            frontier.removeFirst();

        }
        System.out.println(" - END - " + currentState);

        if (p.isGoal(currentState)) return reconstruct_sol(currentNode);

        throw new Exception ("No solution could be found");
    }
}

/*for (Node n : succ){ //CHECK IF IN FRONTIER OR EXPLORED
                    boolean leave = false;
                    for (Node m : frontier){
                        if (n.getState().equals(m.getState())){
                            succ.remove(n); //REMOVE FROM SUCC
                            System.out.println(currentState + " already in the frontier");
                            leave = true;
                            break;
                        }
                    }
                    if (leave) continue;
                    for (Node m : explored){
                        if (n.getState().equals(m.getState())){
                            succ.remove(n);
                            System.out.println(currentState + " already explored");
                            break;
                        }
                    }
                }*/