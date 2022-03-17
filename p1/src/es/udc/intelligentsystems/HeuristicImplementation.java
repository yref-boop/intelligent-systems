package es.udc.intelligentsystems;
import java.util.*;
import es.udc.intelligentsystems.*;

public abstract class HeuristicImplementation {

    public float odd(State st) {
        MagicSquareProblem.MagicSquareState s = (MagicSquareProblem.MagicSquareState) st;
        return Math.abs((s.n*s.n)/2 - s.values.get((s.n*s.n-1)/2));
    }

    int getCornerSum (MagicSquareProblem.MagicSquareState sq) {
        return sq.values.get(0) + sq.values.get(sq.n-1)
             + sq.values.get(sq.n*sq.n-sq.n) + sq.values.get(sq.n*sq.n-1);
    }

    public float oddCentral(State st) {
        MagicSquareProblem.MagicSquareState s = (MagicSquareProblem.MagicSquareState) st;
        return Math.abs(s.values.get(s.values.get((s.n*s.n-1)/2))*4 - getCornerSum(s));
    }

    public float evenCentral(State st) {
        MagicSquareProblem.MagicSquareState s = (MagicSquareProblem.MagicSquareState) st;
        int topLeft = s.n/2*s.n-s.n/2-1;
        return Math.abs(s.values.get(topLeft) + s.values.get(topLeft+1) + s.values.get(topLeft+s.n)
              + s.values.get(topLeft+s.n+1) - getCornerSum(s));
    }

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
        MagicSquareProblem.MagicSquareState s = (MagicSquareProblem.MagicSquareState) e;
        if (s.n%2 == 0){
            return (value(e) + repetivity(e) + evenCentral(e) + corners(e));
        }
        else return (value(e) + repetivity(e) + oddCentral(e) + odd(e) + corners(e));
    }
}
