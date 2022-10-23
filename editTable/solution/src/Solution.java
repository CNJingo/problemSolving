import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";


        Node pointer;

        List<Node> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                list.add(new Node(i, null, null));
                continue;
            }
            Node newNode = new Node(i, list.get(i - 1), null);
            list.get(i - 1).setNext(newNode);
            list.add(newNode);
        }

        pointer = list.get(k);

        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < cmd.length; i++) {
            if (cmd[i].equals("Z")) {
                stack.pop().addNode();
            } else if (cmd[i].equals("C")) {
                stack.push(pointer);
                pointer.removeNode();

                if (pointer.getNext() != null) {
                    pointer = pointer.getNext();
                } else {
                    pointer = pointer.getPrev();
                }
            } else {
                String[] nowCmd = cmd[i].split(" ");

                if (nowCmd[0].equals("U")) {
                    int move = Integer.parseInt(nowCmd[1]);
                    while (move > 0) {
                        pointer = pointer.getPrev();
                        move--;
                    }
                } else {
                    int move = Integer.parseInt(nowCmd[1]);
                    while (move > 0) {
                        pointer = pointer.getNext();
                        move--;
                    }
                }
            }
        }
        while(pointer.getPrev() != null) {
            pointer = pointer.getPrev();

        }
        int[] numArr = new int[n];
        while(pointer != null) {
            numArr[pointer.getNum()]++;
            pointer = pointer.getNext();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (numArr[i] != 0) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] input = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(sol.solution(8, 2, input));
    }
}
class Node {
    private int num;
    private Node prev;
    private Node next;

    public Node(int num, Node prev, Node next) {
        this.num = num;
        this.prev = prev;
        this.next = next;
    }

    public int getNum() {
        return num;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    public void removeNode() {
        if (this.getPrev() != null) {
            this.getPrev().setNext(this.getNext());
        }
        if (this.getNext() != null) {
            this.getNext().setPrev(this.getPrev());
        }
    }

    public void addNode() {
        if (this.getPrev() != null) {
            this.getPrev().setNext(this);
        }
        if (this.getNext() != null) {
            this.getNext().setPrev(this);
        }
    }


    public void setNum(int num) {
        this.num = num;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}