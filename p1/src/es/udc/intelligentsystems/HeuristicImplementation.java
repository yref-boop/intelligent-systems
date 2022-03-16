package es.udc.intelligentsystems;

public abstract class Heuristic {

    public float value(State s){
        int wanted = (int) (s.n * ((s.n * s.n * s.n + s.n)/2));
        int actual = 0, proximity = 0;

        for (int l = 0; l < n; l++) {
            for (int k = l*n; k < l*n + n; k++) { actual += state.values.get(k); }
        }
        return floatValue(Math.abs(actual - wanted));
    }

    public float repetivity(State s) {
        int duplicates = 0;
        ArrayList<Integer> array= s.values;
        List<Integer> checked = new ArrayList<>();
        for (Integer integer : array) {
            if (checked.contains(integer)) duplicates++;
            else checked.add(integer);
        }
        return floatValue(duplicates/(array.size()));
    }

    public float evaluate(State e) {
        return (value(e) + repetivity(e));
    }
}
