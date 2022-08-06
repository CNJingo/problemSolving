import javax.swing.text.Position;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static Boolean isEnd = false;

    static int moveCnt = 0;

    static List<Point> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] move = new String[10001];


        int boardSize = Integer.parseInt(br.readLine());

        int[][] board = new int[boardSize][boardSize];

        int numberOfApple = Integer.parseInt(br.readLine());

        for (int i = 0; i < numberOfApple; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int appleX = Integer.parseInt(st.nextToken());
            int appleY = Integer.parseInt(st.nextToken());
            board[appleX - 1][appleY - 1] = 2;
        }

        int snakeMove = Integer.parseInt(br.readLine());

        for (int i = 0; i < snakeMove; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            move[Integer.parseInt(st.nextToken())] = st.nextToken();
        }

        snake.add(new Point(0, 0));
        String currentDirection = "Right";

        while(!isEnd) {
            Point currentPosition = move(board, snake, currentDirection);
            if (move[moveCnt] != null) {
                if (currentDirection.equals("Right")) {
                    if (move[moveCnt].equals("L")) {
                        currentDirection = "Up";
                    } else {
                        currentDirection = "Down";
                    }

                } else if (currentDirection.equals("Up")) {
                    if (move[moveCnt].equals("L")) {
                        currentDirection = "Left";
                    } else {
                        currentDirection = "Right";
                    }

                }else if (currentDirection.equals("Left")) {
                    if (move[moveCnt].equals("L")) {
                        currentDirection = "Down";
                    } else {
                        currentDirection = "Up";
                    }
                } else if (currentDirection.equals("Down")) {
                    if (move[moveCnt].equals("L")) {
                        currentDirection = "Right";
                    } else {
                        currentDirection = "Left";
                    }
                }
            }
        }
        System.out.println(moveCnt + 1);
    }

    public static Point move(int[][] board, List<Point> snake, String direction) {
        Point head = snake.get(0);
        Point nextPoint;
        switch (direction) {
            case "Right":
                nextPoint = new Point(head.getX() + 1, head.getY());
                break;
            case "Left":
                nextPoint = new Point(head.getX() - 1, head.getY());
                break;
            case "Up":
                nextPoint = new Point(head.getX(), head.getY() - 1);
                break;
            case "Down":
                nextPoint = new Point(head.getX(), head.getY() + 1);
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (nextPoint.getX() < 0 || nextPoint.getX() >= board[0].length || nextPoint.getY() < 0 || nextPoint.getY() >= board.length) {
            isEnd = true;
            return nextPoint;
        }

        if (board[nextPoint.getY()][nextPoint.getX()] == 1) {
            isEnd = true;
            return nextPoint;
        }

        snake.add(0, nextPoint);
        if (board[nextPoint.getY()][nextPoint.getX()] == 0) {
            Point tail = snake.get(snake.size() - 1);
            board[tail.getY()][tail.getX()] = 0;
            snake.remove(snake.size() - 1);
        }
        board[nextPoint.getY()][nextPoint.getX()] = 1;

        moveCnt++;

        return nextPoint;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
