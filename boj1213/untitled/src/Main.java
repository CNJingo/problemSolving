import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Character> oddList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] alphabet = new int[26];

        for (int i = 0; i < input.length(); i++) {
            int idx = input.charAt(i) - 'A';
            alphabet[idx]++;
        }
        String result = makePalindrome(alphabet, "");
        if (oddList.size() >= 2) {
            System.out.println("I'm Sorry Hansoo\n");
        } else {
            if (oddList.size() == 1) {
                System.out.println(result.substring(0, result.length() / 2) + oddList.get(0) + result.substring(result.length() / 2));
            } else {
                System.out.println(result);
            }
        }

    }





    public static String makePalindrome(int[] alphabet, String str) {
        StringBuilder sb = new StringBuilder();
        Boolean flag = false;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] %2 != 0) {
                oddList.add((char)('A' + i));
                alphabet[i]--;
            }

            while (alphabet[i] > 0) {
                sb.append((char)('A' + i));
                alphabet[i]--;
                flag = true;
            }
            if(flag) {
                break;
            }
        }
        if (sb.toString().length() == 0) {
            return str;
        }
        return makePalindrome(alphabet, str.substring(0, str.length() / 2) + sb.toString() + str.substring(str.length() / 2));
    }
}

