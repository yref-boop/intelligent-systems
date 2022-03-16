package es.udc.intelligentsystems;
import java.util.*;
import es.udc.intelligentsystems.*;

public abstract class HeuristicImplemenation {

    public float value(State st){
        MagicSquareProblem.MagicSquareState s = (MagicSquareProblem.MagicSquareState) st;
        int n = s.n;
        int wanted = (int) (s.n * ((s.n * s.n * s.n + s.n)/2));
        int actual = 0, proximity = 0;

        for (int l = 0; l < n; l++) {
            for (int k = l*n; k < l*n + n; k++) { actual += s.values.get(k); }
        }
        return (float)(Math.abs(actual - wanted));
    }

    public float repetivity(State st) {
        MagicSquareProblem.MagicSquareState s = (MagicSquareProblem.MagicSquareState) st;
        int duplicates = 0;
        ArrayList<Integer> array= s.values;
        List<Integer> checked = new ArrayList<>();
        for (Integer integer : array) {
            if (checked.contains(integer)) duplicates++;
            else checked.add(integer);
        }
        return (float)(duplicates/(array.size()));
    }

    public float evaluate(State e) {
        return (value(e) + repetivity(e));
    }
}
