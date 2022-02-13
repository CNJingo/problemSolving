import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static Map<String, SetNode> sets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sets = new HashMap<>();
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                String first = st.nextToken();
                String second = st.nextToken();
                if (!sets.containsKey(first)) {
                    sets.put(first, new SetNode(first, 1));
                }
                if (!sets.containsKey(second)) {
                    sets.put(second, new SetNode(second, 1));
                }
                union(first, second);
                String parent = find(first);
                System.out.println(sets.get(parent).getSize());
            }
        }
    }

    public static String find(String a) {
        SetNode n = sets.get(a);
        if (n.getParent().equals(a)) {
            return a;
        }
        n.setParent(find(n.getParent()));

        return n.getParent();
    }

    public static Boolean union(String a, String b) {
        String aRoot = find(a);
        String bRoot = find(b);

        if (aRoot.equals(bRoot)) {
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
    private String parent;
    private int size;

    public SetNode(String parent, int size) {
        this.parent = parent;
        this.size = size;
    }

    public String getParent() {
        return parent;
    }

    public int getSize() {
        return size;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void addSize(int num) {
        this.size += num;
    }
}

