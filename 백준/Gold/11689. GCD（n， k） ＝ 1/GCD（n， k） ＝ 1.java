/*
    백준 11689
    1. 아이디어
        오일러 피 구하기 에라토스 테네스의 소수 구하기 로직에서 소수 판별 부분을 P[i] = P[i] - P[i] / K
    2. 시간복잡도
        O(n^2) = ( 10 ^ 6 ) ^ 2 = 10 ^ 12  = 1,000,000,000,000
    3. 자료구조
        결과저장: long[]
*/
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long result = n;
        
        for(long p = 2; p*p<=n; p++) {
            if (n % p == 0) {
                // 오일러 피 공식 적용: result = result * (1 - 1/p)
                result = result - (result / p);
                
                // n에서 해당 소인수를 모두 제거합니다.
                while (n % p == 0) {
                    n /= p;
                }
            }
        }
        if (n > 1) {
            result = result - (result / n);
        }

        System.out.println(result);
    }
}