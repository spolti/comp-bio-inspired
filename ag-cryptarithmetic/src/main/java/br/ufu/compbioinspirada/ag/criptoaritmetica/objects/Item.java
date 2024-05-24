package br.ufu.compbioinspirada.ag.criptoaritmetica.objects;

import java.util.Arrays;
import java.util.LinkedHashMap;

import br.ufu.compbioinspirada.ag.criptoaritmetica.Helper;

/**
 * Holds the item information which contains the following information:
 * - the array with integer values corresponding to the input words
 * - hods the integer values for each input word
 * - holds the fitness calculation
 * - holds a map that maps the letter to the corresponding itenger
 *
 */
public class Item {

    private int []item;
    private LinkedHashMap<String, Integer> letterWithNumbers;
    private int input1Int;
    private int input2Int;
    private int inputResultInt;
    /**
     *  holds the result of the maximization:
     */
    private int fitness;

    public Item() {

    }

    public Item(int[] item, LinkedHashMap<String, Integer> letterWithNumbers) {
        this.item = item;
        this.letterWithNumbers = letterWithNumbers;
    }

    public int[] getItem() {
        return item;
    }

    public void setItem(int[] item) {
        this.item = item;
    }

    public LinkedHashMap<String, Integer> getLetterWithNumbers() {
        return letterWithNumbers;
    }

    public int getInput1Int() {
        return input1Int;
    }

    public void setInput1Int(int input1Int) {
        this.input1Int = input1Int;
    }

    public int getInput2Int() {
        return input2Int;
    }

    public void setInput2Int(int input2Int) {
        this.input2Int = input2Int;
    }

    public int getInputResultInt() {
        return inputResultInt;
    }

    public void setInputResultInt(int inputResultInt) {
        this.inputResultInt = inputResultInt;
    }

    /**
     * returns the maximized value from the inputs
     * @return the maximized value from the inputs
     */
    public int getFitness() {
        if (input1Int <= 0 || input1Int <= 0 || inputResultInt <= 0) {
            return 0;
        } else {
            this.fitness = Helper.maximization(input1Int, input2Int, inputResultInt);
        }
        return fitness;
    }

    @Override
    public String toString() {
        if (fitness == 0) {
            return "Not Found\n";
        }
        return "Items{" +
                "item=" + Arrays.toString(item) +
                ", letterWithNumbers=" + letterWithNumbers +
                ", input1Int=" + input1Int +
                ", input2Int=" + input2Int +
                ", inputResultInt=" + inputResultInt +
                ", fitness=" + fitness + "}\n";
    }
}
