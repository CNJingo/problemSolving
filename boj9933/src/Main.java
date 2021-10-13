import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<>();
        for (int i = 0 ; i < k; i++) {
            set.add(br.readLine());
        }

        for (var element : set) {
            if (set.contains(reverseString(element))) {
                System.out.println(element.length() + " " + element.charAt(element.length() / 2));
                return;
            }
        }
    }

    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(s.length() - 1 - i));
        }
        return sb.toString();
    }
}

