package algorithms;

import metrics.PerformanceTracker;

public class MinHeap {
    private int[] heap;
    private int size;
    private PerformanceTracker tracker;

    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
        this.tracker = tracker;
        if (tracker != null) tracker.incAllocations(capacity);
    }

    public void insert(int val) {
        if (size == heap.length) throw new IllegalStateException("Heap full");
        heap[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap empty");
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (tracker != null) tracker.incComparisons(1);
            if (heap[i] < heap[parent]) {
                swap(i, parent);
                i = parent;
            } else break;
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2, smallest = i;
            if (left < size) {
                if (tracker != null) tracker.incComparisons(1);
                if (heap[left] < heap[smallest]) smallest = left;
            }
            if (right < size) {
                if (tracker != null) tracker.incComparisons(1);
                if (heap[right] < heap[smallest]) smallest = right;
            }
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else break;
        }
    }

    private void swap(int i, int j) {
        if (tracker != null) tracker.incSwaps(1);
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // 👇 вот этот метод нужен для BenchmarkRunner
    public static void heapSort(int[] arr, PerformanceTracker tracker) {
        MinHeap h = new MinHeap(arr.length);
        for (int x : arr) h.insert(x);
        for (int i = 0; i < arr.length; i++) arr[i] = h.extractMin();
    }
}
