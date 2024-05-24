package br.ufu.compbioinspirada.ag.criptoaritmetica;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Mutate the given item
 *
 * The mutation will exchange Xi items between themselves.
 */
public class Mutation {

    private static Set<Integer> v = new HashSet<>();

    /**
     * exchange the position of the elements based on the mutation rate, minimal mutation is 1
     * @param arr
     * @param mutationRate mutation rate type double, 0.03 for example.
     * @return the mutated array
     */
    public static int[] mutate(int []arr, double mutationRate) {
        Random r = new Random();
        v = new HashSet<>();
        int positionsToExchange = Math.toIntExact(Math.round(mutationRate * arr.length));

        // at least one position needs to be exchanged
        if (positionsToExchange == 0) {
            positionsToExchange = 1;
        }

        for (int i = 0; i < positionsToExchange ; i++) {
            while (true) {
                int index1 = r.nextInt(arr.length);
                int index2 = r.nextInt(arr.length);
                while (index1 == index2) {
                    index2 = r.nextInt(arr.length);
                }
                //System.out.println("exchanging positions between indexes " + index1 + " and " + index2);
                if (!visited(index1)) {
//                    log.fine(Arrays.toString(arr));
                    int tempHolder = arr[index1];
                    arr[index1] = arr[index2];
                    arr[index2] = tempHolder;
//                    log.fine(Arrays.toString(arr));
                    break;
                }
            }
        }
        return arr;
    }

    private static boolean visited (int index) {
        if (v.contains(index)) {
//            log.fine("index " + index + " visited.");
            return true;
        } else {
//            log.fine("index " + index + " NOT visited.");
            v.add(index);
            return false;
        }
//        return false;
    }

}
