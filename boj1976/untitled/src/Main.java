import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<Integer, SetNode> sets = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cityNum = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int edgeNum = Integer.parseInt(st.nextToken());

        for (int city = 1; city <= cityNum; city++) {
            sets.put(city, new SetNode(city, 1));
        }

        for (int city = 1; city <= cityNum; city++) {
            int[] roads = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int connectedCity = 1; connectedCity <= roads.length; connectedCity++) {
                if (roads[connectedCity - 1] == 1) {
                    union(city, connectedCity);
                }
            }
        }

        int[] coarse = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int root = find(coarse[0]);

        for (int i = 1; i < coarse.length; i++) {
            if (find(coarse[i])!= root) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static Boolean union(int a1, int a2) {
        int aRoot = find(a1);
        int bRoot = find(a2);

        if (aRoot == bRoot) {
            return true;
        }
        SetNode parent = sets.get(aRoot);
        SetNode child = sets.get(bRoot);

        if (parent.size < child.size) {
            SetNode temp = parent;
            parent = child;
            child = temp;
        }

        child.parent = parent.parent;
        parent.size = child.size + parent.size;
        return false;
    }

    public static int find(int a1) {

        SetNode n = sets.get(a1);
        int parent = n.parent;
        if (parent == a1) {
            return a1;
        }
        n.parent = find(n.parent);
        return n.parent;
    }

    static class SetNode {
        int parent;
        int size;

        public SetNode(int parent, int size) {
            this.parent = parent;
            this.size = size;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
