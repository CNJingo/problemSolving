import java.sql.Array;
import java.util.*;

class Solution {
    Set<String> isVisited = new HashSet<>();

    Set<String> userIdCombi = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        List<String>[] machingIds = findMatchingId(user_id, banned_id);

        dfs(findMatchingId(user_id, banned_id), 0, banned_id.length);
        System.out.println(userIdCombi.size());
        return userIdCombi.size();
    }

    public List<String>[] findMatchingId(String[] userIds, String[] bannedIds) {
        List<String>[] matchingIds = new ArrayList[bannedIds.length];
        for (int i = 0; i < bannedIds.length; i++) {
            matchingIds[i] = new ArrayList<>();
        }
        int idx = 0;
        for (String bannedId : bannedIds) {
            for (String userId : userIds) {
                Boolean isMatched = true;
                if (bannedId.length() == userId.length()) {
                    for (int i = 0; i < bannedId.length(); i++) {
                        if (bannedId.charAt(i) == '*') {
                            continue;
                        }
                        if (bannedId.charAt(i) != userId.charAt(i)) {
                            isMatched = false;
                            break;
                        }
                    }
                    if (isMatched) {
                        matchingIds[idx].add(userId);
                    }
                }
            }
            idx++;
        }
        return matchingIds;
    }

    public void dfs (List<String>[] matchingIds, int idx, int len) {
        if (idx == len) {
            StringBuilder sb = new StringBuilder();
            String[] userCombi = new String[len];
            int index = 0;
            for (var userId: isVisited) {
                userCombi[index++] = userId;
            }
            Arrays.sort(userCombi);
            for (var userId : userCombi) {
                sb.append(userId);
            }
            userIdCombi.add(sb.toString());
            return;
        }
        for (String userId : matchingIds[idx]) {
            if (isVisited.contains(userId)) {
                continue;
            }
            isVisited.add(userId);
            dfs(matchingIds, idx + 1, len);
            isVisited.remove(userId);
        }
    }



    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"});
    }
}