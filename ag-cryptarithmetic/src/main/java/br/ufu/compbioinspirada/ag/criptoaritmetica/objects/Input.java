package br.ufu.compbioinspirada.ag.criptoaritmetica.objects;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * Holds the inputs information
 */
public class Input {

    private int sizeOfPopulation;
    private String word1;
    private String word2;
    private String result;
    private int generations;
    private double crossoverRate;
    private double mutationRate;
    // controls the percentage of the population that will survive in the next generation
    private double generationInterval;

    public void setSizeOfPopulation(int sizeOfPopulation) {
        this.sizeOfPopulation = sizeOfPopulation;
    }

    public int getSizeOfPopulation() {
        return sizeOfPopulation;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public String mergedInputs() {
        return word1 + word2 + result;
    }

    public double getGenerationInterval() {
        return generationInterval;
    }

    public void setGenerationInterval(double generationInterval) {
        this.generationInterval = generationInterval;
    }

    /**
     * Merge the inputs into a single array with no duplicated letters
     *
     * @return
     */
    public LinkedHashSet<String> getMergedUniqElements() {
        return this.mergedInputs()
                .chars()
                .mapToObj(c -> Character.toString(c))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public String toString() {
        return "Input{" +
                "sizeOfPopulation=" + sizeOfPopulation +
                ", word1='" + word1 + '\'' +
                ", word2='" + word2 + '\'' +
                ", result='" + result + '\'' +
                ", generations=" + generations +
                ", crossoverRate=" + crossoverRate +
                ", mutationRate=" + mutationRate +
                ", generationInterval=" + generationInterval +
                '}';
    }
}
