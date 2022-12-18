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

        int[] tree = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(lowerBound(tree, M));

    }

    public static long calNumOfTree(int[] tree, long high) {
        long sum = Arrays.stream(tree).mapToLong(i -> Long.valueOf(i)).filter(i -> i > high).map(i -> i - high).sum();
        return sum;
    }

    public static long lowerBound(int[] tree,  long value) {
        long low = 0;
        long high = 2000000000L;
        long treeNum = 0;
        long mid = 0;
        while (low < high) {
            mid = low + (high - low)/2;
            treeNum = calNumOfTree(tree, mid);
            if (value > treeNum) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(mid);
        return low - 1;
    }
}
