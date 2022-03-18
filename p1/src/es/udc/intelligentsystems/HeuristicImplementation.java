package es.udc.intelligentsystems;
import java.util.*;

public class HeuristicImplementation extends Heuristic {

    public float value(MagicSquareProblem.MagicSquareState s) {
        int n = s.n, wanted = s.n * (s.n * s.n * s.n + s.n)/2, value = 0;
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++)
            value += s.values.get(i*n+j) + s.values.get(j*n+i);
        return Math.abs(value-wanted*(n*n));
    }

    public float repetivity(MagicSquareProblem.MagicSquareState s) {
        int duplicates = 0;
        List<Integer> checked = new ArrayList<>();
        for (Integer integer : s.values) {
            if (checked.contains(integer)) duplicates++;
            else checked.add(integer);
        }
        return duplicates;
    }

    public float zeros(MagicSquareProblem.MagicSquareState s) {
        int result = 0;
        for (int i = 0; i < s.values.size(); i++) {
            if (s.values.get(i) == 0) result ++;
        }
        return result;
    }


    public float odd(MagicSquareProblem.MagicSquareState s) {
        return Math.abs((s.n*s.n)/2 - s.values.get((s.n*s.n-1)/2));
    }

    int getCornerSum (MagicSquareProblem.MagicSquareState sq) {
        return sq.values.get(0) + sq.values.get(sq.n-1)
             + sq.values.get(sq.n*sq.n-sq.n) + sq.values.get(sq.n*sq.n-1);
    }

    public float oddCentral(MagicSquareProblem.MagicSquareState s) {
        return Math.abs(s.values.get(s.values.get((s.n*s.n-1)/2))*4 - getCornerSum(s));
    }

    public float evenCentral(MagicSquareProblem.MagicSquareState s) {
        int topLeft = s.n/2*s.n-s.n/2-1;
        return Math.abs(s.values.get(topLeft) + s.values.get(topLeft+1) + s.values.get(topLeft+s.n)
              + s.values.get(topLeft+s.n+1) - getCornerSum(s));
    }

    public float corners(MagicSquareProblem.MagicSquareState s) {
        return Math.abs(getCornerSum(s) - ((4*(s.n*s.n+1)/2*s.n)/s.n)) +
               Math.abs(getCornerSum(s)/2 - s.n*s.n+1);
    }


    public float value2(MagicSquareProblem.MagicSquareState s) {
        int n = s.n, wanted = (n*n*n + n)/2, result = 0;
        for (int i = 0; i < s.values.size()-1; i++) {
            //if (i%n == n-1) i++;
            if (s.values.get(i)+s.values.get(i+1) >= wanted-(n-2))
                result ++;
        }
        return result;
    }

    public float axis(MagicSquareProblem.MagicSquareState s) {
        int result = 0, n = s.n, penalty = 2*n+2, wanted = (n*n*n + n)/2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) result += s.values.get(j+i*n);
            if (result == wanted) penalty --;
            result = 0;
            for (int j = 0; j < n; j++) result += s.values.get(i+j*n);
            if (result == wanted) penalty --;
            result = 0;
        }

        for (int i = 0; i < n*n; i += n+1) result += s.values.get(i);
        if (result == wanted) penalty --;
        result = 0;

        for (int i = n-1; i < n*n-1; i += n-1) result += s.values.get(i);
        if (result == wanted) penalty --;

        return penalty;
    }

    public float cross(MagicSquareProblem.MagicSquareState s) {
        int n = s.n, crossSum;
        if (n%2==1) return 0;

        crossSum = s.values.get(n/2-1) + s.values.get(n/2) +
                s.values.get(n/2-1 + n*n-n) + s.values.get(n/2 + n*n-n) +
                s.values.get(n*(n/2-1)) + s.values.get(n*(n/2-1)+(n-1)) +
                s.values.get(n*(n/2-1)+n) + s.values.get(n*(n/2-1)+(2*n-1));

        return Math.abs(getCornerSum(s)-crossSum/2);
    }

    public float frame(MagicSquareProblem.MagicSquareState s) {
        int penalty = 4, result = 0, n = s.n, wanted = (n*n*n + n)/2;

        for(int i = 0; i < n; i+=n-1) {
            for(int j = 0; j < n; j++) result += s.values.get(j+i*n);
            for(int j = 0; j < n; j++) result += s.values.get(i+j*n);
            if (result == wanted*2) penalty --;
            result = 0;
        }
        return penalty;
    }

    public float hor(MagicSquareProblem.MagicSquareState s) {
        int result = 0, n = s.n, penalty = n, wanted = (n*n*n + n)/2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) result += s.values.get(j+i*n);
            if (result == wanted) penalty --;
            result = 0;
        }
        return penalty;
    }

    public float diagonals(MagicSquareProblem.MagicSquareState s){
        int n = s.n;
        int difference = 0, result = 0;
        int expected = (n*n*n+n)/2;
        
        for (int l = 0; l < n*n; l += n+1) { result += s.values.get(l); }
            difference += Math.abs(result - expected);
            result = 0;
        for (int l = n-1; l < n*n-1; l += n-1) { result += s.values.get(l); }
            difference += Math.abs(result - expected);

        return (float) difference;
    }

    public float rows(MagicSquareProblem.MagicSquareState s){
        int n = s.n;
        int difference = 0, result = 0;
        int expected = (n*n*n+n)/2;
        
        for (int l = 0; l < n; l++) {
            for (int k = l*n; k < l*n + n; k++) { result += s.values.get(k); }
            difference += Math.abs(result - expected);
            result = 0;
        }
        return (float) difference;
    }

    public float columns(MagicSquareProblem.MagicSquareState s){
        int n = s.n;
        int difference = 0, result = 0;
        int expected = (n*n*n+n)/2;
        
        for (int l = 0; l < n; l++) {
            for (int k = l; k < n*n; k+=n) { result += s.values.get(k); }
            difference += Math.abs(result - expected);
            result = 0;
        }
        return (float) difference;
    }  

    public float goal(MagicSquareProblem.MagicSquareState s){
        int n = s.n;
        int difference = 0, result = 0;
        int expected = (n*n*n+n)/2;

        //rows
        for (int l = 0; l < n; l++) {
            for (int k = l*n; k < l*n + n; k++) { result += s.values.get(k); }
            difference += Math.abs(result - expected);
            result = 0;
        }

        //columns
        for (int l = 0; l < n; l++) {
            for (int k = l; k < n*n; k+=n) { result += s.values.get(k); }
            difference += Math.abs(result - expected);
            result = 0;
        }

        //diagonals
        for (int l = 0; l < n*n; l += n+1) { result += s.values.get(l); }
            difference += Math.abs(result - expected);
            result = 0;
        for (int l = n-1; l < n*n-1; l += n-1) { result += s.values.get(l); }
            difference += Math.abs(result - expected);

        return (float) difference;
    }

    public float evaluate(State st) {
        MagicSquareProblem.MagicSquareState s = (MagicSquareProblem.MagicSquareState) st;

      // return(goal(e)/100 + repetivity(e)*10 + cross(e) + diagonals(e) + rows(e) + columns(e) +frame (e) + hor(e) + axis(e) + value(e));


        //return(goal(s) + rows(s) + columns(s) + diagonals(s) + repetivity(s)*1000) + corners(s)*10;


        
return (repetivity(s)*2+axis(s)+corners(s)/10);
        //return (zeros(s) + repetivity(s)*2 + axis(s) + corners(s)/10 + rows(s) + columns(s) + diagonals(s));
       //return (100*repetivity(e) + goal(e) + cross(e) + frame(e));    
    }
}
