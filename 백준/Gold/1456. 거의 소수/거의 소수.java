/*
    백준 1456
    1. 아이디어
        에라토스테네스의 소수 판별 법으로 소수 배열을 구하고 소수의 제곱이 m보다 작은지 확인
        1 10
        (2,4) (2,8) (3,9) 3개
    2. 시간복잡도
        O(N) N = 10^7
    3. 자료구조
        중복제거: booelan[]
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        int limit = 10000000; // squrt(10^14) 소수의 특성상 루트 10^14 까지만 비교하면 된다.
        boolean[] rm = new boolean[limit + 1];
        rm[1] = true;
        //에라토스테네스의 소수 판별법
        for(int i=2; i*i<=limit; i++) {
            if(rm[i]) continue;
            for(int j=i*i; j<=limit; j+=i) {
                rm[j] = true; //rm[j]는 소수가 아님
            }
        } 
        long cnt=0;
        for(int i=2; i<=limit; i++) {
            if(!rm[i]) {
                long temp = i;
                while((double)i <= (double)m / temp) { //만약 i(10^6) *temp(10^13) 은 Long 이 담지 못하기 때문에 double로 안전하게 계산
                    temp *= i;
                    if( temp>=n) cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}