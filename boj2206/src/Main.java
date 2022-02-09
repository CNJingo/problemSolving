import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][][] visited;
    static int shortestPath;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int[][] board = new int[row][col];
        visited = new int[row][col][2];
        shortestPath = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {
            String line = br.readLine();
            for (int j = 0; j < col; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
        bfsWithWall(board, 0, 0);
        if (shortestPath == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(shortestPath);
        }

    }

    public static void bfsWithWall(int[][] board, int x, int y) {

        int[] dx = {-1 ,1 ,0 ,0};
        int[] dy = {0, 0, -1, 1};

        Queue<Path> queue = new LinkedList<>();
        Path path = new Path(x, y, 1);
        queue.add(path);
        while(!queue.isEmpty()) {
            Path next = queue.poll();
            if (next.getX() == board[0].length - 1 && next.getY() == board.length - 1) {
                if (shortestPath > next.getCount()) {
                    shortestPath = next.getCount();
                }
            }
            for (int i = 0; i < 4; i++) {
                int nx = next.getX() + dx[i];
                int ny = next.getY() + dy[i];

                if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {

                    if (board[ny][nx] == 1 && next.isWall() == false && visited[ny][nx][1] == 0) {
                        visited[ny][nx][1] = 1;
                        Path nextPath = new Path(nx, ny, next.getCount() + 1, true);
                        queue.add(nextPath);
                    } else if (board[ny][nx] == 0 && next.isWall() == false && visited[ny][nx][0] == 0) {
                        Path nextPath = new Path(nx, ny, next.getCount() + 1, next.isWall());
                        visited[ny][nx][0] = 1;
                        queue.add(nextPath);
                    } else if (board[ny][nx] == 0 && next.isWall() == true && visited[ny][nx][1] == 0) {
                        visited[ny][nx][1] = 1;
                        Path nextPath = new Path(nx, ny, next.getCount() + 1, true);
                        queue.add(nextPath);
                    }
                }
            }
        }
    }

}

class Path {
    private int x;
    private int y;
    private int count;
    private boolean wall;

    public Path(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.wall = false;
    }

    public Path(int x, int y, int count, boolean wall) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.wall = wall;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCount() {
        return count;
    }

    public boolean isWall() {
        return wall;
    }
}
