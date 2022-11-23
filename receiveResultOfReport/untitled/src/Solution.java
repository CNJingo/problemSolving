import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    Map<String, Person> idMap = new HashMap<>();

    Map<String, Integer> nameAndMailCnt = new HashMap<>();

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};

        for (String name : id_list) {
            idMap.put(name, new Person(name));
            nameAndMailCnt.put(name, 0);
        }

        for (int i = 0; i < report.length; i++) {
            String reporter = report[i].split(" ")[0];
            String reportedPerson = report[i].split(" ")[1];

            idMap.get(reportedPerson).addReporter(reporter);
        }

        for (String name: id_list) {
            if (idMap.get(name).getReportedNum() >= k) {
                for (String reporter : idMap.get(name).getReporterSet()) {
                    nameAndMailCnt.put(reporter, nameAndMailCnt.get(reporter) + 1);
                }
            }
        }
        answer = new int[id_list.length];
        int idx = 0;
        for (var name : id_list) {
            answer[idx++] = nameAndMailCnt.get(name);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] answer = sol.solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
        for (int num : answer) {
            System.out.println(num);
        }
    }

    class Person {
        String name;
        Set<String> reporterSet;

        public Person(String name) {
            this.name = name;
            this.reporterSet = new HashSet<>();
        }

        public void addReporter(String reporter) {
            reporterSet.add(reporter);
        }

        public int getReportedNum() {
            return reporterSet.size();
        }

        public String getName() {
            return name;
        }

        public Set<String> getReporterSet() {
            return reporterSet;
        }
    }
}