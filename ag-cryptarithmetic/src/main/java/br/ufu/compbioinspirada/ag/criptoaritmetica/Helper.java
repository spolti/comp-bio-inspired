package br.ufu.compbioinspirada.ag.criptoaritmetica;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.logging.Logger;

import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Input;
import br.ufu.compbioinspirada.ag.criptoaritmetica.objects.Item;

public class Helper {

    private static Logger log = Logger.getLogger(Helper.class.getName());

    /**
     * Maximum value for maximization
     */
    public static int maximizationValue = 100000;

    /**
     * Maximization:
     *  as closer as the result of the formula maximizationValue - Math.abs(ax.getNumber1()+ ax.getNumber2() - ax.getResult());
     *   gets to this number, it is more likely to be closer to the result.
     * @param word1, word2, result
     * @return the maximized value
     */
    public static int maximization(int word1, int word2, int result) {
        // Math.ABS returns the absolute value from send + more - money to avoid numbers higher than 100000
        return maximizationValue - Math.abs((word1 + word2) - result);
    }

    /**
     * End condition
     */
    public static boolean hasEnded(int fitness) {
        boolean result = true ? fitness == maximizationValue : false;
        log.fine("Has ended? " + maximizationValue + " ==  " + fitness + " ? " +  result);
        return result;
    }

    /**
     * holds the result of the merged words provided as input, e.g.
     * send
     * + more
     * = money
     * will result on sendmory
     * each value from the population array should be assigned to its correspondent letter
     * E.g.:
     *  [5, 3, 7, 1, 0, 4, 8, 2] => {s=5, e=3, n=7, d=1, m=0, o=4, r=8, y=2}
     * @param {@link Population}
     */
    public static LinkedHashMap<String, Integer> assignLettersToNumbers(Input input, int[] values) {
        LinkedHashMap<String, Integer> tempResult;
        // assign each letter to its value in the mergedWords
        tempResult = new LinkedHashMap<>();
        Iterator<String> letter = input.getMergedUniqElements().iterator();
        int counter = 0;
        while (letter.hasNext()) {
            tempResult.put(letter.next(), values[counter++]);
        }
        return tempResult;
    }

    /**
     * Represents each member of the population with all values set and mapped.
     * @param input
     * @param values
     * @param print
     * @return
     */
    public static Item populateItem(Input input, int[] values, boolean print){
        LinkedHashMap<String, Integer> resultLetterValueMap = assignLettersToNumbers(input, values);
        Item item = new Item(values, resultLetterValueMap);

        // extract the values for each letter and assign to the items variables
        String num1 = "";
        String num2 = "";
        String result = "";
        //first word
        for (int w1 = 0; w1 < input.getWord1().length(); w1++) {
            num1 += resultLetterValueMap.get("" + input.getWord1().charAt(w1));
        }
        // second word
        for (int w2 = 0; w2 < input.getWord2().length(); w2++) {
            num2 += resultLetterValueMap.get("" + input.getWord2().charAt(w2));
        }
        // result word
        for (int rs = 0; rs < input.getResult().length(); rs++) {
            result += resultLetterValueMap.get("" + input.getResult().charAt(rs));
        }
        item.setInput1Int(Integer.parseInt(num1));
        item.setInput2Int(Integer.parseInt(num2));
        item.setInputResultInt(Integer.parseInt(result));

        // print the population
        if (print) {
            System.out.println("\n##########################");
            System.out.println(input.getMergedUniqElements() + " = " + item.getLetterWithNumbers());
            System.out.println(input.getWord1() + " = " + item.getInput1Int());
            System.out.println(input.getWord2() + " = " + item.getInput2Int());
            System.out.println(input.getResult() + " = " + item.getInputResultInt());
            int variation = (item.getInput1Int() + item.getInput2Int()) - item.getInputResultInt();
            System.out.println("Variation: (" + input.getWord1() + " + " + input.getWord2() + " - " + input.getResult() + ") = " + variation);
            System.out.println("Fitness (Maximization): 100.000 - abs(" + input.getWord1() + " + " + input.getWord2() + " - " + input.getResult() + ") = " + item.getFitness());
        }
        return item;
    }

    public static int rafflePopulation(int size, double percentage) {
        int sizeOfPopulation = Math.toIntExact(Math.round(size * percentage));
//        log.info("Number of the population sample(s):  " + sizeOfPopulation);
        return sizeOfPopulation;
    }

    /**
     * @param min
     * @param max
     * @return a randon number between the range
     */
    public static int randomBetween(int min, int max){
        return new Random().nextInt(max - min) + min;
    }
}
