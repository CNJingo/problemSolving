import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] visited;

    static int minCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int boardSize = Integer.parseInt(br.readLine());

        int[][] board = new int[boardSize][boardSize];
        visited = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int num = 1;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (visited[i][j] == 0 && board[i][j] != 0) {
                    groupingIsland(board, j, i, num++);
                }
            }
        }
        visited = new int[boardSize][boardSize];
        minCnt = Integer.MAX_VALUE;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != 0) {
                    visited = new int[boardSize][boardSize];
                    bfs(board, new Point(j, i, board[i][j], 0), board[i][j]);
                }
            }
        }
        System.out.println(minCnt - 1);

    }

    public static void groupingIsland(int[][] board, int x, int y, int number) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        visited[y][x] = 1;
        board[y][x] = number;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                if (visited[ny][nx] == 0 && board[ny][nx] == 1) {
                    groupingIsland(board, nx, ny, number);
                }
            }
        }
    }

    public static void bfs(int[][] board, Point point, int number) {
        Queue<Point> queue = new LinkedList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        queue.add(point);

        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();

            if (currentPoint.getNumber() != number && currentPoint.getNumber() != 0) {
                if (minCnt > currentPoint.getDepth()) {
                    minCnt = currentPoint.getDepth();
                }
            }

            if (minCnt != Integer.MAX_VALUE && minCnt < currentPoint.getDepth()) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = currentPoint.getX() + dx[i];
                int ny = currentPoint.getY() + dy[i];
                if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                    if (board[ny][nx] != number && visited[ny][nx] == 0) {
                        visited[ny][nx] = 1;
                        queue.add(new Point(nx, ny, board[ny][nx], currentPoint.getDepth() + 1));
                    }
                }
            }
        }
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static class Point {
        int x;
        int y;

        int number;

        int depth;

        public Point(int x, int y, int number, int depth) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.depth = depth;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getNumber() {
            return number;
        }

        public int getDepth() {
            return depth;
        }
    }
}
