package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.*;

public class Strategy4 implements SearchStrategy {

    public Strategy4() {}
        
    public ArrayList<Node> reconstruct_sol (Node currentNode, int i){
            
        ArrayList<Node> sol = new ArrayList<>();
        Node aux_node = currentNode;

        while (aux_node!=null){
            sol.add(aux_node);
            aux_node = aux_node.getParentNode();
        }
        Collections.reverse(sol);
        for(Node n : sol)
            System.out.println(n.getState() + n.getAction() + "\n");
        return sol;
    }
    

    @Override
    public State solve(SearchProblem p) throws Exception{
        
        ArrayList<Node> explored = new ArrayList<Node>();
        State currentState = p.getInitialState();
        Node currentNode = new  Node(null, null, currentState);
        explored.add(currentNode);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentNode.state);

        while (!p.isGoal(currentNode.state)){
            System.out.println((i++) + " - " + currentNode.state + " is not a goal");
            Action[] availableActions = p.actions(currentNode.state);
            boolean modified = false;
            
            for (Action acc: availableActions) {
                State sc = p.result(currentNode.getState(), acc); 
                Node nextNode = new Node(currentNode, acc, sc);
                System.out.println((i++) + " - RESULT(" + currentNode.getState() + ","+ acc + ")=" + sc);
                
                if (!(explored.stream().anyMatch(n -> n.state.equals(sc)))) {
                    nextNode.setAction(acc);
                    nextNode.setParentNode(currentNode);
                    currentNode = nextNode;
                    System.out.println((i++) + " - " + sc + " NOT explored");
                    explored.add(currentNode);
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + currentNode.getState());
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " already explored");
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentNode.getState());

        reconstruct_sol(currentNode, i);

        return currentNode.getState();
    }
}
