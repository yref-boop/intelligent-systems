package es.udc.intelligentsystems;

import java.util.*;

public class BreadthFirstSearchStrategy implements SearchStrategy {

    public void reconstruct_sol (Node currentNode) {
        ArrayList<Node> sol = new ArrayList<>();
        Node aux_node = currentNode;

        while (aux_node != null) {
            sol.add(aux_node);
            aux_node = aux_node.getParentNode();
        }
        Collections.reverse(sol);
        for(Node n : sol) System.out.println(n.getState().toString() + n.getAction());
    }

    private ArrayList<Node> successors (SearchProblem p, Node node) {
        ArrayList<Node> successors = new ArrayList<>();
        Action[] availableActions = p.actions(node.state);

        for (Action act: availableActions){
            State st = p.result(node.getState(), act);
            Node aux_node = new Node(node, act, st);
            successors.add(aux_node);
        }
        return successors;
    }

    @Override
    public State solve(SearchProblem p) throws Exception {
        State currentState = p.getInitialState();
        Node currentNode = new Node (null, null, currentState);

        ArrayList<Node> explored = new ArrayList<>();
        //add and remove
        Deque<Node> frontier = new LinkedList<>();
        frontier.add(currentNode);

        int i = 0, created = 0;
        System.out.println(i++ + " - Starting search at " + currentNode.state);

        while (frontier.peek() != null) {
            currentNode = frontier.remove(); //function "remove" since it is breadth first
            currentState = currentNode.getState();

            if (p.isGoal(currentState)) {
                System.out.println(++i + ") ***** END *****\n" + currentState);
                System.out.println ("Number of expanded nodes: " + explored.size() + "\n" +
                                    "Number of created nodes: " + created);
                reconstruct_sol(currentNode);
                return currentState;
            }

            explored.add(currentNode);
            ArrayList<Node> successors = successors(p, currentNode);

            for (Node successorNode : successors) {
                created++;
                State st = successorNode.getState();
                System.out.println(i++ + ") RESULT:\n" + successorNode.getState()
                                       + successorNode.getAction() + ":\n" + st);
                if (explored.stream().noneMatch(n -> n.state.equals(st))) {
                    if (frontier.stream().noneMatch(n -> n.state.equals(st))) {
                        frontier.add(successorNode);
                        System.out.println(i++ + ") " + st + " NOT explored");
                    }
else System.out.println(i++ + ") " + st + " already explored");
                }
            }
        }
        throw new Exception ("No solution could be found");
    }
}
