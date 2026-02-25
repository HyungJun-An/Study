/*
    백준 
    1. 아이디어
        입력값 두 개 중 큰 수 하나씩 증가하며 곱한 값이 작은값으로 나눴을 때 나머지가 0일때가 최소공배수
    2. 시간복잡도
        O(nlogn * 1,000) = 45,000 * 1,000 = 45,000,000
    3. 자료구조
        결과저장 : int
*/
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result =0;
        StringTokenizer st;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int max = Math.max(f,s);
            int min = Math.min(f,s);
            
            for(int j=1; j<=min; j++) {
                int mul = max * j;
                if(mul % min == 0) {
                    System.out.println(mul);
                    break;
                }
            }
        }
    }
}