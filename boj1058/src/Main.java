import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        max = Integer.MIN_VALUE;
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[K][K];
        for (int i = 0 ; i < K; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == 'N') {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = 1;
                }
            }
        }

        for (int i = 0 ; i < arr.length; i++) {
            int friends = 0;
            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    if (arr[i][j] == 1 && arr[j][i] == 1) {
                        friends++;
                        continue;
                    }
                    for (int k = 0; k < arr.length; k++) {
                        if (i != k) {
                            if (arr[i][k] == 1 && arr[j][k] == 1) {
                                friends++;
                                break;
                            }
                        }
                    }
                }
            }
            if (max <= friends) {
                max = friends;
            }
        }
        System.out.println(max);

    }
}

