import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] visited;
    static int num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        visited = new int[1000001];
        num = 0;

        int ans = bfs(start, end);
        System.out.println(ans);
    }

    public static int bfs(int startPoint, int endPoint) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startPoint, 0));
        visited[startPoint] = 1;
        int count = 0;
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.getPosition() == endPoint) {
                count = now.getCnt();
                break;
            }

            int[] dx = {now.getPosition() - 1, now.getPosition() + 1, now.getPosition() * 2};

            for (int i = 0; i < dx.length; i++) {

                int nx = dx[i];
                if (nx >= 0 && nx < 100001) {
                    if (visited[nx] == 0) {
                        queue.add(new Point(nx, now.getCnt() + 1));
                    }
                }
            }
        }
        return count;
    }
}

class Point {
    private int position;
    private int cnt;

    public Point(int position, int cnt) {
        this.position = position;
        this.cnt = cnt;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCnt() {
        return cnt;
    }
}
