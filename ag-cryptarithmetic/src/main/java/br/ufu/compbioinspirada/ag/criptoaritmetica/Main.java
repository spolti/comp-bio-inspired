package br.ufu.compbioinspirada.ag.criptoaritmetica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Input;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Item;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Population;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Report;
import br.ufu.compbioinspirada.ag.criptoaritmetica.selection.Tournament;

public class Main {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        Input input = new Input();
        input.setSizeOfPopulation(500);
        input.setWord1("coca");
        input.setWord2("cola");
        input.setResult("oasis");
        input.setCrossoverRate(0.8);
        input.setMutationRate(0.05);
        input.setGenerations(50);
        input.setGenerationInterval(0.4);
        System.out.print("\n\nInputs: " + input + "\n");

        int numberOfExecutions = 200 ;
        int numberOfExecutionsCounter = 1;



//        while (numberOfExecutionsCounter <= numberOfExecutions) {
//            // ##ROULETTE BEGINS
//            System.out.println("\n\nExecution " + numberOfExecutionsCounter);
//            boolean hasEneded = false;
//            boolean print = false;
//            Population population = new Population();
//            population.setPrint(print);
//            // The generatePopulation does:
//            // - generate the population
//            // - the first item on the array cannot be 0
//            // - fitness (maximization result)
//            // - and, if any generated item has the result, stop it.
//            hasEneded = population.generatePopulation(input);
//            if (hasEneded) {
//                System.exit(0);
//            }
//
//            int counter = 1;
//            while (counter <= input.getGenerations()) {
//                // perform new tests on the children
//                System.out.println("\n##### generation " + counter);
//                // Generated the Roulette
//                // it will return the generated children from each selected parent based on its fitness
//                Roulette roulette = new Roulette();
//
//                // the roulette returns the children from the selected fathers with the best fathers from the
//                // current generation
//                HashSet<int[]> children = roulette.spin(population, input);
//                ArrayList<Item> generatedChildren = new ArrayList<>();
//
//                Item solution = new Item();
//                Item bestSolution = new Item();
//                int resultGreaterThan90percent = 0;
//                int resultGreaterThan95percent = 0;
//                int resultGreaterThan99percent = 0;
//                //for (int i = 0; i < children.size(); i++) {
//                for (int[] values : children) {
//                    Item item = Helper.populateItem(input, values, print);
//                    generatedChildren.add(item);
//
//                    if (item.getFitness() > 90000 && item.getFitness() < 95000 && item.getFitness() < 99000) {
//                        resultGreaterThan90percent++;
//                    }
//                    if (item.getFitness() > 95000 && item.getFitness() < 99000) {
//                        resultGreaterThan95percent++;
//                    }
//                    if (item.getFitness() > 99000) {
//                        resultGreaterThan99percent++;
//                    }
//
//                    // save the generated children in the current population
//                    if (Helper.hasEnded(item.getFitness())) {
//                        solution = item;
//                        System.out.println("Found result " + item);
//                        hasEneded = true;
//                    }
//                    if (bestSolution.getFitness() < item.getFitness()) {
//                        bestSolution = item;
//                    }
//                }
//
//                Report report = new Report(counter, solution, bestSolution, input, resultGreaterThan90percent,
//                                           resultGreaterThan95percent, resultGreaterThan99percent, population.getItems());
//                if (hasEneded) {
//                    System.out.println(report);
//                    System.out.println("Solution found in: " + (System.currentTimeMillis() - start) + "ms");
//                    System.out.println("############ End generation " + counter);
//                    System.out.println("\n\nExecution time: " + (System.currentTimeMillis() - start) + "ms - [Execution loop: " + numberOfExecutionsCounter + "]");
//                    System.exit(0);
//                }
//
//                // clean the population according, strongest ones survives.
//                population.applyGenerationInterval(input.getGenerationInterval(), generatedChildren);
//
//                System.out.println(report);
//                counter++;
//            }
//
//            numberOfExecutionsCounter++;
//        // ##ROULETTE ENDS
//        }


        while (numberOfExecutionsCounter <= numberOfExecutions) {
            // ## TOURNAMENT BEGINS
            System.out.println("\n\nExecution " + numberOfExecutionsCounter);
            boolean hasEneded = false;
            boolean print = false;
            Population population = new Population();
            population.setPrint(print);
            // The generatePopulation does:
            // - generate the population
            // - the first item on the array cannot be 0
            // - fitness (maximization result)
            // - and, if any generated item has the result, stop it.
            hasEneded = population.generatePopulation(input);
            if (hasEneded) {
                System.exit(0);
            }

            System.out.println(Arrays.asList(population.getItems() ));
            System.exit(0);

            int counter = 1;
            while (counter <= input.getGenerations()) {
                // perform new tests on the children
                System.out.println("\n##### generation " + counter);
                // Start the Tournament for this generation
                // it will return the generated children from each selected parent based on its fitness
                Tournament tournament = new Tournament();
                // the roulette returns the children from the selected fathers with the best fathers from the
                // current generation
                HashSet<int[]> children = tournament.go(population, input);

                ArrayList<Item> generatedChildren = new ArrayList<>();

                Item solution = new Item();
                Item bestSolution = new Item();
                int resultGreaterThan90percent = 0;
                int resultGreaterThan95percent = 0;
                int resultGreaterThan99percent = 0;
                for (int[] values : children) {
                    Item item = Helper.populateItem(input, values, print);
                    generatedChildren.add(item);

                    if (item.getFitness() > 90000 && item.getFitness() < 95000 && item.getFitness() < 99000) {
                        resultGreaterThan90percent++;
                    }
                    if (item.getFitness() > 95000 && item.getFitness() < 99000) {
                        resultGreaterThan95percent++;
                    }
                    if (item.getFitness() > 99000) {
                        resultGreaterThan99percent++;
                    }

                    // save the generated children in the current population
                    if (Helper.hasEnded(item.getFitness())) {
                        solution = item;
                        System.out.println("Found result " + item);
                        hasEneded = true;
                    }
                    if (bestSolution.getFitness() < item.getFitness()) {
                        bestSolution = item;
                    }
                }
                Report report = new Report(counter, solution, bestSolution, input, resultGreaterThan90percent,
                                           resultGreaterThan95percent, resultGreaterThan99percent, population.getItems());
                if (hasEneded) {
                    System.out.println(report);
                    System.out.println("Solution found in: " + (System.currentTimeMillis() - start) + "ms");
                    System.out.println("############ End generation " + counter);
                    System.out.println("\n\nExecution time: " + (System.currentTimeMillis() - start) + "ms - [Execution loop: " + numberOfExecutionsCounter + "]");
                    System.exit(0);
                }

                // clean the population accordingly, strongest ones survives.
                population.applyGenerationInterval(input.getGenerationInterval(), generatedChildren);

                System.out.println(report);
                counter++;
                System.out.println(population.getItems().size());
            }
            numberOfExecutionsCounter++;
            // ## TOURNAMENT ENDS
        }


        System.out.println("\n\nExecution time: " + (System.currentTimeMillis() - start) + "ms - [Execution loop: " + (numberOfExecutionsCounter - 1) + "]");
    }
}
