import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] ethernetCables = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ethernetCables[i] = Integer.parseInt(st.nextToken());
        }
        long result = lowerBound(ethernetCables, M);
        System.out.println(result);

    }

    public static long lowerBound(int[] ethernetCables,  long value) {
        long low = 0;
        long high = Long.MAX_VALUE;
        long cableNum = 0;
        long mid = 0;
        while (low < high) {
            mid = low + (high - low)/2;
            cableNum = calNumOfCables(ethernetCables, mid);
            if (value > cableNum) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low - 1;
    }

    private static long calNumOfCables(int[] ethernetCables, long mid) {
        return Arrays.stream(ethernetCables).mapToLong(i -> Long.valueOf(i)).map(i -> i / mid).sum();
    }
}
