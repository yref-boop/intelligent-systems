package es.udc.intelligentsystems;

import java.util.*;

public class MainEx2b {

    public static void main(String[] args) throws Exception {

        //initial state -> {4,9,2,3,5,0,0,1,0} 

        ArrayList<Integer> list = new ArrayList<>();

        list.add(2); list.add(0); list.add(15); list.add(0);
        list.add(0); list.add(12); list.add(0); list.add(3);
        list.add(11); list.add(0); list.add(4); list.add(0);
        list.add(0); list.add(0); list.add(10); list.add(0);

        Heuristic heuristic = new HeuristicImplementation();

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(list);
        SearchProblem square = new MagicSquareProblem(initialState);
        InformedSearchStrategy searcher = new ASearchStrategy();
        System.out.println(searcher.solve(square, heuristic));
    }
}

