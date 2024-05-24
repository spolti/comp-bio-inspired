package br.ufu.compbioinspirada.celautomatons.uni;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {

        int wolframRuleNumber = 30;
        String ruleInBinary = String.format("%8s", Integer.toBinaryString(wolframRuleNumber)).replaceAll(" ", "0");
        System.out.println(ruleInBinary);
        Map<String, String> mappedRule = new LinkedHashMap<>();
        for (int i = 0; i <ruleInBinary.length(); i++) {
            mappedRule.put(String.format("%03d", Integer.parseInt(Integer.toBinaryString(i))), String.valueOf(ruleInBinary.charAt(i)));
            // rule 49 needs to print 0101101
            //System.out.println(0101101);
        }
        System.out.println("Wolfran rule: " + wolframRuleNumber + "\nRule of Transition: " + Arrays.asList(mappedRule));

//        int espacoCelular[] = new int[]{1, 0, 0, 1, 0, 0, 1, 1, 1, 0};
        int espacoCelular[] = Helper.generateCellSpace(200);
        //System.out.println(Arrays.toString(Helper.generateCellSpace(10)));
//        System.exit(0);

        // quantas vezes vamos recalcular a vizinhança
        int tempo = 200;

        int prevIndex = 0;
        int nextIndex = 0;
        int history[][] = new int[tempo + 1][espacoCelular.length];

        for (int time = 0; time <= tempo; time++) {
//            System.out.println("T[" + time + "] = " + Arrays.toString(espacoCelular));
            int[] timeResult = new int[espacoCelular.length];
            for (int j = 0; j < espacoCelular.length; j++) {
                if (j == 0) {
                    // faz o loop no array, pega o ultimo index do array
                    prevIndex = espacoCelular.length - 1;
                } else {
                    prevIndex = j - 1;
                }
                if (j == espacoCelular.length - 1) {
                    // faz o loop no array, pega o primeiro index do array
                    nextIndex = 0;
                } else {
                    nextIndex = j + 1;
                }

                // get the values from the current index
                // eg, for the index 1 gets the radix: the index before and the index after.
                String prev = String.valueOf(espacoCelular[prevIndex]);
                String current = String.valueOf(espacoCelular[j]);
                String next = String.valueOf(espacoCelular[nextIndex]);
                String value = prev + current + next;

                timeResult[j] = Integer.parseInt(mappedRule.get(value));

                // keep track of the executions to draw the image
                history[time][j] = espacoCelular[j];
            }
            // the current cell space is now the timeResult with the result of
            // the previous turn.
            espacoCelular = timeResult;
        }

        // print the history matrix
        BufferedImage image = new BufferedImage(201, 201, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < history.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < history[i].length; j++) { //this equals to the column in each row.
                if (history[i][j] == 0) {
                    image.setRGB(j, i, 0x00000000);
                } else {
                    image.setRGB(j, i, 0xffffffff);
                }
            }
            //change line on console as row comes to end in the matrix.
        }
        File file = new File("outimage.png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
