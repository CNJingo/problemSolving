import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        Set<String> jewel = new HashSet<>();
        for (int i = 0; i < gems.length; i++) {
            jewel.add(gems[i]);
        }
        int totalJewel = jewel.size();
        int leftRegister = 0;
        int rightRegister  = 0;

        int left = 0;
        int right = 1;
        int jewelCnt = 0;
        String leftJewel = gems[left];
        Map<String, Integer> map = new HashMap<>();
        jewel = new HashSet<>();
        map.put(leftJewel, 1);
        int shortestLen = Integer.MAX_VALUE;
        if (map.size() == totalJewel) {
            shortestLen = 1;
            leftRegister = 1;
            rightRegister = 1;
        }
        while(right < gems.length && left < right) {
            if (!map.containsKey(gems[right])) {
                map.put(gems[right++], 1);
            } else {
                map.put(gems[right], map.get(gems[right]) + 1);
                while (map.get(leftJewel) > 1 && right > left) {
                    map.put(gems[left], map.get(gems[left]) - 1);
                    if (map.get(gems[left]) == 0) {
                        map.remove(gems[left]);
                    }
                    left++;
                    leftJewel = gems[left];

                }
                right++;
            }
            if (map.size() == totalJewel) {
                if (right - left < shortestLen) {
                    shortestLen = right - left;
                    leftRegister = left + 1;
                    rightRegister = right;
                }
            }
        }
        return new int[]{leftRegister, rightRegister};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new String[]{"XYZ", "XYZ", "XYZ"});
    }
}