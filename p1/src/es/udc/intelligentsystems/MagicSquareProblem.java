package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.SearchProblem;

import java.util.*;

public class MagicSquareProblem extends SearchProblem {
    public static class MagicSquareState extends State {
        
        //ArrayList chosen to ease the implementation of a generic solution that
        //allows dynamic growing (n can be calculated easily)

        public ArrayList<Integer> values;
        public int n = nValue(values);

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
            for (int i = values.size() - 1; i >= 0; i--) {
                int num = values.get(i);
                sb.append(num);
            }
            String result = sb.toString();
            return result;
        }

        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MagicSquareState that = (MagicSquareState) o;

            return that.values == values;
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

        @Override
        public boolean isApplicable (State st){
            return true;
        }

        @Override
        public State applyTo(State st){
            MagicSquareState state = (MagicSquareState) st;
            int currentValue = state.values.get(position);
            if (currentValue == 0)
                state.values.set(position, newValue);
            return state;
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

    public Action[] actions(State st){
        MagicSquareState state = (MagicSquareState) st;
        int aux_value1 = state.n * state.n;
        int aux_value2 = state.n * state.n;
        ArrayList<Action> acts = new ArrayList<Action>();
        while (aux_value1 <= 0){
            while (aux_value2 <= 0){
                MagicSquareAction act = new MagicSquareAction(aux_value1, aux_value2);
                acts.add(act);
                aux_value2--;
            }
            aux_value1--;
        }
        Action[] arrayActs = new Action[acts.size()];
        acts.toArray(arrayActs);
        return arrayActs;
    }

    @Override
    public boolean isGoal(State st){
        MagicSquareState state = (MagicSquareState) st;
        int result = ((state.n * (state.n * state.n + 1))/2);
        int n = state.n;
        int i = 0;
        int auxVal;
        int auxValD = 0;
        int auxPos;

        ArrayList<Boolean> bValues = new ArrayList<Boolean>();

        while (i < (n*n)){
            
            auxVal = 0;
            auxPos = 0;
            
            while (auxPos < n) {
                //horizontal values//
                auxVal = auxVal + state.values.get((i/n) + auxPos);
                auxPos++;
            }  
            bValues.add(auxVal == result);
            auxVal = 0;
            auxPos = 0;

            while (auxPos < n) {
                //vertical values//
                auxVal = auxVal + state.values.get((i%n) + n * auxPos);
                auxPos++;
            }
            
            bValues.add(auxVal == result);
            auxVal = 0;

            auxValD = auxValD + (state.values.get(i));

            i = i + n + 1;
        }
        bValues.add(auxValD == result);
        return true;
    }   
}
