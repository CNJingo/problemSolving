import java.util.*;
import java.util.stream.Collectors;

class Solution {

    static int register;

    static int ans;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        register = 0;

        Map<Integer, Node> mountain = new HashMap<>();
        for (int i = 0; i < paths.length; i++) {

            Node vertex1 = new Node(paths[i][0]);
            if (mountain.containsKey(paths[i][0])) {
                vertex1 = mountain.get(paths[i][0]);
            }
            Node vertex2 = new Node(paths[i][1]);
            if (mountain.containsKey(paths[i][1])) {
                vertex2 = mountain.get(paths[i][1]);
            }
            vertex1.setType(TYPE.SHELTER);
            vertex2.setType(TYPE.SHELTER);
            mountain.put(paths[i][0], vertex1);
            mountain.put(paths[i][1], vertex2);
            vertex1.addNeighbor(vertex2, paths[i][2]);
            vertex2.addNeighbor(vertex1, paths[i][2]);
        }

        Set<Node> startingPoint = new HashSet<>();
        for (int i = 0; i < gates.length; i++) {
            mountain.get(gates[i]).setType(TYPE.STARTING);
            startingPoint.add(mountain.get(gates[i]));
        }

        for (int i = 0; i < summits.length; i++) {
            mountain.get(summits[i]).setType(TYPE.END);
        }

        ans = Integer.MAX_VALUE;
        register = Integer.MAX_VALUE;
        int min = findMinIntensity(startingPoint.stream().collect(Collectors.toList()));

        return new int[]{ans, min};
    }
    public int findMinIntensity(List<Node> nodeList) {
        int left = 0;
        int right = 10000000;
        int mid = (left + right) / 2;
        while (left <= right) {
            mid = (right + left) / 2;
            register = Integer.MAX_VALUE;
            if (bfs(nodeList, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public Boolean bfs(List<Node> nodeList, int limit) {
        Set<Integer> visited = new HashSet<>();
        Boolean isPossible = false;

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            queue.add(nodeList.get(i));
            visited.add(nodeList.get(i).getNum());
        }
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.getType() == TYPE.END) {
                if (register > currentNode.getNum()) {
                    register = currentNode.getNum();
                    ans = register;
                }
                isPossible = true;
            } else {
                for (var nodeAndDistance : currentNode.getNeighborAndDistance().entrySet()) {
                    if (visited.contains(nodeAndDistance.getKey().getNum())) {
                        continue;
                    }
                    if (nodeAndDistance.getValue() <= limit) {
                        queue.add(nodeAndDistance.getKey());
                        visited.add(nodeAndDistance.getKey().getNum());
                    }
                }
            }
        }
        return isPossible;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(7, new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6,7,1}}, new int[]{3,7}, new int[]{1, 5});
    }


    class Node {

        int num;

        Map<Node, Integer> neighborAndDistance;

        TYPE type;


        public Node(int num) {
            this.num = num;
            this.neighborAndDistance = new HashMap<>();
        }

        public void setType(TYPE type) {
            this.type = type;
        }

        public void addNeighbor(Node newNode, int distance) {
            neighborAndDistance.put(newNode, distance);
        }

        public int getNum() {
            return num;
        }

        public Map<Node, Integer> getNeighborAndDistance() {
            return neighborAndDistance;
        }

        public TYPE getType() {
            return type;
        }
    }

    enum TYPE {
        STARTING, END, SHELTER
    }
}