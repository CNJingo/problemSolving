import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder preorder;
    static StringBuilder inorder;
    static StringBuilder postorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Node root = new Node('A');
        preorder = new StringBuilder();
        inorder = new StringBuilder();
        postorder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            Character parent = input[0].charAt(0);
            Character left = input[1].charAt(0);
            Character right = input[2].charAt(0);
            if (left != '.') {
                addLeftChild(root, new Node(left), parent);
            }
            if (right != '.') {
                addRightChild(root, new Node(right), parent);
            }
        }

        preorderTraversal(root);
        inorderTraversal(root);
        postorderTraversal(root);
        System.out.println(preorder.toString());
        System.out.println(inorder.toString());
        System.out.println(postorder.toString());
    }

    public static void preorderTraversal(Node root) {
        preorder.append(root.getC());
        if (root.getLeftChild() != null) {
            preorderTraversal(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            preorderTraversal(root.getRightChild());
        }
    }

    public static void inorderTraversal(Node root) {
        if (root.getLeftChild() != null) {
            inorderTraversal(root.getLeftChild());
        }
        inorder.append(root.getC());
        if (root.getRightChild() != null) {
            inorderTraversal(root.getRightChild());
        }
    }

    public static void postorderTraversal(Node root) {
        if (root.getLeftChild() != null) {
            postorderTraversal(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            postorderTraversal(root.getRightChild());
        }
        postorder.append(root.getC());
    }

    public static void addLeftChild(Node node, Node child, Character c) {
        if (node.getC() == c) {
            node.setLeftChild(child);
        } else {
            if (node.getLeftChild() != null) {
                addLeftChild(node.getLeftChild(), child, c);
            }
            if (node.getRightChild() != null) {
                addLeftChild(node.getRightChild(), child, c);
            }
        }
    }

    public static void addRightChild(Node node, Node child, Character c) {
        if (node.getC() == c) {
            node.setRightChild(child);
        } else {
            if (node.getLeftChild() != null) {
                addRightChild(node.getLeftChild(), child, c);
            }
            if (node.getRightChild() != null) {
                addRightChild(node.getRightChild(), child, c);
            }
        }
    }
}

class Node {
    private Character c;
    private Node leftChild;
    private Node rightChild;

    public Node(Character c) {
        this.c = c;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(Character c, Node leftChild, Node rightChild) {
        this.c = c;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Character getC() {
        return c;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
