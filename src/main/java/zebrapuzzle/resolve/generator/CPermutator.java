package zebrapuzzle.resolve.generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Rermutaror creating permutation sequence
 * User: mf
 * Date: 09.06.12
 * Time: 17:44
 */
public class CPermutator {

    public static List<int[]> generate(int numberOfElements) {
        return generate(new ArrayList<int[]>(factorial(numberOfElements)),
                createInitialPermutation(numberOfElements), numberOfElements);
    }

    private static int factorial(int number) {
        int factorial = 1;
        for (int index = 2; index <= number; index++) {
            factorial *= index;
        }
        return factorial;
    }

    private static int[] createInitialPermutation(int numberOfElements) {
        int[] permutation = new int[numberOfElements];
        for (int index = 0; index < numberOfElements; index++) {
            permutation[index] = index;
        }
        return permutation;
    }

    private static List<int[]> generate(List<int[]> permutations, int[] currentPermutation, int numberOfElements) {
        if (numberOfElements == 1) {
            permutations.add(currentPermutation.clone());
            return permutations;
        }

        for (int index = 0; index < numberOfElements; index++) {
            generate(permutations, currentPermutation, numberOfElements - 1);
            swap(currentPermutation, numberOfElements % 2 == 0 ? 0 : index, numberOfElements - 1);
        }
        return permutations;
    }

    private static void swap(int[] permutation, int index1, int index2) {
        int temp = permutation[index1];
        permutation[index1] = permutation[index2];
        permutation[index2] = temp;
    }


}
