import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Comparator;

class MergeSortTest {

    // A basic test for the mergeSort method with a fixed array
    @Test
    void testMergeSortBasic() {
        Integer[] array = {5, 2, 8, 1, 9, 4, 7, 3, 6};
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        MergeSort.mergeSort(array, new MergeSort.DefaultComparator());

        Assertions.assertArrayEquals(expected, array);
    }

    // Parameterized test to parallel the main method's execution in MergeSort.java
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16, 32, 64})
    void testMergeSortWithVaryingN(int N) {
        // Generate array as done in MergeSort.java's test method
        Integer[] array = MergeSort.generateArray(N);

        // Create a copy to sort with Java's built-in sort for comparison
        Integer[] expectedSortedArray = Arrays.copyOf(array, N);
        Arrays.sort(expectedSortedArray);

        // Reset whileExecutions counter before sorting, as done in MergeSort.java's test method
        MergeSort.whileExecutions = 0;

        // Perform merge sort
        MergeSort.mergeSort(array, new MergeSort.DefaultComparator());

        // Assert that the array is sorted correctly
        Assertions.assertArrayEquals(expectedSortedArray, array, "Array should be sorted correctly for N = " + N);

        // Calculate expected while executions as done in MergeSort.java's test method
        double logN = Math.log(N) / Math.log(2);
        int expectedWhileExecutions = (int) (N * logN);

        // Assert that whileExecutions matches the expected value
        Assertions.assertEquals(expectedWhileExecutions, MergeSort.whileExecutions,
                "whileExecutions count should match N*log(N) for N = " + N);
    }
}
