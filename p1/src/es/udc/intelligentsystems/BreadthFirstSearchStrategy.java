package es.udc.intelligentsystems;

import es.udc.intelligentsystems.*;
import java.util.*;

public class BreadthFirstSearchStrategy implements SearchStrategy {  

    public BreadthFirstSearchStrategy() {}
        
    //public ArrayList<Node> reconstruct_sol (Node currentNode, int i){}  

    private ArrayList<Node> successors (SearchProblem p, Node node){
        ArrayList<Node> children = new ArrayList<Node>();
        Action[] availableActions = p.actions(node.state);
        Node auxNode, cpNode;
        State st;
        cpNode = node;
        int i = 0;
        for (Action act: availableActions){
            st = p.result(cpNode.getState(), act); //this line fails (possibly in applyTo())
            System.out.println(node.state);
            auxNode = new Node (node, act, st);
            children.add(auxNode);
            i++;
        }
        return children;
    }


    @Override
    public State solve(SearchProblem p) throws Exception{
        ArrayList<Node> explored = new ArrayList<Node>();
        Deque<Node> frontier = new LinkedList<Node>();
        int i = 0;

        State currentState = p.getInitialState();
        Node currentNode = new Node (null, null, currentState);

        if (p.isGoal (currentNode.getState())) {
            System.out.println((i++) + " - END - " + currentNode.getState());
            return currentNode.getState();
        }
        else {
            frontier.add(currentNode);
        }

        System.out.println((i++) + " - Starting search at " + currentNode.state);

        while (null!=(frontier.peek())){
            currentNode = frontier.remove();
            currentState = currentNode.getState();
            
            if (p.isGoal(currentState)){
                System.out.println((i++) + " - END - " + currentState);
                return currentNode.getState();
            }
            System.out.println(currentNode.getState());
            explored.add(currentNode);
            
            ArrayList<Node> children = successors(p, currentNode);
            for (Node childNode : children){
                State st = childNode.getState();
                System.out.println((i++) + " - RESULT(" + childNode.getState() + "," + childNode.getAction() + ")=" + st);
                if (!(explored.stream().anyMatch(n -> n.state.equals(st)))) {
                    if (!(frontier.stream().anyMatch(n -> n.state.equals(st)))) {
                        frontier.add(childNode);
                        System.out.println((i++) + " - " + st + " NOT explored");
                    }
                else
                    System.out.println((i++) + " - " + st + " already explored");
                }
            }
            System.out.println("for finished");
        }
        throw new Exception ("while out");
    }
}
