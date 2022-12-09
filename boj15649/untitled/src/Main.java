import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int M;
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        makeCombi("");

    }
    public static void makeCombi(String number) {
        if (number.length() == M) {
            for (int i = 0; i < number.length(); i++) {
                System.out.print(number.charAt(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited.contains(i)) {
                visited.add(Integer.valueOf(i));
                makeCombi(number + i);
                visited.remove(Integer.valueOf(i));
            }
        }

    }

}
