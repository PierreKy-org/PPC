package com;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("N = " + n);
        ArrayList<Stats> res = new ArrayList<>();
        Model model = new Model(n + "-all-intervall");
        Model model1 = new Model(n + "-all-intervall");
        Model model2 = new Model(n + "-all-intervall");
        Model model3 = new Model(n + "-all-intervall");
        Model model4 = new Model(n + "-all-intervall");
        Model model5 = new Model(n + "-all-intervall");
        res.add(withouttable(n, model, "AC_REGIN"));
        res.add(withouttable(n, model1, "AC"));
        res.add(withouttable(n, model2, "AC_ZHANG"));
        res.add(withouttable(n, model3, "BC"));
        res.add(withouttable(n, model4, "FC"));
        res.add(withouttable(n, model5, "NEQS"));
        for(int i = 0; i < res.size(); i++){
            System.out.println(res.get(i).toString());
        }
    }


    public static Stats withouttable(int n, Model model, String consistency){
        IntVar[] vars = new IntVar[n];
        IntVar[] varsdiff = new IntVar[n-1];
        IntVar[] varsdiffabs = new IntVar[n-1];
        for(int q = 0; q < n; q++){
            vars[q] = model.intVar("n_"+q, 0, n-1);
        }
        for(int q = 0; q < n-1; q++){
            varsdiff[q] = model.intVar("diff_"+q, -n-1, n+1);
            varsdiffabs[q] = model.intVar("diffabs_"+q, 0, n+1);
        }

        for(int i  = 0; i < n-1; i++){
            model.arithm(varsdiff[i], "=", vars[i+1], "-", vars[i]).post();
            model.absolute(varsdiffabs[i], varsdiff[i]).post();
        }
        model.allDifferent(vars, consistency).post();
        model.allDifferent(varsdiffabs, consistency).post();


        ArrayList<Solution> solutions = (ArrayList<Solution>) model.getSolver().findAllSolutions();
        if(solutions != null){
            Stats st = new Stats(consistency, model.getSolver().getBackTrackCount(), model.getSolver().getNodeCount(), model.getSolver().getTimeCount(), model.getSolver().getFailCount(), model.getSolver().getSolutionCount());
            return st;
        }
        Stats st = new Stats();
        return st;
    }
}


