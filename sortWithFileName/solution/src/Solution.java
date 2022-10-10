import java.util.*;

class Solution {
    public String[] solution(String[] files) {

        List<File> fileList = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            int numIdx = 0;
            int numEndIdx = 0;
            for (int j = 0; j < files[i].length(); j++) {
                if (files[i].charAt(j) - '0' <= 9 && files[i].charAt(j) - '0' >= 0) {
                    numIdx = j;
                    while (j < files[i].length() && files[i].charAt(j) - '0' <= 9 && files[i].charAt(j) - '0' >= 0) {
                        j++;
                        numEndIdx = j;
                    }
                    break;
                }
            }
            String head = files[i].substring(0, numIdx);
            String number = files[i].substring(numIdx, numEndIdx);
            String tail = files[i].substring(numEndIdx);
            fileList.add(new File(head, number, tail));
        }

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
            }
        });

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.head.toUpperCase(Locale.ROOT).compareTo(o2.head.toUpperCase(Locale.ROOT));
            }
        });


        String[] answer = new String[fileList.size()];
        int idx = 0;
        for (var file : fileList) {
            answer[idx++] = file.getFileFullName();
            System.out.println(file.getFileFullName());
        }
        return answer;
    }

    class File {
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public String getHead() {
            return head;
        }

        public String getNumber() {
            return number;
        }

        public String getTail() {
            return tail;
        }

        public String getFileFullName() {
            return head + number + tail;
        }

    }
}