import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Set<String> set = new TreeSet<>();

    static int[] visited;

    static char[] words;

    static Set<Character> vowels = new HashSet<>();

    static int cryptoLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cryptoLen = Integer.parseInt(st.nextToken());

        visited = new int[10000];

        int wordLen = Integer.parseInt(st.nextToken());

        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        words = new char[wordLen];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < wordLen; i++) {
            words[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(words);

        dfs(wordLen, "", -1);

        List<String> stringList = new ArrayList<>();
        for (String word : set) {
            System.out.println(word);
        }


    }

    public static void dfs(int len, String word, int maxNum) {

        if (word.length() == cryptoLen) {
            int cnt = 0;
            if (!set.contains(word)) {
                for (int i = 0; i < word.length(); i++) {
                    if (vowels.contains(word.charAt(i))) {
                        cnt++;
                    }
                }
            }
            if (cnt >= 1 && word.length() - cnt >= 2) {
                set.add(word);
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (visited[i] == 0) {
                if (i > maxNum) {
                    visited[i] = 1;
                    dfs(len, word + words[i], i);
                    visited[i] = 0;
                }
            }
        }
    }
}
