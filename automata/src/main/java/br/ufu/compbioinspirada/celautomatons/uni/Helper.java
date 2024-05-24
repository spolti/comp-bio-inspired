package br.ufu.compbioinspirada.celautomatons.uni;

import java.util.Random;

import br.ufu.compbioinspirada.celautomatons.copiado.Celula;

public abstract class Helper {

    /**
     * generates the automata space cell
     * @param size
     * @return
     */
    public static int[] generateCellSpace(int size) {
        int[] g = new int[size];
        for (int i = 0; i < size; i++) {
//            g[i] = (int) (Math.random() + 0.5);
            double num = Math.random();
            if (num > 0.5) {
                g[i] = 1;
            } else {
                g[i] = 0;
            }
        }
        return g;
    }

}
