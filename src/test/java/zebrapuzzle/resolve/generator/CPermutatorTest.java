package zebrapuzzle.resolve.generator;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CPermutatorTest {
    @Test
    public void oneElementPermutations() {
        List<int[]> expected = new LinkedList<>();
        add(expected, 0);

        assertPermutationsEqual(expected, CPermutator.generate(1));
    }

    @Test
    public void twoElementPermutations() {
        List<int[]> expected = new LinkedList<>();
        add(expected, 0, 1);
        add(expected, 1, 0);

        assertPermutationsEqual(expected, CPermutator.generate(2));
    }

    @Test
    public void threeElementPermutations() {
        List<int[]> expected = new LinkedList<>();
        add(expected, 0, 1, 2);
        add(expected, 0, 2, 1);
        add(expected, 1, 0, 2);
        add(expected, 1, 2, 0);
        add(expected, 2, 0, 1);
        add(expected, 2, 1, 0);

        assertPermutationsEqual(expected, CPermutator.generate(3));
    }

    @Test
    public void fourElementPermutations() {
        List<int[]> expected = new LinkedList<>();
        add(expected, 0, 1, 2, 3);
        add(expected, 0, 1, 3, 2);
        add(expected, 0, 2, 1, 3);
        add(expected, 0, 2, 3, 1);
        add(expected, 0, 3, 1, 2);
        add(expected, 0, 3, 2, 1);
        add(expected, 1, 0, 2, 3);
        add(expected, 1, 0, 3, 2);
        add(expected, 1, 2, 0, 3);
        add(expected, 1, 2, 3, 0);
        add(expected, 1, 3, 0, 2);
        add(expected, 1, 3, 2, 0);
        add(expected, 2, 0, 1, 3);
        add(expected, 2, 0, 3, 1);
        add(expected, 2, 1, 0, 3);
        add(expected, 2, 1, 3, 0);
        add(expected, 2, 3, 0, 1);
        add(expected, 2, 3, 1, 0);
        add(expected, 3, 0, 1, 2);
        add(expected, 3, 0, 2, 1);
        add(expected, 3, 1, 0, 2);
        add(expected, 3, 1, 2, 0);
        add(expected, 3, 2, 0, 1);
        add(expected, 3, 2, 1, 0);

        assertPermutationsEqual(expected, CPermutator.generate(4));
    }

    private void add(List<int[]> listOfArrays, int... items) {
        listOfArrays.add(items);
    }

    private void assertPermutationsEqual(List<int[]> expected, List<int[]> actual) {
        assertEquals(expected.size(), actual.size());
        for (int[] actualPermutation : actual) {
            for (int[] expectedPermutation : expected) {
                if (Arrays.equals(actualPermutation, expectedPermutation)) {
                    expected.remove(expectedPermutation);
                    break;
                }
            }
        }
        assertEquals(0, expected.size());
    }
}
