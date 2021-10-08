import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int cnt;
    static int winner;
    static int xPosition;
    static int yPosition;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cnt = 0;
        int[][] board = new int[19][19];
        xPosition = 0;
        yPosition = 0;

        winner = 0;
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
               board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int idx = k * 2;
                        dfs(board, j, i, idx, board[i][j]);
                        dfs(board, j, i, idx + 1, board[i][j]);
                        cnt -= 1;
                        if (cnt == 5) {
                            winner = board[i][j];
                            dfs(board, j, i, idx, board[i][j]);
                            System.out.println(winner);
                            yPosition += 1;
                            xPosition += 1;
                            System.out.println(yPosition + " " + xPosition);
                            return;
                        }
                        cnt = 0;
                    }
                }
            }
        }
        System.out.println(winner);
    }

    public static void dfs(int[][] board, int x, int y, int direction, int color) {
        int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dy = {0, 0, -1, 1, 1, -1, -1, 1};
        cnt++;
        int nx = x + dx[direction];
        int ny = y + dy[direction];
        xPosition = x;
        yPosition = y;

        if (nx >= 0 && nx < 19 && ny >= 0 && ny < 19) {
            if (board[ny][nx] == color) {
                dfs(board, nx, ny, direction, color);
            }
        }
    }
}
