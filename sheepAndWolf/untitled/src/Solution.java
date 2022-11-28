import java.util.*;

class Solution {

    static int[] info;

    static List<Integer>[] childs;

    static int maxSheepCnt;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        int answer = 0;
        List<Integer>[] nodeList = new ArrayList[info.length];
        List<Integer> nextPos = new ArrayList();
        for (int i = 0; i < info.length; i++) {
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            nodeList[parent].add(child);
        }
        childs = nodeList;
        maxSheepCnt = 0;
        dfs(0, 0, 0, nextPos);

        return maxSheepCnt;
    }

    public void dfs(int nodeNum, int sheepCnt, int wolfCnt, List<Integer> nextPos) {
        if (info[nodeNum] == 0) {
            sheepCnt++;
        } else {
            wolfCnt++;
        }
        maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);

        if (wolfCnt >= sheepCnt) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(nextPos);

        list.remove(Integer.valueOf(nodeNum));

        for (int child : childs[nodeNum]) {
            list.add(child);
        }

        for (int next : list) {
            dfs(next, sheepCnt, wolfCnt, lzist);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}});
    }
}