import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());

        int[][] memo = new int[num][3];
        int[] wine = new int[num];
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            wine[i] = Integer.parseInt(st.nextToken());
        }
        memo[0][0] = 0;
        memo[0][1] = wine[0];
        memo[0][2] = wine[0];

        for (int i = 1; i < num; i++) {
            memo[i][0] = Math.max(Math.max(memo[i - 1][2], memo[i - 1][1]), memo[i - 1][0]);
            memo[i][1] = memo[i - 1][0] + wine[i];
            memo[i][2] = memo[i - 1][1] + wine[i];
        }
        System.out.println(Math.max(Math.max(memo[num - 1][0], memo[num - 1][1]), memo[num -1][2]));

    }
}
