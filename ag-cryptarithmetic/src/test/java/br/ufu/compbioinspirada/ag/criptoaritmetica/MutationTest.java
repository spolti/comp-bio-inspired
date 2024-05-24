package br.ufu.compbioinspirada.ag.criptoaritmetica;

import org.junit.jupiter.api.Test;

public class MutationTest {

    @Test
    public void testMutate() {
        int []array = new int[]{1, 4, 5, 7, 6, 3, 2, 9};
        System.out.println(Mutation.mutate(array, 0.2));

    }


}
