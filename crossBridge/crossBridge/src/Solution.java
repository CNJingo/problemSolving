import java.util.Stack;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int right = 200000000;
        int left = 0;
        int mid = (right + left) / 2;
        while (left <= right) {
            mid = (right + left) / 2;
            if (checkIsPossibleToCross(stones, mid, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public Boolean checkIsPossibleToCross(int[] bridge, int mid, int k) {
        int num = mid - 1;
        int maxNoneStone = 0;
        int continuousNoneStone = 0;
        int flag = 0;
        for (int i = 0; i < bridge.length; i++) {
            if (bridge[i] - num <= 0) {
                continuousNoneStone++;
                if (continuousNoneStone > maxNoneStone) {
                    maxNoneStone = continuousNoneStone;
                }
            } else {
                continuousNoneStone = 0;
            }
        }
        if (k <= maxNoneStone || maxNoneStone == bridge.length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

}