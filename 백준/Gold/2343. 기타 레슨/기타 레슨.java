/*
    백준 2343
    1. 아이디어
        블루레이 용량을 start와 end 사이에서 이분 탐색하며 if(sum + A[i] > mid) 조건이 될 때마다 
        블루레이 개수를 추가해 목표 개수 count에 적합한 최솟값을 찾는 방식.
    2. 시간복잡도
        O(nlogn)
    3. 자료구조
        입력값 저장: int[]

*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        int[] A = new int[N+1];
        int[] S = new int[N+1];
        st = new StringTokenizer(br.readLine());
        int start=0;
        int end=0;
        for(int i=1; i<=N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if( start< A[i]) start = A[i];
            end += A[i];
        }
        int result =0;
        while(start<=end) {
            int mid = (start + end)/2;
            int sum = 0;
            int cnt = 0;
            for(int i=1; i<=N; i++) {
                if(sum + A[i] > mid) {
                    cnt++;
                    sum=0;
                }
                sum += A[i];
            }
            if(sum!=0){
                cnt++;
            }
            if(cnt<=count) {
                result = mid;
                end = mid -1;
            } else {
                start = mid +1;
            }
        }
        System.out.println(result);
    }
}
