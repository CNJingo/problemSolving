import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static char[] operators;
    static int min;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        operators = new char[]{'+', '-', '*', '/'};

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        K = arr.length - 1;

        int[] opers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        recursiveCal(arr, opers, arr[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    public static void recursiveCal(int[] arr, int[] opers, int result, int idx) {
        if (idx == arr.length) {
            if (result < min) {
                min = result;
            }
            if (result > max) {
                max = result;
            }
            return;
        }
        if (opers[0] >= 1) {
            opers[0]--;
            recursiveCal(arr, opers, result + arr[idx], idx + 1);
            opers[0]++;
        }
        if (opers[1] >= 1) {
            opers[1]--;
            recursiveCal(arr, opers, result - arr[idx], idx + 1);
            opers[1]++;
        }
        if (opers[2] >= 1) {
            opers[2]--;
            recursiveCal(arr, opers, result * arr[idx], idx + 1);
            opers[2]++;
        }
        if (opers[3] >= 1) {
            opers[3]--;
            recursiveCal(arr, opers, result / arr[idx], idx + 1);
            opers[3]++;
        }
    }
}

