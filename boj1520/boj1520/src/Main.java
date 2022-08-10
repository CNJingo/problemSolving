import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[M][N];
        visited = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
            }
        }
        dfs(board, 0, 0);
        System.out.println(visited[0][0]);
    }

    public static int dfs(int[][] board, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        if (visited[y][x] != -1) {
            return visited[y][x];
        }

        if (x == board[0].length - 1 && y == board.length - 1) {
            return 1;
        }

        visited[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                if (board[ny][nx] < board[y][x]) {
                    visited[y][x] += dfs(board, nx, ny);
                }
            }
        }

        return visited[y][x];
    }
}
