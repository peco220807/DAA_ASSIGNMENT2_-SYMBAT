package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;

import java.util.*;

public class BenchmarkRunner {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java -cp target/your-jar.jar cli.BenchmarkRunner <inputType> <n> <runs>");
            System.out.println("inputType = random | sorted | reversed");
            return;
        }

        String inputType = args[0];
        int n = Integer.parseInt(args[1]);
        int runs = Integer.parseInt(args[2]);

        List<Map<String, Object>> allResults = new ArrayList<>();

        for (int run = 1; run <= runs; run++) {
            int[] arr = generateArray(inputType, n);

            PerformanceTracker tracker = new PerformanceTracker();
            tracker.reset();

            long start = System.currentTimeMillis();
            MinHeap.heapSort(arr, tracker);
            long end = System.currentTimeMillis();

            Map<String, Object> result = tracker.snapshot(end - start, n, inputType, run);
            allResults.add(result);

            System.out.println(result);
        }

        try {
            PerformanceTracker.writeCsv("results.csv", allResults);
            System.out.println("Results saved to results.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] generateArray(String type, int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(100000);

        if ("sorted".equals(type)) Arrays.sort(arr);
        if ("reversed".equals(type)) {
            Arrays.sort(arr);
            for (int i = 0; i < n / 2; i++) {
                int tmp = arr[i];
                arr[i] = arr[n - 1 - i];
                arr[n - 1 - i] = tmp;
            }
        }
        return arr;
    }
}
