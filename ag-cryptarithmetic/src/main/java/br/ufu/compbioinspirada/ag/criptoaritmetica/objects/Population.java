package br.ufu.compbioinspirada.ag.criptoaritmetica.objects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import br.ufu.compbioinspirada.ag.criptoaritmetica.Helper;

public class Population {

    private boolean print;
    private LinkedList<Item> items;

    public Population() {
        this.items = new LinkedList<>();
    }

    public Population(LinkedList<Item> items) {
        this.items = items;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    /**
     * This method will:
     * - elect the best generated sons
     * - ordered by the fitness
     * - calculate how may items in the population will survive
     * - get the best children and replace with the worst items in the current population.
     * - fathers must also be replaced by the worst fitness to the best ones.
     *
     * @param generationInterval
     */
    public void applyGenerationInterval(double generationInterval, ArrayList<Item> children) {
        int numberOfReplacements = Math.toIntExact(Math.round(this.items.size() * generationInterval));

//         log.fine("number of replacements in the current population" + numberOfReplacements);

        List<Item> orderedPopulation = this.items.stream()
                .sorted(Comparator.comparing(Item::getFitness).reversed())
                .collect(Collectors.toList());
        List<Item> orderedChildren = children.stream()
                .sorted(Comparator.comparing(Item::getFitness).reversed())
                .collect(Collectors.toList());

        for (int i = 1; i <= numberOfReplacements; i++) {
            if (orderedChildren.size() > i) {
//            log.fine("Replacing  " + orderedPopulation.get(orderedPopulation.size() - i));
                orderedPopulation.remove(orderedPopulation.size() - i);

//           log.fine("With  "+ orderedChildren.get(i-1));
                orderedPopulation.add(orderedChildren.get(i - 1));
            }
        }
        this.items = new LinkedList<>(orderedPopulation);
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean generatePopulation(Input input) {
        Random r = new Random();

        int[] values;
        LinkedHashSet<Integer> tempValues = null;

        for (int i = 0; i < input.getSizeOfPopulation(); i++) {
            tempValues = new LinkedHashSet<>();
            for (int j = 0; j < input.getMergedUniqElements().size(); j++) {
                while (true) {
                    //first character cannot be 0
                    if (j == 0) {
                        while (true) {
                            int rand = r.nextInt(10);
                            if (rand > 0) {
                                tempValues.add(rand);
                                break;
                            }
                        }
                        break;
                    } else {
                        int rand = r.nextInt(10);
                        if (!tempValues.contains(rand)) {
                            tempValues.add(rand);
                            break;
                        }
                    }
                }
            }
            values = tempValues.stream().mapToInt(Integer::intValue).toArray();
            Item item = Helper.populateItem(input, values, print);
            items.add(item);
            if (Helper.hasEnded(item.getFitness())) {
                return true;
            }
            // print the population
//            if (print) {
//                System.out.println("\n##########################");
//                System.out.println(input.getMergedUniqElements() + " = " + item.getLetterWithNumbers());
//                System.out.println(input.getWord1() + " = " + item.getInput1Int());
//                System.out.println(input.getWord2() + " = " + item.getInput2Int());
//                System.out.println(input.getResult() + " = " + item.getInputResultInt());
//                int variation = (item.getInput1Int() + item.getInput2Int()) - item.getInputResultInt();
//                System.out.println("Variation: (" + input.getWord1() + " + " + input.getWord2() + " - " + input.getResult() + ") = " + variation);
//                System.out.println("Fitness (Maximization): 100.000 - abs(" + input.getWord1() + " + " + input.getWord2() + " - " + input.getResult() + ") = " + item.getFitness());
//            }
        }

        return false;
    }

    //############
}
