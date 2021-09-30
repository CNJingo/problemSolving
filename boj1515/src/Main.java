import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String number = st.nextToken();

        int answer = 0;
        for (int i = 1; i <= 10000000; i++) {
            String num = String.valueOf(i);
            number = removeNum(number, num);
            if (number.equals("")) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    public static String removeNum(String number, String cnt) {
        for (int i = 0 ; i < cnt.length(); i++) {
            String pivot = cnt.substring(i, i + 1);
            if (number.equals("")) {
                return "";
            }

            if (pivot.equals(number.substring(0, 1))) {
                number = number.substring(1);
            }
        }
        return number;
    }
}

