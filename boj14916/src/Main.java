import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int minCoin = -1;
        int num = Integer.parseInt(st.nextToken());
        int pivot = num;
        for (int i = 1; i < pivot / 2; i++) {
            num = pivot;
            int five = i * 5;
            num -= five;
            if (num < 0) {
                break;
            }
            if (num % 2 == 0) {
                minCoin = i + num / 2;
            }
        }
        System.out.println(minCoin);
    }
}

