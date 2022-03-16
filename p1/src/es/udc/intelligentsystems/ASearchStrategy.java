package es.udc.intelligentsystems;

import es.udc.intelligentsystems.*;
import java.util.*;

public class ASearchStrategy extends InformedSearchStrategy {

    public ASearchStrategy() {}

    public ArrayList<Node> reconstruct_sol (Node currentNode, int i){
        ArrayList<Node> sol = new ArrayList<Node>();
            Node aux_node = currentNode;

            while (aux_node!=null){
                sol.add(aux_node);
                aux_node = aux_node.getParentNode();
            }
            Collections.reverse(sol);
            for(Node n : sol)
                System.out.println(n.getState() + " " + n.getAction());
            return sol;
    }  


    private ArrayList<Node> successors (SearchProblem p, Node node, Heuristic h){
        
        ArrayList<Node> succ = new ArrayList<Node>();
        Action[] availableActions = p.actions(node.state);

        for (Action act: availableActions){ 
            State st = p.result(node.getState(), act);
            Node aux_node = new Node(node, act, st);
            aux_node.setHeuristicValue = h.evaluate(st);
            aux_node.setHeuristicPathValue = h.evaluate(node.getState()) + h.evaluate(currentState);
            succ.add(aux_node);
        }             
        return succ;
    }

    public State solve (SearchProblem p, Heuristic h) throws Exception{
        ArrayList<Node> explored = new ArrayList<Node>();
        Queue<Node> frontier = new PriorityQueue<>();
        int i = 1;

        State currentState = p.getInitialState();
        Node currentNode = new Node (null, null, currentState);
        currentNode.setHeuristicValue = h.evaluate(currentState);
        currentNode.setHeuristicPathValue = h.evaluate(currentState);

        int expanded = 0, created = 1;

        if (p.isGoal (currentNode.getState())) {
            System.out.println((i++) + " - END - " + currentNode.getState());
            System.out.println ("number of expanded nodes" + expanded + "\n" + "number of created nodes" + created);
            return currentNode.getState();
        }
        else {
            frontier.add(currentNode);
            created++;
        }

        System.out.println((i++) + " - Starting search at " + currentNode.state);

        while (null!=(frontier.peek())){
            
            currentNode = frontier.remove();
            currentState = currentNode.getState();

            if (p.isGoal(currentState)){    
                System.out.println((i++) + " - END - " + currentState);
                System.out.println ("number of expanded nodes" + expanded + "\n" + "number of created nodes" + created);
                reconstruct_sol(currentNode, i);
                return currentState;
            }

            explored.add(currentNode);
            expanded++;

            ArrayList<Node> children = successors(p, currentNode, h);
            for (Node childNode : children){
                 
            }
        }
        throw new Exception ("while out");
    }
}
