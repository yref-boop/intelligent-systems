package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;

public class Node {
  
    //attributes
    private Node parent;
    public String action;
    private State state;

 
    //constructor
    public Node(Node parent, String action, State state, Node next) {
        this.parent = parent;
        this.action = action;
        this.state = state;
    }
 
    //setter
    public void setNextNode(Node node) {
        this.next = node;
    }
 
    //getter
    public Node getNextNode() {
        return this.next;
    } 
}
