import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        int sizeOfTriangle = Integer.parseInt(st.nextToken());

        int[][] memo = new int[sizeOfTriangle][sizeOfTriangle];

        int[][] triangle = new int[sizeOfTriangle][sizeOfTriangle];

        int idx = 0;

        for (int i = 0; i < sizeOfTriangle; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < input.length; j++) {
                triangle[i][j] = input[j];
                if (i == sizeOfTriangle - 1) {
                    memo[i][j] = input[j];
                }
            }
        }



        for (int i = sizeOfTriangle - 2; i >= 0; i--) {
            for (int j = 0; j < sizeOfTriangle - 1; j++) {
                memo[i][j] = Math.max(memo[i + 1][j], memo[i + 1][j + 1]) + triangle[i][j];
            }
        }

        System.out.println(memo[0][0]);

    }

}
