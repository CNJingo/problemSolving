import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Edge>[] tree;

    static int[][] distances;

    static int[] visited;

    static int maxValue;

    static int[] memo;

    static int max;

    static int farthestNode;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int NodeNum = Integer.parseInt(st.nextToken());

        maxValue = 0;

        visited = new int[NodeNum + 1];

        tree = new ArrayList[NodeNum + 1];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < NodeNum - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parentNodeNum = Integer.parseInt(st.nextToken());
            int childNodeNum = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            tree[parentNodeNum].add(new Edge(childNodeNum, distance));
            tree[childNodeNum].add(new Edge(parentNodeNum, distance));
        }

        int root = 1;
        farthestNode = 0;
        dfs(root, 0);
        root = farthestNode;
        max = 0;
        dfs(root, 0);
        System.out.println(max);
    }


    public static void dfs(int root, int distance) {
        visited[root] = 1;

        if (max < distance) {
            max = distance;
            farthestNode = root;
        }

        for (Edge edge : tree[root]) {
            if (visited[edge.getNum()] == 1) {
                continue;
            }
            dfs(edge.getNum(), distance + edge.getDistance());
        }
        visited[root] = 0;
    }

    public static class Edge {
        int num;
        int distance;

        public Edge(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }

        public int getNum() {
            return num;
        }

        public int getDistance() {
            return distance;
        }
    }

}
