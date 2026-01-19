/* 
    1. 아이디어
        BFS if( N<K ) 일때 방문X 이면서 *2 or X -1 or X +1 while문 돌리면 최적의 경로
        
    2. 시간복잡도
        O(V+E) 선택지(Edge)는 3가지 4V ==> 400,000 >> 가능

    3. 자료구조
        그림: 딱히 그릴 필요는 없어보임
        방문여부: boolean[] chk
        결과저장: Queue<int[]> q {현재위치, 횟수}
 */

import java.io.*;
import java.util.*;




public class Main {
    static int X;
    static int N;
    static boolean[] chk;
    static int max = 100001;
    public static int bfs(int s, int d) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{s,0});
        chk[s]=true;

        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cnt = cur[1];

            if(cur_x == d) {
                return cnt;
            }

            int twice = cur_x * 2; // Watching out of index
                                // the reason why chk[2N]
            if(twice < max) {
                
                if(!chk[twice]) {
                    chk[twice]= true;
                    q.add(new int[]{twice,cnt+1});
                }
            }

            int minus = cur_x -1;
            if( minus >= 0 && !chk[minus] ) {
                chk[minus] = true;
                q.add(new int[]{minus, cnt+1});
            }

            int plus = cur_x + 1;

            if(plus < max ) {
                if(!chk[plus]) {
                    chk[plus] = true;
                    q.add(new int[]{plus,cnt+1});
                }
            }
            
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        chk = new boolean[max];
        System.out.println(bfs(X,N));
    }
}
