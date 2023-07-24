import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int inputCase = Integer.parseInt(st.nextToken());

        for (int i = 0; i < inputCase; i++) {
            Stack<Integer> stack = new Stack<>();
            st = new StringTokenizer(br.readLine());
            int numOfdocs = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            List<Integer> printQueue = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int[] visited = new int[numOfdocs];
            int[] arr = printQueue.stream().mapToInt(Integer::intValue).toArray();
            Collections.sort(printQueue, Collections.reverseOrder());

            int order = 1;

            int arrIndex = 0;
            for (;;) {
                if (visited[arrIndex] != 1) {
                    if (arr[arrIndex] == printQueue.get(0)) {
                        if (arrIndex == index) {
                            System.out.println(order);
                            break;
                        }

                        printQueue.remove(0);
                        visited[arrIndex] = order++;
                    }
                }
                arrIndex = (arrIndex + 1) % numOfdocs;
            }
        }

    }
}
