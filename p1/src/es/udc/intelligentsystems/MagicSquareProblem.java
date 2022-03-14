package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.State;
import es.udc.intelligentsystems.SearchProblem;

import java.util.*;

public class MagicSquareProblem extends SearchProblem {
    public static class MagicSquareState extends State {
        
        //ArrayList chosen to ease the implementation of a generic solution that
        //allows dynamic growing (n can be calculated easily)

        public ArrayList<Integer> MagicSquareValues;
        private int n = nValue(MagicSquareValues);

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
            for (int i = MagicSquareValues.size() - 1; i >= 0; i--) {
                int num = MagicSquareValues.get(i);
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

            return that.MagicSquareValues == MagicSquareValues;
        }

        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (Integer i : MagicSquareValues) {  
                int val = i.hashCode();  
                sb.append(val);
            }
            String str = sb.toString();
            int num = Integer.parseInt(str);
            return num;
        }
        //constructor
        public MagicSquareState(ArrayList<Integer> list){
            this.MagicSquareValues = list;
        }
    }

    public static class MagicSquareAction extends Action {
    
        public int position;

        @Override
        public String toString(){
            
        }

        @Override
        public boolean isApplicable (State st){
            return true;
        }

        @Override
        public State applyTo(State st){
            
        }
    }

    private Action[] actionList;

    public MagicSquareProblem(MagicSquareState initialState){
        super (initialState);
        actionList = new Action();
    }

    @Override
    public boolean isGoal(State st){
    }
}
