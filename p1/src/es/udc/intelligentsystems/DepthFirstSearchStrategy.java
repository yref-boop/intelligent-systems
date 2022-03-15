package es.udc.intelligentsystems;

import es.udc.intelligentsystems.*;
import java.util.*;

public class DepthFirstSearchStrategy implements SearchStrategy {  

    public DepthFirstSearchStrategy() {}
        
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
        ArrayList<Node> succ = new ArrayList<Node>();
        Action[] availableActions = p.actions(node.state);

        for (Action act: availableActions){     
            State st = p.result(node.getState(), act);
            Node aux_node = new Node(node, act, st);
            succ.add(aux_node);
        }             
        return succ;
    }


    @Override
    public State solve(SearchProblem p) throws Exception{
        
        ArrayList<Node> explored = new ArrayList<Node>();
            //add and remove 
        Deque<Node> frontier = new LinkedList<Node> (); 

        State currentState = p.getInitialState();
        Node currentNode = new  Node(null, null, currentState);

        int i = 1;
        int expanded, created;
        expanded = 0;
        created = 1;

        if (p.isGoal(currentNode.getState())) {
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

            currentNode = frontier.pop();
            currentState = currentNode.getState();

            if (p.isGoal(currentState)) {
                System.out.println((i++) + " - END - " + currentState);
                System.out.println ("number of expanded nodes" + expanded + "\n" + "number of created nodes" + created);
                reconstruct_sol(currentNode, i);
                return currentState;
            }

            explored.add(currentNode);
            expanded++;
            ArrayList<Node> succs = successors(p, currentNode);

            for (Node succNode: succs){
                State st = succNode.getState();
                     System.out.println((i++) + " - RESULT(" + succNode.getState() + "," + succNode.getAction() + ")=" + st);
                if (!(explored.stream().anyMatch(n -> n.state.equals(st)))) {
                    if (!(frontier.stream().anyMatch(n -> n.state.equals(st)))) {
                        frontier.add(succNode);
                        created++;
                        System.out.println((i++) + " - " + st + " NOT explored");
                    }
                else
                    System.out.println((i++) + " - " + st + " already explored");
                }
            }
        }
        throw new Exception("No solution could be found");
    }
}
