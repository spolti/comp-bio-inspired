package br.ufu.compbioinspirada.ag.criptoaritmetica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Crossover {

    /**
     * Defines the crossover point based on the array size
     *
     * @param size the size of the array
     * @return the crossover point, the location where the array will be split to join the fathers
     */
    private int crossOverPoint(int size) {
        return size % 2 == 0 ? size / 2 : (size / 2) + 1;
    }

    /**
     * Perform the crossover with the two inputs and produces two sons
     * The sons cannot have duplicated values
     * <p>
     * Cyclic Crossover
     * divide the array by 2 and switch the first the arrays in this way:
     * 1 2 3 4 0 9 8 7
     * 5 6 7 8 4 3 2 1
     * <p>
     * After Crossover:
     * 1 2 3 4 4 3 2 1
     * 5 6 7 8 0 9 8 7
     * <p>
     * There can be no duplicates, so the duplication function will make sure that the duplicates
     * are switched of between to two generated children
     *
     * @param a father 1
     * @param b father 2
     */
    public ArrayList<int[]> cyclic(int[] a, int[] b) {

        ArrayList<int[]> children = new ArrayList<>();

        int crossover = crossOverPoint(a.length);
        int[] son1 = new int[a.length];
        int[] son2 = new int[a.length];

//        System.out.println("\n\n################ fathers");
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
//        System.out.println("############################");

        // perform the crossover
        for (int i = 0; i < a.length; i++) {
            son1[i] = i < crossover ? a[i] : b[i];
            son2[i] = i >= crossover ? a[i] : b[i];
        }

//        System.out.println("################ merged sons");
//        System.out.println(Arrays.toString(son1));
//        System.out.println(Arrays.toString(son2));
//        System.out.println("############################");

        // TODO use the normalize func here.
        // Normalize sons, there could not be duplicated values
        // find duplicates
        // Use Set as it does not allow duplicates
        Set<Integer> visited = new HashSet<>();

        boolean hasCycleEnded = false;
        while (!hasCycleEnded) {
            int dupIdx = duplicate(son1, visited);
            if (dupIdx >= 0) {
                int holder = son1[dupIdx];
                son1[dupIdx] = son2[dupIdx];
                son2[dupIdx] = holder;
                visited.add(dupIdx);
            } else {
//                System.out.println("no duplicates");
//                System.out.println("################ merged sons after cycle");
//                System.out.println(Arrays.toString(son1));
//                System.out.println(Arrays.toString(son2));
//                System.out.println("############################");
                children.add(son1);
                children.add(son2);
                hasCycleEnded = true;
            }
        }
        // return sons.
        return children;
    }

    // TODO create PMX Crossover

    /**
     * Perform the crossover with the two inputs and produces two sons
     * The sons cannot have duplicated values
     * <p>
     * PMX Crossover
     * selects one section and switch over the entire section between the generated children
     *
     * @param a father 1
     * @param b father 2
     */
    public ArrayList<int[]> pmx(int[] a, int[] b) {
        ArrayList<int[]> children = new ArrayList<>();

        // to select the point of pmx, get the half of the array and select the start point.
        // and from the second half, get the end point, everything in the between will be switched.
        int middle = crossOverPoint(a.length);
        int firstPoint = Helper.randomBetween(0, middle);
        int secondPoint = Helper.randomBetween(middle, a.length);

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        System.out.println("size " + a.length);

        System.out.println("first point; 0 " + (middle - 1) + " -> " + firstPoint);
        System.out.println("second point; " + (middle) + " " + (a.length - 1) + " -> " + secondPoint);

        for (int i = firstPoint; i <= secondPoint; i++) {
            int holder = a[i];
            a[i] = b[i];
            b[i] = holder;
        }

        System.out.println("before normalize");
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        // return sons.
        children.addAll(normalize(a, b));
        System.out.println("s");
        for (int[] child : children) {
            System.out.println(Arrays.toString(child));
        }

        //System.exit(0);
        return children;
    }

    /**
     * Normalize sons, there could not be duplicated values
     * find duplicates and swap them until we have a closed cycle
     * Use Set as it does not allow duplicates
     *
     * @return both sons without duplicated items
     */
    private ArrayList<int[]> normalize(int[] a, int[] b) {
        ArrayList<int[]> children = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        boolean hasCycleEnded = false;
        while (!hasCycleEnded) {
            int dupIdx = duplicate(a, visited);
            if (dupIdx >= 0) {
                int holder = a[dupIdx];
                a[dupIdx] = b[dupIdx];
                b[dupIdx] = holder;
                visited.add(dupIdx);
            } else {
//                System.out.println("no duplicates");
//                System.out.println("################ merged sons after cycle");
//                System.out.println(Arrays.toString(a));
//                System.out.println(Arrays.toString(b));
//                System.out.println("############################");
//                children.add(a);
//                children.add(b);
                hasCycleEnded = true;
            }
        }

        // verify second array
        boolean hasCycleEndedBtoA = false;
        Set<Integer> visitedb = new HashSet<>();
        while (!hasCycleEndedBtoA) {
            int dupIdxb = duplicate(b, visitedb);
            if (dupIdxb >= 0) {
                int holder = b[dupIdxb];
                b[dupIdxb] = a[dupIdxb];
                a[dupIdxb] = holder;
                visitedb.add(dupIdxb);
            } else {
//                System.out.println("no duplicates");
//                System.out.println("################ merged sons after cycle");
//                System.out.println(Arrays.toString(a));
//                System.out.println(Arrays.toString(b));
//                System.out.println("############################");
                children.add(a);
                children.add(b);
                hasCycleEndedBtoA = true;
            }
        }

        return children;
    }

    /**
     * return -1 if no duplicates or the duplicate index in the given array
     *
     * @param a array of integers
     * @return the index of the item
     */
    private int duplicate(int[] a, Set<Integer> visited) {
        for (int i = 0; i < a.length; i++) {
            if (!visited.contains(i)) {
                for (int j = 0; j < a.length; j++) {
                    if (a[i] == a[j] && i != j) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
