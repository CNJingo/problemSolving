import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static Stack<Integer> stack;
    static int max;
    static int pivot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pivot = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        stack = new Stack<>();
        max = Integer.MIN_VALUE;
        String num = String.valueOf(pivot);
        for (int i = 1; i <= num.length(); i++) {
            makeCombi(arr, i, 0);
        }
        System.out.println(max);
    }

    public static void makeCombi(int[] arr, int k, int depth) {
        if (depth == k) {
            String num = "";
            for (int i = 0; i < stack.size(); i++) {
                num += stack.get(i);
            }
            if (Integer.parseInt(num) >= max && Integer.parseInt(num) <= pivot) {
                max = Integer.parseInt(num);
            }
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
            makeCombi(arr, k, depth + 1);
            stack.pop();
        }
    }
}
