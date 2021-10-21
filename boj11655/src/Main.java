import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i) - '0' >= 0 && input.charAt(i) - '0' <= 9) || input.charAt(i) == ' ') {
                sb.append(input.charAt(i));
                continue;
            }
            if (input.charAt(i) >= 65 && input.charAt(i) <= 90) {
                char newChar = (char) (input.charAt(i) + 13);
                if (newChar > 90) {
                    newChar = (char) (newChar % 91 + 65);
                }
                sb.append(newChar);
            } else {
                char newChar = (char) (input.charAt(i) + 13);
                if (newChar > 122) {
                    newChar = (char) (newChar % 123 + 97);
                }
                sb.append(newChar);
            }
        }
        System.out.println(sb.toString());


    }
}
