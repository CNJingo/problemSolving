import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] memo = new int[arr.length];
        Arrays.fill(memo, 1);


        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == j) {
                    continue;
                }
                if (arr[j] > arr[i] && memo[i] + 1 > memo[j]) {
                    memo[j] = memo[i] + 1;
                }
            }
        }
        int longestLen = 0;
        for (int i = 0; i < memo.length; i++) {
            if (memo[i] > longestLen) {
                longestLen = memo[i];
            }
        }

        System.out.println(longestLen);

    }
}
