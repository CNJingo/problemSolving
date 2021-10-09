import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[] joy;
    static int[] healths;
    static int[] pleasures;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        int pleasure = 100;
        healths = new int[k];
        pleasures = new int[k];
        healths = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        pleasures = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        joy = new int[k];
        int answer = calculate(0, 100, 0);
        System.out.println(answer);
    }

    public static int calculate(int idx, int health, int pleasure) {
        if (health <= 0) {
            return 0;
        }
        if (idx == k) {
            return pleasure;
        }
        return Math.max(calculate(idx + 1, health - healths[idx], pleasure + pleasures[idx])
            ,calculate(idx + 1, health, pleasure));

    }
}

