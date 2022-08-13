import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int executionNum = Integer.parseInt(st.nextToken());

        Node head = new Node(null, null);
        Node tail = new Node(null, null);

        for (int i = 0; i < executionNum; i++) {
            st = new StringTokenizer(br.readLine());
            String functions = st.nextToken();
            st = new StringTokenizer(br.readLine());
            int arraySize = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            String arrayString = st.nextToken();

            List<Integer> array = new ArrayList<>();
            String[] splitString = arrayString.substring(1, arrayString.length() - 1).split(",");
            for (int j = 0; j < splitString.length; j++) {
                if (splitString[j].equals("")) {
                    continue;
                }
                int num = Integer.parseInt(String.valueOf(splitString[j]));
                array.add(num);
            }

            String result = executeFunction(functions, array);
            if (result.equals("error")) {
                System.out.println("error");
            } else {
                System.out.println(result);
            }
        }
    }

    private static String formatter(String number) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < number.length(); i++) {
            sb.append(number.charAt(i));
            if (i != number.length() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String executeFunction(String functions, List<Integer> array) {
        int reverse = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int pointer = 0;
        for (int j = 0; j < functions.length(); j++) {
            char function = functions.charAt(j);
            if (function == 'R') {
                reverse = (reverse + 1) % 2;
                if (reverse == 1) {
                    pointer = array.size() - 1;
                } else {
                    pointer = 0;
                }
            } else {
                if (array.size() == 0) {
                    return "error";
                }
                array.remove(pointer);
                if (reverse == 1) {
                    pointer = array.size() - 1;
                }
            }
        }
        if (reverse == 1) {
            for (int i = 0; i < array.size(); i++) {
                sb.append(array.get(array.size() -1 - i));
                if (i != array.size() - 1) {
                    sb.append(",");
                }
            }
        } else {
            for (int i = 0; i < array.size(); i++) {
                sb.append(array.get(i));
                if (i != array.size() - 1) {
                    sb.append(",");
                }
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static class Node {
        public Node next;
        public Node prev;

        public Node(Node next, Node prev) {
            this.next = next;
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void addNextNode(Node node) {
            Node currentNode = this;
            while(currentNode.next != null) {
                currentNode = this.next;
            }
            currentNode.setNext(node);
        }
    }

}
