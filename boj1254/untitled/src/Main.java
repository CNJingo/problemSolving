import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append(input);

        while(!isPalindrome(sb.toString())) {
            sb.append('@');
        }
        System.out.println(sb.toString().length());
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (!isEqualCharacter(str.charAt(i), str.charAt(str.length() -1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEqualCharacter(char a, char b) {
        if (b == '@' || a== b) {
            return true;
        }
        return false;
    }
}
