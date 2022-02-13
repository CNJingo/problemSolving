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

    static int[][] visited;
    static Map<Integer, Node> group;
    static int[] parents;
    static int numVertex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row, col;
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        int[][] board = new int[row][col];
        visited = new int[row][col];
        group = new HashMap<>();

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1 && visited[i][j] == 0) {
                    grouping(board, j, i, num);
                    group.put(num, new Node(num));
                    num++;
                }
            }
        }
        numVertex = group.size();
        parents = new int[numVertex + 1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != 0) {
                    visited = new int[row][col];
                    calcualteDistance(board, j, i, 0, board[i][j], 0);
                    calcualteDistance(board, j, i, 0, board[i][j], 2);
                }
            }
        }
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < group.size(); i++) {
            for (var element : group.get(i + 1).getDistances().entrySet()) {
                if (group.get(i + 1).getGroupNum() != element.getKey()) {
                    list.add(new Edge(group.get(i + 1).getGroupNum(), element.getKey(), element.getValue()));
                }
            }
        }
        int ans = kruskal(list.toArray(Edge[]::new));
        System.out.println(ans);
        
    }

    public static void calcualteDistance(int[][] board, int x, int y, int cnt, int num, int direction) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        visited[y][x] = 1;

        for (int i = direction; i < direction + 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                if (visited[ny][nx] == 0 && board[ny][nx] == 0) {
                    calcualteDistance(board, nx, ny, cnt + 1, num, direction);
                }
                if (board[ny][nx] != 0 && cnt != 1) {
                    group.get(num).modifyDistance(board[ny][nx], cnt);
                }
            }
        }
    }

    public static void grouping(int[][] board, int x, int y, int num) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        visited[y][x] = 1;
        board[y][x] = num;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                if (visited[ny][nx] == 0 && board[ny][nx] == 1) {
                    grouping(board, nx, ny, num);
                }
            }
        }
    }
    private static int kruskal(Edge[] edgeList) {

        int res = 0, cnt = 0;
        Arrays.sort(edgeList);

        make();

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                res += edge.weight;
                if (++cnt == numVertex - 1) {
                    return res;
                }
            }
        }

        return -1;
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            return false;
        }
        parents[aRoot] = bRoot;
        return true;
    }

    private static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static void make() {
        for (int i = 1; i <= numVertex; i++) {
            parents[i] = i;
        }
    }
}

class Node {
    private int groupNum;
    private Map<Integer, Integer> distances;

    public Node(int groupNum) {
        this.groupNum = groupNum;
        this.distances = new HashMap<>();
    }

    public int getGroupNum() {
        return groupNum;
    }

    public Map<Integer, Integer> getDistances() {
        return distances;
    }

    public void modifyDistance(int groupNum, int distance) {
        if (!distances.containsKey(groupNum)) {
            distances.put(groupNum, distance);
        } else {
            int originalDistance = distances.get(groupNum);
            if (originalDistance > distance) {
                distances.put(groupNum, distance);
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        super();
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

}