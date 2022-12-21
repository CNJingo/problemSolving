import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<Integer, Node> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        int relationNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < relationNum; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            Node parentNode = map.getOrDefault(parent, new Node(parent));
            Node childNode = map.getOrDefault(child, new Node(child));
            if (!map.containsKey(parent)) {
                map.put(parent, parentNode);
            }
            if (!map.containsKey(child)) {
                map.put(child, childNode);
            }

            parentNode.addNeighbor(childNode);
            childNode.addNeighbor(parentNode);
        }
        System.out.println(bfs(map.get(p1), p2));
    }

    public static int bfs(Node root, int target) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        depthQueue.add(0);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int depth = depthQueue.poll();

            for (Node neighbor : currentNode.neighbors) {
                if (neighbor.num == target) {
                    return depth + 1;
                }

                if (visited.contains(neighbor.num)) {
                    continue;
                }
                depthQueue.add(depth + 1);
                visited.add(neighbor.num);
                queue.add(neighbor);
            }
        }
        return -1;
    }


    static class Node {
        int num;

        List<Node> neighbors;

        public Node(int num) {
            this.num = num;
            neighbors = new ArrayList<>();
        }

        public void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }

    }
}
