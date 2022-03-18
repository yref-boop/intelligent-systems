package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;

public class MainEx1 {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem vacuum = new VacuumCleanerProblem(initialState);

        SearchStrategy searcher = new GraphSearchStrategy();
        System.out.println(searcher.solve(vacuum));
    }
}
