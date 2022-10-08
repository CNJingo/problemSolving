import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Long sum1 = 0L;
        Long sum2 = 0L;
        List<Integer> list = new ArrayList<>();

        int pointer1 = 0;
        int pointer2 = queue1.length - 1;
        Long currentSum = 0L;

        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            currentSum += queue1[i];
            list.add(queue1[i]);
        }

        for (int i = 0; i < queue2.length; i++) {
            sum2 += queue2[i];
            list.add(queue2[i]);
        }

        long target = sum1 + sum2;

        if (target % 2 != 0 ) {
            return -1;
        }

        target /= 2;
        int totalCnt = 0;

        while(totalCnt <= (list.size() * 3)) {
            if (currentSum == target) {
                break;
            } else if (currentSum < target) {
                pointer2++;
                if (pointer2 == list.size()) {
                    pointer2 = 0;
                }
                currentSum += list.get(pointer2);
            } else if (currentSum > target) {
                currentSum -= list.get(pointer1);
                pointer1++;
                if (pointer1 == list.size()) {
                    pointer1 = 0;
                }
            }
            totalCnt++;
        }
        if (currentSum != target) {
            return -1;
        }
        return totalCnt;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new int[]{1, 1}, new int[]{1, 5});
    }
}