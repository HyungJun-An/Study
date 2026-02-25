/*
    백준 1850
    1. 아이디어
        입력값 A,B의 최대 공약수를 구한 뒤 최대공약수의 수만큼 1을 출력
    2. 시간복잡도

    3. 자료구조
        결과 저장: int
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        long a = sc.nextLong();
        long b = sc.nextLong();
        
        long resultSize = gcd(a, b);
        while( resultSize > 0) {
            sb.append(1);
            resultSize--;
        }
        System.out.println(sb);

    }

    public static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}