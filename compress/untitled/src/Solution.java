import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf(alphabet.toUpperCase(Locale.ROOT).charAt(i)), i + 1);
        }
        List<Integer> index = new ArrayList<>();
        int lastIndex = 27;

        for (int i = 0; i < msg.length(); i++) {
            int len = i + 1;
            while (len <= msg.length() && dict.containsKey(msg.substring(i, len))) {
                len++;
            }
            if (i == len - 1) {
                len++;
            }
            index.add(dict.get(msg.substring(i, len - 1)));
            if (len < msg.length()) {
                dict.put(msg.substring(i, len), lastIndex++);
            }
            i += len - i - 2;
        }
        return index.stream().mapToInt(Integer::intValue).toArray();
    }
}