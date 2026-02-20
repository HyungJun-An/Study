/*
    백준 1920
    1. 아이디어
        이진 탐색을 통해 문제를 풀어보자

    2. 시간복잡도
        O(nlogn) => 100,000 * log 100,000

    3. 자료구조
        숫자배열: int[]
        찾을 수 배열 int[]
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        int M = Integer.parseInt(br.readLine());
        int[] S = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0; i<M; i++) {
            int start = 0;
            int end = N-1;
            boolean find = false;
            while(start <= end) {
                int mid = (start+end) /2;
                if(A[mid] > S[i]) {
                    end = mid-1;
                } else if (A[mid] < S[i]) {
                    start = mid+1;
                } else {
                    find = true;
                    break;
                }
            }
            System.out.println(find ? 1 : 0);
        }
    }
}