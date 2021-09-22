import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static Map<Integer, SetNode> sets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sets = new HashMap<>();

        int numSet = Integer.parseInt(st.nextToken());
        int numCalculation = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= numSet; i++) {
            sets.put(i, new SetNode(i, 1));
        }

        for (int i = 0; i < numCalculation; i++) {
            st = new StringTokenizer(br.readLine());
            int calculation = Integer.parseInt(st.nextToken());
            if (calculation == 0) {
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                if (find(Integer.parseInt(st.nextToken())) != find(Integer.parseInt(st.nextToken()))) {
                    System.out.println("NO");
                } else {
                    System.out.println("YES");
                }
            }
        }

    }


    public static int find(int a) {
        SetNode n = sets.get(a);
        if (n.getParent() == a) {
            return a;
        }
        n.setParent(find(n.getParent()));

        return n.getParent();
    }

    public static Boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return true;
        }
        SetNode parent = sets.get(aRoot);
        SetNode child = sets.get(bRoot);

        if (child.getSize() > parent.getSize()) {
            SetNode temp = parent;
            parent = child;
            child = temp;
        }

        child.setParent(parent.getParent());
        parent.addSize(child.getSize());
        return false;

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

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void addSize(int num) {
        this.size += num;
    }
}

