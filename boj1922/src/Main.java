import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static int atoi(String o) {
        return Integer.parseInt(o);
    }

    static Map<Integer, SetNode> sets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sets = new HashMap<>();

        int numOfComputer = atoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int numOfCable = atoi(st.nextToken());

        for (int i = 1; i <= numOfComputer; i++) {
            sets.put(i, new SetNode(i, 1));
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < numOfCable; i++) {
            st = new StringTokenizer(br.readLine());
            int from = atoi(st.nextToken());
            int to = atoi(st.nextToken());
            int len = atoi(st.nextToken());
            if (from != to) {
                edges.add(new Edge(from, to, len));
            }
        }

        System.out.println(kruskal(edges, numOfComputer));
    }


    public static int kruskal(List<Edge> edges, int numOfVertex) {

        edges.sort(Edge::compareTo);

        int res = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            if (union(edge.getFrom(), edge.getTo())) {
                res += edge.getLen();
                if (++cnt == numOfVertex - 1) {
                    return res;
                }
            }
        }
        return res;
    }

    public static int find(int a) {
        SetNode node = sets.get(a);
        if (node.getParent() == a) {
            return a;
        }
        node.setParent(find(node.getParent()));

        return node.getParent();
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }
        SetNode parent = sets.get(aRoot);
        SetNode child = sets.get(bRoot);

        if (child.getSize() > parent.getSize()) {
            SetNode temp = child;
            child = parent;
            parent = temp;
        }

        child.setParent(parent.getParent());
        parent.addSize(child.getSize());
        return true;
    }
}

class Edge implements Comparable<Edge> {
    private int from;
    private int to;
    private int len;

    public Edge(int from, int to, int len) {
        this.from = from;
        this.to = to;
        this.len = len;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.len, o.len);
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getLen() {
        return len;
    }

    @Override
    public String toString() {
        return from + " - " + to;
    }
}

class SetNode {
    private int parent;
    private int size;

    public SetNode(int parent, int size) {
        this.parent = parent;
        this.size = size;
    }

    public int getParent() {
        return parent;
    }

    public int getSize() {
        return size;
    }

    public void addSize(int size) {
        this.size += size;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
