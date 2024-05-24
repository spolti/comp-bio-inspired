package br.ufu.compbioinspirada.ag.criptoaritmetica.selection;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;

import br.ufu.compbioinspirada.ag.criptoaritmetica.Crossover;
import br.ufu.compbioinspirada.ag.criptoaritmetica.Helper;
import br.ufu.compbioinspirada.ag.criptoaritmetica.Mutation;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Input;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Population;

/**
 * tournament selects two aleatory fathers and perform the crossover and mutation
 */
public class Tournament {

    private Logger log = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    public HashSet<int[]> go(Population pop, Input input) {
        Random r = new Random();
        HashSet<int[]> generatedChildren = new HashSet<>();

        int sizeOfNewGeneration = Helper.rafflePopulation(input.getSizeOfPopulation(), input.getCrossoverRate());

        for (int i = 0; i < sizeOfNewGeneration; i++) {
            int candidateFather1 = r.nextInt(pop.getItems().size());
            int[] father1 = pop.getItems().get(candidateFather1).getItem();

            int[] father2;
            while (true) {
                int candidateFather2 = r.nextInt(pop.getItems().size());
                // father 1 and father 2 cannot be the same
                if (pop.getItems().get(candidateFather1).getItem() != pop.getItems().get(candidateFather2).getItem()) {
                    father2 = pop.getItems().get(candidateFather2).getItem();
                    break;
                } else {
                    log.fine("Father 2 equal to father 1, trying another one");
                }
            }

            Crossover crossover = new Crossover();
//             ArrayList<int[]> tempGeneratedChildren = crossover.cyclic(father1, father2);
//            for (int j = 0; j < tempGeneratedChildren.size(); j++) {
//                // Mutate
//                int[] mutatedArray = Mutation.mutate(tempGeneratedChildren.get(j), input.getMutationRate());
//                generatedChildren.add(mutatedArray);
//
//                if (generatedChildren.size() == sizeOfNewGeneration) {
//                    System.out.println("New generation reached " + input.getCrossoverRate() + "percent, stopping generation. new population size: " + generatedChildren.size());
//                    return generatedChildren;
//                }
//            }

            ArrayList<int[]> tempGeneratedChildren = crossover.pmx(father1, father2);
            System.out.println("asdasdsa generatedChildren " + generatedChildren.size());
            for (int j = 0; j < tempGeneratedChildren.size(); j++) {
                // Mutate
                int[] mutatedArray = Mutation.mutate(tempGeneratedChildren.get(j), input.getMutationRate());
                generatedChildren.add(mutatedArray);

                if (generatedChildren.size() == sizeOfNewGeneration) {
                    System.out.println("New generation reached " + input.getCrossoverRate() + "percent, stopping generation. new population size: " + generatedChildren.size());
                    return generatedChildren;
                }
            }
        }
        // at this point it should never be called
        System.out.println("NEVER TOURNAMENT");
        return generatedChildren;
    }
}
