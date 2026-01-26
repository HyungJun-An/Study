/*
    백준 16928

    1. 아이디어
        BFS 주사위
        if( 1~6 이동 ) { if(사다리, 뱀 )이동 if(방문 X) 카운팅 X 
    2. 시간복잡도
        O( V+E ) = V = 10 * 10 = 100, E = 6V  7V ==> 700 

    3. 자료구조
        그림 : int[]
        방문 : boolean[]
        결과저장 : Queue<int[]> 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] chk  = new boolean[101];
    static int[]     move = new int[101];    

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        // add cur_x, cnt
        q.add(new int[]{1,0});
        chk[1] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_x = cur[0];
            if( cur_x == 100 ) {
                System.out.println(cur[1]);
                return;
            }
            
            for ( int i=1; i<=6; i++ ) {
                int nx = cur_x + i;
                
                if (nx > 100 ) continue;
                
                if ( 0 != move[nx]) nx = move[nx];
                
                if( !chk[nx] ) {
                    chk[nx] = true;
                    q.add(new int[]{nx, cur[1] + 1});
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N+M; i++ ) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to   = Integer.parseInt(st.nextToken());
            move[from] = to;
        }
        bfs();
    }
}
