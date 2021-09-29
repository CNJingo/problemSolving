import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int initialMoney = Integer.parseInt(st.nextToken());

        int[] stockPrices = new int[14];

        stockPrices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int junHyun = initialMoney;
        int junHyunStock = 0;
        int sungMin = initialMoney;
        int sungMinStock = 0;
        int descending = 0;
        int ascending = 0;
        for (int i = 0; i < stockPrices.length; i++) {
            if (junHyun >= stockPrices[i]) {
                junHyunStock += junHyun / stockPrices[i];
                junHyun %= stockPrices[i];
            }
            if (i == 0 || stockPrices[i] == stockPrices[i - 1]) {
                continue;
            }

            if (i != 0 && stockPrices[i] < stockPrices[i - 1]) {
                descending++;
                ascending = 0;
            } else {
                ascending++;
                descending = 0;
            }
            if (descending >= 3) {
                if (sungMin >= stockPrices[i]) {
                    sungMinStock += sungMin / stockPrices[i];
                    sungMin %= stockPrices[i];
                }
            }
            if (ascending >= 3) {
                sungMin += sungMinStock * stockPrices[i];
                sungMinStock = 0;
            }
        }

        int resultJunHyun = junHyun + junHyunStock * stockPrices[13];
        int resultSungMin = sungMin + sungMinStock * stockPrices[13];
        if (resultJunHyun > resultSungMin) {
            System.out.println("BNP");
        } else if (resultJunHyun == resultSungMin) {
            System.out.println("SAMESAME");
        } else {
            System.out.println("TIMING");
        }



    }
}
