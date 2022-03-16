package es.udc.intelligentsystems;

import es.udc.intelligentsystems.*;
import java.util.*;

public class BreadthFirstSearchStrategy implements SearchStrategy {  

    public BreadthFirstSearchStrategy() {}
        
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

    private ArrayList<Node> successors (SearchProblem p, Node node){
        ArrayList<Node> successors = new ArrayList<>();
        Action[] availableActions = p.actions(node.state);
        Node auxNode, cpNode;
        State st;
        cpNode = node;
        for (Action act: availableActions) {
            st = p.result(node.getState(), act);
            auxNode = new Node (cpNode, act, st);
            successors.add(auxNode);
            cpNode = auxNode;
        }
        return successors;
    }


    @Override
    public State solve(SearchProblem p) throws Exception{
        ArrayList<Node> explored = new ArrayList<Node>();
        Deque<Node> frontier = new LinkedList<Node>();
        int i = 1;

        State currentState = p.getInitialState();
        Node currentNode = new Node (null, null, currentState);
      
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

            ArrayList<Node> children = successors(p, currentNode);
            
            for (Node childNode : children){
                State st = childNode.getState();
                System.out.println((i++) + " - RESULT(" + childNode.getState() + "," + childNode.getAction() + ")=" + st);
                if (!(explored.stream().anyMatch(n -> n.state.equals(st)))) {
                    if (!(frontier.stream().anyMatch(n -> n.state.equals(st)))) {
                        frontier.add(childNode);
                        created++;
                        System.out.println((i++) + " - " + st + " NOT explored");
                    }
                else
                    System.out.println((i++) + " - " + st + " already on the frontier");
                }
            }
        }
        throw new Exception ("while out");
    }
}
