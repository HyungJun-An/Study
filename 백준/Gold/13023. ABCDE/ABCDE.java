/*
    백준 
    1. 아이디어
        ArrayList<Integer>[] 로 0~n 중에 길이가 n-1이면 1 출력
    2. 시간복잡도
        O(N) ==> 2,000
    3. 자료구조
        ArrayList<Integer>[]

*/
import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Integer>[] lists;
    private static boolean chk[];
    private static boolean arrived = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n];
        for(int i=0; i<n; i++) lists[i] = new ArrayList<>();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            lists[left].add(right);
            lists[right].add(left);
        }
        chk = new boolean[n];
        for(int i=0; i<n; i++) {
            dfs(i,0);
            if(arrived) break;
        }
        System.out.println(arrived? 1: 0);
    }
    static void dfs(int now, int depth) {
        if (depth == 4 ){
            arrived = true;
            return;
        }
        chk[now] = true;
        for(int next: lists[now]) {
            if(!chk[next]){
                dfs(next,depth+1);
            }
            if(arrived) return;
        }
        chk[now] = false;
    }
}