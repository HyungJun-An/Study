/*
    1. 아이디어
        BFS A,B 를 얼만큼 부었는지 알면 C는 정해짐
        경우의 수
        A -> B
        A -> C
        B -> A
        B -> C
        C -> A
        C -> B

    2. 시간복잡도
        O(V+E) = 
            V = 200 * 200 == 40,000  
            E = 6V == 6 * 40,000 == 240,000
            O = 280,000 

    3. 자료구조
        방문여부: boolean[][]
        결과저장 Queue<int[]>
        결과출력 Queue<Integer>
 */
import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] chk = new boolean[201][201];
    static int A, B, C;
    static int[] from = {0,0,1,1,2,2};
    static int[] to   = {1,2,0,2,0,1};
    static TreeSet<Integer> res = new TreeSet<>();

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[] limit = {A,B,C};
        q.add(new int[]{0,0});
        chk[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_A = cur[0];
            int cur_B = cur[1];
            int cur_C = C - cur_A - cur_B;

            if(cur_A == 0) {
                res.add(cur_C);
            }

            for(int i=0; i<6; i++ ) {
                int[] next = {cur_A, cur_B, cur_C};

                // calculating how amount
                int amount = Math.min(next[from[i]],limit[to[i]]-next[to[i]]);

                // pouring amount
                next[from[i]] -= amount;
                next[to[i]] += amount;

                if(!chk[next[0]][next[1]]) {
                    chk[next[0]][next[1]] = true;
                    q.add(new int[]{next[0],next[1]});
                }
            }
        }
        

        for(int val : res) {
            System.out.print(val + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        bfs();
    }
}
