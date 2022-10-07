import java.util.*;

class Solution {

    Stack<Character> stack = new Stack<>();
    Set<String> set = new HashSet<>();
    Long max = 0L;

    public long solution(String expression) {
        long answer = 0;
        makePriorityCombi("*-+", 0, new int[3]);
        for (var operators : set) {
            String[] expr = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
            List<Long> nums = new ArrayList<>();
            List<Character> ops = new ArrayList<>();
            for (int i = 0; i < expr.length; i++) {
                if (expr[i].matches("[-+*/]")) {
                    ops.add(expr[i].charAt(0));
                } else {
                    nums.add(Long.valueOf(expr[i]));
                }
            }
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < ops.size(); i++) {
                    if (ops.get(i) == operators.charAt(k)) {
                        if (operators.charAt(k) == '*') {
                            Long res = nums.get(i) * nums.get(i + 1);
                            nums.remove(i);
                            nums.remove(i);
                            ops.remove(i);
                            nums.add(i, res);
                            i--;
                        }
                        if (operators.charAt(k) == '-') {
                            Long res = nums.get(i) - nums.get(i + 1);
                            nums.remove(i);
                            nums.remove(i);
                            ops.remove(i);
                            nums.add(i, res);
                            i--;
                        }
                        if (operators.charAt(k) == '+') {
                            Long res = nums.get(i) + nums.get(i + 1);
                            nums.remove(i);
                            nums.remove(i);
                            ops.remove(i);
                            nums.add(i, res);
                            i--;
                        }
                    }
                }
            }
            if (max < Math.abs(nums.get(0))) {
                max = Math.abs(nums.get(0));
            }
        }
        System.out.println(max);

        return answer;
    }

    public void makePriorityCombi(String str, int depth, int[] visited) {
        if (depth == 3) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i  < 3; i++) {
                sb.append(stack.get(i));
            }
            set.add(sb.toString());
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i] == 0) {
                stack.push(str.charAt(i));
                visited[i] = 1;
                makePriorityCombi(str, depth + 1, visited);
                visited[i] = 0;
                stack.pop();
            }
        }
    }
}