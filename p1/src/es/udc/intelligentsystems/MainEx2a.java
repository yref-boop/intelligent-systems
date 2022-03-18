package es.udc.intelligentsystems;

import es.udc.intelligentsystems.*;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import java.util.*;

public class MainEx2a {

    public static void main(String[] args) throws Exception {

        //initial state -> {4,9,2,3,5,0,0,1,0} 

        ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(2); list.add(0); list.add(0); list.add(0); list.add(0); 
            list.add(0); list.add(0); list.add(0); list.add(0);

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(list);
        
        SearchProblem square = new MagicSquareProblem(initialState);
                
        SearchStrategy searcher = new DepthFirstSearchStrategy();

        System.out.println(searcher.solve(square));
    }
}
