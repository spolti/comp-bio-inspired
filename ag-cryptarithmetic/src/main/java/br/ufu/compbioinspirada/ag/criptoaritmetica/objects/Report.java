package br.ufu.compbioinspirada.ag.criptoaritmetica.objects;

import java.util.ArrayList;
import java.util.LinkedList;

public class Report {
    private int generation;
    private Item solution;
    private Item bestSolution;
    private Input input;
    private int resultGreaterThan90percent;
    private int resultGreaterThan95percent;
    private int resultGreaterThan99percent;
    private LinkedList<Item> population;

    public Report(int generation, Item solution, Item bestSolution, Input input, int resultGreaterThan90percent,
                  int resultGreaterThan95percent, int resultGreaterThan99percent, LinkedList<Item> population) {
        this.generation = generation;
        this.solution = solution;
        this.bestSolution = bestSolution;
        this.input = input;
        this.resultGreaterThan90percent = resultGreaterThan90percent;
        this.resultGreaterThan95percent = resultGreaterThan95percent;
        this.resultGreaterThan99percent = resultGreaterThan99percent;
        this.population = population;
    }

    @Override
    public String toString() {
        return "Generation Report:\n" +
                "input=" + input + "\n" +
                "solution=" + solution +
                "bestSolution=" + bestSolution +
                "resultGreaterThan90percent=" + resultGreaterThan90percent +
                ", resultGreaterThan95percent=" + resultGreaterThan95percent +
                ", resultGreaterThan99percent=" + resultGreaterThan99percent;
    }
}
