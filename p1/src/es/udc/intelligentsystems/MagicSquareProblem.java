package es.udc.intelligentsystems;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.SearchProblem;

import java.util.*;

public class MagicSquareProblem extends SearchProblem {
    public static class MagicSquareState extends State {
        
        //ArrayList chosen to ease the implementation of a generic solution that
        //allows dynamic growing (n can be calculated easily)

        public ArrayList<Integer> values;
        public int n;

        private int nValue(ArrayList<Integer> list){
            if (list.isEmpty())
                return 0;
            else {
                int result = (int) Math.sqrt(list.size());
                return result;
            }
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= values.size() - 1; i++) {
                int num = values.get(i);
                sb.append(num);
                if (i != values.size()-1)
                    sb.append(",");
            }
            String result = sb.toString();
            return result;
        }

        //equals checks the value of the states
        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MagicSquareState that = (MagicSquareState) o;

            return that.values.equals(values);
        }

        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (Integer i : values) {  
                int val = i.hashCode();  
                sb.append(val);
            }
            String str = sb.toString();
            int num = Integer.parseInt(str);
            return num;
        }
        //constructor
        public MagicSquareState(ArrayList<Integer> list){
            this.values = list;
            this.n = nValue(list);
        }
    }

    public static class MagicSquareAction extends Action {
    
        public int position;
        public int newValue;

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("CHANGING VALUE AT POSITION ");
            sb.append(position);
            sb.append(" TO ");
            sb.append(newValue);
            String result = sb.toString();
            return result;
        }


        //all possible actions are applicable since they cannot have non-applicable values
        //this is checked on the possible actions
        @Override
        public boolean isApplicable (State st){
            return true;
        }

        @Override
        public State applyTo(State st){
            MagicSquareState state = (MagicSquareState) st;

            ArrayList<Integer> newValues = new ArrayList<Integer>(state.values);

            if (newValues.get(position) == 0)
                newValues.set(position, newValue);

            return new MagicSquareState(newValues);
        }

        //constructor
        public MagicSquareAction(int p, int v) {
            this.position = p;
            this.newValue = v;
        }
    }

    private Action[] actionList;

    public MagicSquareProblem(MagicSquareState initialState){
        super (initialState);
        actionList = actions(initialState);
    }
    
    //this auxiliar function discards some possible actions that cannot lead to a solution 
    //in order to trim the node tree
    public boolean actions_aux (MagicSquareState state){
        int n = state.n, result = 0;
        int givenResult = (n*n*n+n)/2;

        for (int l = 0; l < n; l++) {
            //rows
            for (int k = l*n; k < l*n + n; k++) { result += state.values.get(k); }
            if (result > givenResult) return false;
            result = 0;
            //columns
            for (int k = l; k < n*n; k+=n) { result += state.values.get(k); }
            if (result > givenResult) return false;
            result = 0;
        }

        //right diagonal
        for (int l = 0; l < n*n; l += n+1) { result += state.values.get(l); }
        if (result > givenResult) return false;
        result = 0;

        //left diagonal
        for (int l = n-1; l < n*n-1; l += n-1) { result += state.values.get(l); }
        return result <= givenResult;
    }

    //here some valid, yet non-leading to the solution actions are discarded (checked by applicable)
    public Action[] actions(State st){
        MagicSquareState state = (MagicSquareState) st;
        List<Integer> copy = new ArrayList<>(state.values);
        int aux_value1 = state.n * state.n -1;
        int aux_value2;
        boolean applicable = true;
        ArrayList<Action> acts = new ArrayList<Action>();
        
        while (aux_value1 >= 0){
            aux_value2 = state.n * state.n;
            while (aux_value2 > 0){
                MagicSquareAction act = new MagicSquareAction(aux_value1, aux_value2);
                for(int i_value : copy){
                    if(i_value == aux_value2)
                        applicable = false;
                }
                
                if((actions_aux(state))&&(applicable)&&(copy.get(aux_value1) == 0))
                    acts.add(act);
                applicable = true;
                aux_value2--;
            }
            aux_value1--;
        }

        Action[] arrayActs = new Action[acts.size()];
        acts.toArray(arrayActs);
        return arrayActs;
    }

    //auxiliar function that checks if there are numbers repeated on a given state
    public float repetivity(MagicSquareProblem.MagicSquareState s) {
        int duplicates = 0;
        List<Integer> checked = new ArrayList<>();
        for (Integer integer : s.values) {
            if (checked.contains(integer)) duplicates++;
            else checked.add(integer);
        }
        return duplicates;
    }

    @Override
    public boolean isGoal(State st){
        MagicSquareState state = (MagicSquareState) st;

        int n = state.n, result = 0;
        int givenResult = (n*n*n+n)/2;

        if (repetivity(state) != 0) return false;

        for (int l = 0; l < n; l++) {
            //rows
            for (int k = l*n; k < l*n + n; k++) { result += state.values.get(k); }
            if (result != givenResult) return false;
            result = 0;
            //columns
            for (int k = l; k < n*n; k+=n) { result += state.values.get(k); }
            if (result != givenResult) return false;
            result = 0;
        }

        //right diagonal
        for (int l = 0; l < n*n; l += n+1) { result += state.values.get(l); }
        if (result != givenResult) return false;
        result = 0;

        //left diagonal
        for (int l = n-1; l < n*n-1; l += n-1) { result += state.values.get(l); }
        return result == givenResult;
    }
}
