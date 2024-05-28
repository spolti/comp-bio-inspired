package br.ufu.compbioinspirada.ag.criptoaritmetica.selection;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;

import br.ufu.compbioinspirada.ag.criptoaritmetica.Crossover;
import br.ufu.compbioinspirada.ag.criptoaritmetica.Helper;
import br.ufu.compbioinspirada.ag.criptoaritmetica.Mutation;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Input;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Population;

public class Roulette {

    private Logger log = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    public HashSet<int[]> spin(Population pop, Input input) {
        int fit[];
        int totalFitness = 0;
        int[] rouletteTotal = new int[]{};
        int[] result;
        HashSet<int[]> generatedChildren = new HashSet<>();
        Random r = new Random();

        // defines how many new children will be generated per generation
        int sizeOfNewGeneration = Helper.rafflePopulation(input.getSizeOfPopulation(), input.getCrossoverRate());

        for (int i = 0; i < pop.getItems().size(); i++) {

            int evaluation = Math.divideExact(pop.getItems().get(i).getFitness(), 1000);

            totalFitness += evaluation;
            fit = new int[evaluation];
            // fill the array with the father fitness
            Arrays.fill(fit, i);

            int aLen = rouletteTotal.length;
            int bLen = fit.length;
            result = new int[aLen + bLen];
            System.arraycopy(rouletteTotal, 0, result, 0, aLen);
            System.arraycopy(fit, 0, result, aLen, bLen);
            rouletteTotal = result;
            // debug the generated roulette
            log.fine(Arrays.toString(pop.getItems().get(i).getItem()));
        }


        // spin the Roulette
        for (int i = 0; i < sizeOfNewGeneration; i++) {

            int candidateFather1 = r.nextInt(totalFitness);
            int[] father1 = pop.getItems().get(rouletteTotal[candidateFather1]).getItem();

            int[] father2;
            while (true) {
                int candidateFather2 = r.nextInt(totalFitness);
                // father 1 and father 2 cannot be the same
                if (pop.getItems().get(rouletteTotal[candidateFather1]).getItem() != pop.getItems().get(rouletteTotal[candidateFather2]).getItem()) {
                    father2 = pop.getItems().get(rouletteTotal[candidateFather2]).getItem();
                    break;
                } else {
                    log.fine("Father 2 equal to father 1, trying another one");
                }
            }

            Crossover crossover = new Crossover();
            ArrayList<int[]> tempGeneratedChildren = crossover.cyclic(father1, father2);
            for (int j = 0; j < tempGeneratedChildren.size(); j++) {

                // Mutate
                int []mutatedArray = Mutation.mutate(tempGeneratedChildren.get(j), input.getMutationRate());
                generatedChildren.add(mutatedArray);

                if (generatedChildren.size() == sizeOfNewGeneration) {
                    System.out.println("New generation reached " + input.getCrossoverRate() + "percent, stopping generation. new population size: " + generatedChildren.size());
                    return generatedChildren;
                }
            }

        }
        // at this point it should never be called
        System.out.println("NEVER ROULETTE");
        return generatedChildren;
    }
}
