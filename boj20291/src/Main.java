import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static TreeMap<String, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        map = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            String extension = br.readLine().split("\\.")[1];
            if (!map.containsKey(extension)) {
                map.put(extension, 1);
            } else {
                map.put(extension, map.get(extension) + 1);
            }
        }

        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}

