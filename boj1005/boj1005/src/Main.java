import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] degree;

    static int[] cost;

    static int buildingCnt;

    static StringBuilder sb;

    static int buildingTime;

    static ArrayList<Integer>[] buildings;

    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCaseCnt = Integer.parseInt(st.nextToken());

        for (int i = 0; i < testCaseCnt; i++) {
            buildingTime = 0;
            st = new StringTokenizer(br.readLine());
            buildingCnt = Integer.parseInt(st.nextToken());
            memo = new int[buildingCnt + 1];
            cost = new int[buildingCnt + 1];
            degree = new int[buildingCnt + 1];
            int ruleCnt = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();

            buildings = new ArrayList[buildingCnt + 1];
            for (int j = 1; j <= buildingCnt; j++) {
                cost[j] = Integer.parseInt(st.nextToken());
                buildings[j] = new ArrayList<>();
            }
            ArrayList<Pair> pairList = new ArrayList<>();

            for (int j = 0; j < ruleCnt; j++) {
                st = new StringTokenizer(br.readLine());
                int preceding = Integer.parseInt(st.nextToken());
                int following = Integer.parseInt(st.nextToken());

                Pair pair = new Pair(preceding, following);
                pairList.add(pair);
                degree[following] += 1;

                buildings[preceding].add(following);
            }

            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            // System.out.println(map.get(target).totaltime);
            int totalTime = 0;

            topologySort(buildings,  target);
            System.out.println(memo[target]);
        }
    }

    public static void topologySort(List<Integer>[] buildings, int target) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= buildingCnt; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                memo[i] = cost[i];
            }
        }

        while(!queue.isEmpty()) {
            int buildingNum = queue.poll();
            if (buildingNum == target) {
                break;
            }
            for (int num : buildings[buildingNum]) {
                if (--degree[num] == 0) {
                    queue.add(num);
                }
                if (memo[num] < memo[buildingNum] + cost[num]) {
                    memo[num] = memo[buildingNum] + cost[num];
                }
            }
        }
    }

    public static class Pair {
        int preceding;
        int following;

        public Pair(int preceding, int following) {
            this.preceding = preceding;
            this.following = following;
        }
    }
}
