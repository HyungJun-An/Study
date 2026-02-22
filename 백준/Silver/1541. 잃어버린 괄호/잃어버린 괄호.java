/*
    백준 
    1. 아이디어
        -가 나오면 다음 괄호로 묶어서 계산해야 함
        예시 55 - (50 + 40) = - 45
    2. 시간복잡도
        O(L) = L
    3. 자료구조
        
*/
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. '-' 기준으로 먼저 나눈다.
        String[] groups = br.readLine().split("-");
        int result = Integer.MAX_VALUE; // 초기값 설정

        for (int i = 0; i < groups.length; i++) {
            int tempSum = 0;
            // 2. 각 덩어리 내부의 '+'로 연결된 숫자들을 더한다.
            // split("\\+")를 쓰는 이유는 +가 정규식 예약어이기 때문!
            String[] additions = groups[i].split("\\+");
            for (String num : additions) {
                tempSum += Integer.parseInt(num);
            }

            // 3. 첫 번째 덩어리는 더해주고, 나머지는 뺀다.
            if (result == Integer.MAX_VALUE) {
                result = tempSum;
            } else {
                result -= tempSum;
            }
        }
        System.out.println(result);
    }
}