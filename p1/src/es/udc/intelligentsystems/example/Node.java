package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;

public class Node {
  
    //attributes
    private Node parent;
    public Action action;
    public State state;

 
    //constructor
    public Node(Node parent, Action action, State state) {
        this.parent = parent;
        this.action = action;
        this.state = state;
    }
 
    //setter
    public void setParentNode(Node node) {
        this.parent = node;
    }
    public void setAction(Action act){
        this.action = act;
    }
 
    //getter
    public Node getParentNode() {
        return this.parent;
    }
    public Action getAction() {
        return this.action;
    }
    public State getState(){
        return this.state;
    }
}
