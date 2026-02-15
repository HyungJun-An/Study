/*
    백준 1517
    1. 아이디어
    2. 시간복잡도
    3. 자료구조
 */
import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static int[] tmp;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        tmp = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        merge_sort(A,0,N-1);
        System.out.println(ans);
    }
    static void merge_sort(int[] A,int S, int E) {
        if(S>=E) {
            return;
        }
        int M = (S+E) /2;

        merge_sort(A,S,M); // 잘게 나누기
        merge_sort(A,M+1,E);

        for(int i=S; i<=E; i++) {
            tmp[i] = A[i];
        }
        int k = S;
        int idx1 = S;
        int idx2 = M+1;

        while(idx1 <=M && idx2 <=E) {
            if(tmp[idx1] <= tmp[idx2]) {
                A[k++] = tmp[idx1++];
            } else{
                A[k++] = tmp[idx2++];
                ans += M-idx1+1;
            }
        }   
        while(idx1<=M) A[k++] = tmp[idx1++];
        while(idx2<=E) A[k++] = tmp[idx2++];
    }
}