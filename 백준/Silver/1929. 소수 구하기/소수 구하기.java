/*
    백준 1929
    1. 아이디어
        에라토스테네스의 체를 사용하여 소수를 구해보자
        1~30 까지 일 때 숫자를 배열로 정리 후 현재 지점에 배수를 모두 삭제!
    2. 시간복잡도
        O(nlogn) = 1,000,000 * log 1,000,000
        N^2이라 생각 될 수 있는데 바깥쪽 for문은 배수를 삭제하는 과정에서 많이 스킵 됨
    3. 자료구조
        삭제처리 : boolean[]
        if(!rm[i]) 일때만 i값 출력
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] rm = new boolean[m+1];

        for(int i=2; i*i<=m; i++) {
            if(rm[i]) continue;
            for(int j=i*i; j<=m; j+=i) {
                rm[j] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=n; i<=m; i++) {
            if(i < 2) continue; // 1 이하는 출력 X
            if(!rm[i]) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}