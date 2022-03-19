import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String[][] patern;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        String[][] board = new String[2187][2187];
        patern = new String[2187][2187];
        patern[0][0] = "*";
        board[0][0] = "*";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        while(n != 1) {
            n /= 3;
            cnt++;
        }
        N = 1;
        for (int i = 0; i < cnt; i++) {
            drawStar(board);
            N *= 3;
        }
        printBoard(board);
        System.out.println(sb.toString());

    }

    public static void printBoard(String[][] board) {
        int n = N;
        for (int i = 0; i < n; i++) {
            for (int j = 0 ;j < n; j++) {
                if (board[i][j] == null) {
                    sb.append(" ");
                } else {
                    sb.append(board[i][j]);
                }
            }
            sb.append("\n");
        }
    }


    public static void drawStar(String[][] board) {
        int n = N;
        for (int i = 0; i < 3 * n; i++) {
            for (int j = 0; j < 3 * n; j++) {
                if (i >= n  && i <= 2 * n - 1 && j >= n && j <= 2 * n - 1) {
                    continue;
                }
                board[i][j] = patern[i % n][j % n];
                patern[i][j] = board[i][j];
            }
        }
    }
}
