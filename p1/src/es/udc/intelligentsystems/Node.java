package es.udc.intelligentsystems;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
  
    //attributes
    private Node parent;
    public Action action;
    public State state;
    public float g;
    public float f;

    @Override
    public int compareTo(Node other){
        int value;
        if (this.g == other.g) return 0;
        else {
            if ((this.g - other.g)<0)
                return -1;
            else
                return 1;
        }
    }
 
    //constructor
    public Node(Node parent, Action action, State state) {
        this.parent = parent;
        this.action = action;
        this.state = state;
    }
 
    //setters
    public void setParentNode(Node node) {
        this.parent = node;
    }
    public void setAction(Action act){
        this.action = act;
    }
    
    public void setState(State st){
        this.state = st;
    }
    public void setgValue(float f){
        this.g = f;
    }
    public void setfValue(float f){
        this.f = f;
    }

    //getters
    public Node getParentNode() {
        return this.parent;
    }
    public Action getAction() {
        return this.action;
    }
    public State getState(){
        return this.state;
    }
    public float getgValue(){
        return this.g;
    }
    public float getfValue(){
        return this.f;
    }
}
