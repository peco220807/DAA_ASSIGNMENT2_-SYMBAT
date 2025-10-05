package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    public void emptyExtractThrows() {
        MinHeap h = new MinHeap(1);
        assertThrows(IllegalStateException.class, h::extractMin);
    }

    @Test
    public void simpleInsertExtract() {
        MinHeap h = new MinHeap(10);
        h.insert(5);
        h.insert(3);
        h.insert(7);
        assertEquals(3, h.extractMin());
        assertEquals(5, h.extractMin());
        assertEquals(7, h.extractMin());
    }

    @Test
    public void heapSortScales() {
        int[] sizes = {100, 1000, 10_000, 100_000}; // 10^2 .. 10^5
        Random rnd = new Random(123);

        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = rnd.nextInt(1_000_000);
            }

            int[] copy = Arrays.copyOf(a, a.length);
            Arrays.sort(copy);

            PerformanceTracker tracker = new PerformanceTracker();
            MinHeap.heapSort(a, tracker); // ✅ теперь с 2 аргументами

            assertArrayEquals(copy, a, "Failed on size " + n);
            System.out.printf("n=%d, comps=%d, swaps=%d%n",
                    n, tracker.getComparisons(), tracker.getSwaps());
        }
    }

    @Test
    public void heapSortWithDuplicates() {
        int n = 50_000;
        int[] arr = new int[n];
        Arrays.fill(arr, 42); // все одинаковые

        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap.heapSort(arr, tracker); // ✅ тоже с трекером

        assertArrayEquals(copy, arr);
    }
}
