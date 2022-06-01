package com;

public class Stats {

    long backtrack;
    long node;
    long fails;
    long solutions;
    float time;
    String name;
    public Stats(String name, long backtrack, long node, float time, long fails, long solutions) {
        this.backtrack = backtrack;
        this.node = node;
        this.time = time;
        this.fails = fails;
        this.solutions = solutions;
        this.name = name;
    }

    public Stats() {
    }

    @Override
    public String toString() {
        return name + " { " +
                "backtrack = " + backtrack +
                ", node = " + node +
                ", fails = " + fails +
                ", solutions = " + solutions +
                ", time = " + time +
                " }";
    }

}
