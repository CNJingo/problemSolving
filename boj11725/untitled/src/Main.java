import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeNum = Integer.parseInt(st.nextToken());


        int[] memo = new int[nodeNum + 1];

        List<Integer>[] lists = new ArrayList[nodeNum + 1];

        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeNum - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            lists[n1].add(n2);
            lists[n2].add(n1);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while(!queue.isEmpty()) {
            int node = queue.poll();

            for (int i = 0; i < lists[node].size(); i++) {
                int nextNode = lists[node].get(i);
                if (visited.contains(nextNode)) {
                    continue;
                }
                visited.add(nextNode);
                queue.add(nextNode);
                memo[nextNode] = node;
            }
        }

        for (int i = 2; i < memo.length; i++) {
            System.out.println(memo[i]);
        }
    }
}
