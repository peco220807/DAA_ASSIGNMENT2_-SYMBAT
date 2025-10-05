package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PerformanceTracker {
    private long comparisons=0, swaps=0, arrayAccesses=0, allocations=0;

    public void incComparisons(long k){ comparisons+=k; }
    public void incSwaps(long k){ swaps+=k; }
    public void incArrayAccesses(long k){ arrayAccesses+=k; }
    public void incAllocations(long k){ allocations+=k; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getAllocations() { return allocations; }

    public void reset(){ comparisons=swaps=arrayAccesses=allocations=0; }

    public Map<String, Object> snapshot(long timeMs, int n, String inputType, int runId) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("n", n);
        m.put("time_ms", timeMs);
        m.put("comparisons", comparisons);
        m.put("swaps", swaps);
        m.put("array_accesses", arrayAccesses);
        m.put("allocations", allocations);
        m.put("input_type", inputType);
        m.put("run_id", runId);
        return m;
    }

    public static void writeCsv(String file, List<Map<String,Object>> rows) throws IOException {
        if (rows.isEmpty()) return;
        try (FileWriter fw = new FileWriter(file)) {
            Map<String,Object> h = rows.get(0);
            String header = String.join(",", h.keySet());
            fw.write(header + "\n");
            for (Map<String,Object> r : rows) {
                StringBuilder sb = new StringBuilder();
                for (Object v : r.values()) {
                    sb.append(v == null ? "" : v.toString()).append(",");
                }
                sb.setLength(sb.length() - 1);
                fw.write(sb.toString() + "\n");
            }
        }
    }
}