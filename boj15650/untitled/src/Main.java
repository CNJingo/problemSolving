import javax.xml.parsers.SAXParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int M;

    static Stack<Integer> stack = new Stack<>();

    static Set<String> set = new HashSet<>();

    static List<String> list = new ArrayList<>();
    static int[] visited;

    static int[] numberArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new int[9];
        numberArr = new int[9];
        makeCombi("");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });
        printAnswer(list);
    }

    public static void printAnswer(List<String> list) {
        for (String str : list) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i) + " ");
            }
            System.out.println();
        }
    }

    public static void makeCombi(String number) {
        if (number.length() == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == 1) {
                    sb.append(i);
                }
            }

            if (!set.contains(sb.toString())) {
                list.add(sb.toString());
            }
            set.add(sb.toString());
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                makeCombi(number + i);
                visited[i] = 0;
            }
        }

    }
}
