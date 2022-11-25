package src;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Solution {

    static int minFee = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        HashMap<Integer, Node> nodeList = new HashMap<>();

        HashMap<Integer, Integer> minDistsA = new HashMap<>();

        HashMap<Integer, Integer> minDistsB = new HashMap<>();

        HashMap<Integer, Integer> minDistsS = new HashMap<>();

        for (int nodeNUm = 1; nodeNUm <= n; nodeNUm++) {
            nodeList.put(nodeNUm, new Node(nodeNUm));
            minDistsA.put(nodeNUm, Integer.MAX_VALUE);
            minDistsB.put(nodeNUm, Integer.MAX_VALUE);
            minDistsS.put(nodeNUm, Integer.MAX_VALUE);

        }

        for (int i = 0; i < fares.length; i++) {
            nodeList.get(fares[i][0]).addRoead(nodeList.get(fares[i][1]), fares[i][2]);
            nodeList.get(fares[i][1]).addRoead(nodeList.get(fares[i][0]), fares[i][2]);
        }

        HashMap<Integer, Integer> resultS = run(nodeList, s, minDistsS);
        HashMap<Integer, Integer> resultA = run(nodeList, a, minDistsA);
        HashMap<Integer, Integer> resultB = run(nodeList, b, minDistsB);

        for(var node : nodeList.entrySet()) {
            int nodeNum = node.getKey();
            if (resultS.get(nodeNum) + resultA.get(nodeNum) + resultB.get(nodeNum) < minFee) {
                minFee = resultS.get(nodeNum) + resultA.get(nodeNum) + resultB.get(nodeNum);
            }

        }

        return minFee;
    }

    public HashMap<Integer, Integer> run (final HashMap<Integer, Node> nodes, final int from, final HashMap<Integer, Integer> prevs) {
        HashMap<Integer, Integer> minDists = new HashMap<>();

        final int INF = Integer.MAX_VALUE;
        for (var entry : nodes.entrySet()) {
            int num = entry.getKey();
            minDists.put(num, INF);
        }

        minDists.put(from, 0);
        prevs.put(from, null);

        PriorityQueue<Candidate> open = new PriorityQueue<Candidate>();
        Node s = nodes.get(from);
        Candidate candidate = new Candidate(s, 0);
        open.add(candidate);

        while(!open.isEmpty()) {
            candidate = open.poll();

            Node n = candidate.getNode();
            int nodeNum = n.getNum();

            int minDist = minDists.get(nodeNum);
            int dist = candidate.getDistance();

            if (minDist < dist) {
                continue;
            }

            Map<Node, Integer> roads = n.getRoads();

            for (var e : roads.entrySet()) {
                Node next = e.getKey();

                int weight = e.getValue();
                int newDist = minDist + weight;

                int nextNum = next.getNum();
                int nextMinDist = minDists.get(nextNum);

                if (newDist >= nextMinDist) {
                    continue;
                }
                minDists.put(nextNum, newDist);

                prevs.put(nextNum, nodeNum);

                Candidate newCandidate = new Candidate(next, newDist);

                open.add(newCandidate);
            }
        }
        return minDists;
    }

    class Node {

        int num;
        Map<Node, Integer> roads = new HashMap<>();

        public Node(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public Map<Node, Integer> getRoads() {
            return roads;
        }

        public void addRoead(final Node to, final int dist) {
            roads.put(to, dist);
        }

        public int getDistnace(final Node to) {
            return roads.get(to);
        }
    }

    class Candidate implements Comparable<Candidate> {

        private final Node node;
        private final int distance;

        public Candidate(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public Node getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Candidate o) {
            return this.getDistance() - o.getDistance();
        }
    }
}