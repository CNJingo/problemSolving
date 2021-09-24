import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        Node root = new Node("");

        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            Node cursor = root;
            for (int j = 0; j < k; j++) {
                String str = input[j + 1];
                if (cursor.findNode(str) != null) {
                    cursor = cursor.findNode(str);
                } else {
                    cursor.addChild(new Node(str));
                    cursor = cursor.findNode(str);
                }
            }
        }

        for (var child : root.getMap().entrySet()) {
            dfs(child.getValue(), 0);
        }
        System.out.print(sb.toString());
    }

    public static void dfs(Node root, int level) {
        if (root.getAlphabet().equals("")) {
            return;
        }
        for (int i = 0; i < level; i++) {
            sb.append("--");
        }
        sb.append(root.getAlphabet());
        sb.append("\n");
        for (var entry : root.getMap().entrySet()) {
            dfs(entry.getValue(), level + 1);
        }
    }
}


class Node implements Comparable<Node>{
    private String alphabet;
    private TreeMap<String, Node> map;

    public Node(String alphabet) {
        this.alphabet = alphabet;
        this.map = new TreeMap<>();
    }

    @Override
    public int compareTo(Node o1) {
        return this.alphabet.compareTo(o1.alphabet);
    }

    public void addChild(Node child) {
        this.map.put(child.getAlphabet(), child);
    }

    public String getAlphabet() {
        return alphabet;
    }


    public Node findNode(String str) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        return null;
    }

    public Map<String, Node> getMap() {
        return map;
    }
}

