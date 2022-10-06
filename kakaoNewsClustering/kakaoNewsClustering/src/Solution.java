import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Solution {

    Map<String, Numset> map;
    public int solution(String str1, String str2) {
        map = new HashMap<>();
        int answer = 0;
        parseString(str1, 1);
        parseString(str2, 2);

        double min = 0;
        double max = 0;

        for (var entry : map.entrySet()) {
            max += entry.getValue().totalNum();
            min += entry.getValue().calIntersection();
        }
        if (max == 0) {
            return 65536;
        }
        max = max - min;
        double similar = min / max;
        System.out.println((int)(similar * 65536));
        return (int)(similar * 65536);
    }

    public void parseString(String str, int num) {
        if (str.length() <= 1) {
            return;
        }
        String newString = str.substring(0, 2).toUpperCase(Locale.ROOT);
        Pattern p = Pattern.compile("[^A-Za-z]");
        Matcher m = p.matcher(newString);
        // boolean b = m.matches();
        boolean b = m.find();
        if (!b) {
            if (map.containsKey(newString)) {
                if (num == 1) {
                    map.get(newString).groupOne++;
                } else {
                    map.get(newString).groupTwo++;
                }
            } else {
                if (num == 1) {
                    map.put(newString, new Numset(newString, 1, 0));
                } else {
                    map.put(newString, new Numset(newString, 0, 1));
                }
            }
        }
        parseString(str.substring(1), num);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution("FRANCE", "french");
    }

    class Numset {
        String str;
        int groupOne;
        int groupTwo;

        public Numset(String str, int groupOne, int groupTwo) {
            this.str = str;
            this.groupOne = groupOne;
            this.groupTwo = groupTwo;
        }

        public int totalNum() {
            return groupOne + groupTwo;
        }

        public int calIntersection() {
            int cnt = 0;
            while (groupOne >= 1 && groupTwo >= 1) {
                cnt++;
                groupOne--;
                groupTwo--;
            }
            return cnt;
        }


    }
}