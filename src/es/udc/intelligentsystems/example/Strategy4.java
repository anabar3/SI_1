package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Strategy4 implements SearchStrategy {

    public Strategy4() {
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
    public Node[] solve(SearchProblem p) throws Exception{
        ArrayList<State> explored = new ArrayList<State>();
        Node currentNode = new Node (p.getInitialState(), null, null);
        State currentState= currentNode.getState();
        explored.add(currentState);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);

        while (!p.isGoal(currentState)){
            System.out.println((i++) + " - " + currentNode.getState() + " is not a goal");
            Action[] availableActions = p.actions(currentState);
            boolean modified = false;

            for (Action acc: availableActions) {
                State sc = p.result(currentState, acc);
                System.out.println((i++) + " - RESULT(" + currentState + ","+ acc + ")=" + sc);
                if (!explored.contains(sc)) {
                    currentState = sc;
                    System.out.println((i++) + " - " + sc + " NOT explored");

                    currentNode = new Node (sc,currentNode,acc);

                    explored.add(currentState);
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + currentState);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " already explored");
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentState);

        return reconstruct_sol(currentNode);
    }
}
