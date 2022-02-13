import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int[][] visited;
    static int cnt;
    static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int boardSize = Integer.parseInt(br.readLine());

        int[][] board = new int[boardSize][boardSize];
        visited = new int[boardSize][boardSize];

        List<Integer> list = new ArrayList<>();
        cnt = 0;
        num = 0;
        for (int i = 0; i < boardSize; i++) {
            String line = br.readLine();
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (visited[i][j] == 0 && board[i][j] == 1) {
                    dfs(board, j, i);
                    list.add(cnt);
                    cnt = 0;
                    num++;
                }
            }
        }
        System.out.println(num);
        list.sort(null);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void dfs(int[][] board, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy= {0, 0, -1, 1};
        visited[y][x] = 1;
        cnt++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < board[0].length && ny >= 0 && ny < board.length) {
                if (visited[ny][nx] == 0 && board[ny][nx] == 1) {
                    dfs(board, nx, ny);
                }
            }
        }
    }


}
