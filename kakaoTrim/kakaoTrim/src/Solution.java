import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length() - 1);
        List<Integer> sumList = new ArrayList<>();

        Stream.of(s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ")).
                forEach(string -> sumList.add(Arrays.stream(string.split(",")).mapToInt(Integer::parseInt).sum()));

        Collections.sort(sumList);
        int[] ans = new int[sumList.size()];
        ans[0] = sumList.get(0);
        for (int i = 1; i < ans.length; i++) {
            ans[i] = sumList.get(i) - sumList.get(i - 1);
        }
        return ans;
    }
}