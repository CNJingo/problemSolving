import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        Rail[] arr = new Rail[num];
        Rail[] arr2 = new Rail[num];

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (end < start) {
                int temp = start;
                start = end;
                end = temp;
            }
            Rail rail = new Rail(start, end);
            arr[i] = rail;
            arr2[i] = rail;
        }
        int len = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        Comparator<Rail> comp = new Comparator<Rail>() {
            @Override
            public int compare(Rail o1, Rail o2) {
                return o1.getEnd() - o2.getEnd();
            }
        };
        Arrays.sort(arr, comp);
        int start = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int end = Integer.MIN_VALUE;
        PriorityQueue<Rail> queue = new PriorityQueue<>();
        for (int i = 0; i < num; i++) {
            if (arr[i].getEnd() - arr[i].getStart() <= len) {
                if (start > arr[i].getStart() || queue.size() == 0) {
                    start = arr[i].getStart();
                }
                if (arr[i].getEnd() > end) {
                    end = arr[i].getEnd();
                }
                while (!queue.isEmpty() && end - start > len) {
                    queue.poll();
                    if (queue.size() >= 1) {
                        start = queue.peek().getStart();
                    }
                }
                if (queue.size() == 0) {
                    start = arr[i].getStart();
                }
                queue.add(arr[i]);
                if (queue.size() >= max) {
                    max = queue.size();
                }
            }
        }
        if (max == Integer.MIN_VALUE) {
            max = 0;
        }
        System.out.println(max);
    }
}

class Rail implements Comparable<Rail> {
    private int start;
    private int end;

    public Rail(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Rail r) {
        return this.start - r.start;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

