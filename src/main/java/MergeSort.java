import java.util.Comparator;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    static int whileExecutions = 0; // variable for total number of loop executions

    // Insert merge and mergeSort code from book here
    // Edit book code to include whileExecutions
    // Code fragments 12.1.1 and 12.1.2 go here then edit
    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            whileExecutions++;
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i + j] = S1[i++];               // copy ith element of S1 and increment i
            else
                S[i + j] = S2[j++];               // copy jth element of S2 and increment j
        }
    }

    /**Merge-sort contents of array S. */
    public static <K> void mergeSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        if (n < 2) return;                              // array is trivially sorted
        // divide
        int mid = n/2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);         // copy of first half
        K[] S2 = Arrays.copyOfRange(S, mid, n);         // copy of second half
        // conquer (with recursion)
        mergeSort(S1, comp);                            // sort copy of first half
        mergeSort(S2, comp);                            // sort copy of second half
        // merge results
        merge(S1, S2, S, comp);              // merge sorted halves back into original
    }


    // CODE FROM BOOK ENDS HERE

    static class DefaultComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    public static Integer[] generateArray(int N) {
        long seed = 12345L;
        Random rand = new Random(seed);
        Integer[] array = new Integer[N];
        for (int i = 0; i < N; i++) {
            array[i] = rand.nextInt(1000); // Generate random integers
        }
        return array;
    }

    public static void test(int N) {
        Integer[] array = generateArray(N); // variable to store array generated
        System.out.println("Pre-Sorting: " + Arrays.toString(array));

        whileExecutions = 0; // reset counter
        mergeSort(array, new DefaultComparator());

        System.out.println("Post-Sorting: " + Arrays.toString(array));
        System.out.println("Actual while executions: " + whileExecutions);

        double logN = Math.log(N) / Math.log(2);
        int property = (int) (N * logN); // cast to an int for readability
        System.out.println("Expected while executions (N*log(N)): " + property);
    }

    public static void main(String[] args) {
        System.out.println("Running Test Now");
        for (int i = 2; i <= 64; i *= 2) {
            System.out.println("\nTesting with N = " + i);
            test(i);
        }
    }

}