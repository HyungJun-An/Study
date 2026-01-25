/*
백준 9205번

1. 아이디어
        BFS로 이동 이동하기 전 맥주가 있어야 함, 맥주 한 캔 당 50 이동 가능 최대 1000 이동 가능, 박스에는 20개가 최대
        (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)
        if( 맥주>0 && 방문 X) 맥주-- 
        
        2. 시간복잡도
        O(V+E) =  V + E == V^2 + V == (N+2)102 * 102 + 102 ~= 10500
        
        3. 자료구조
        그림 : int [][]
        방문여부 : boolean[][]
        결과저장 : Queue<int[]>
        */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

       public class Main {
           public static int t;
           public static int n;
           public static int[][] map;
           public static boolean[] chk;
           public static int beer = 20;
           
           public static void bfs() {
               Queue<Integer> q = new LinkedList<>();
        q.add(0);
        chk[0] = true;
        while(!q.isEmpty()) {
            int idx = q.poll();
            int cur_x = map[idx][0];
            int cur_y = map[idx][1];

            if(idx == n+1) {
                System.out.println("happy");
                return;
            }

            for (int i=0; i<n+2; i++ ) {
                if(!chk[i]) {
                    int dist = Math.abs(cur_x - map[i][0]) + Math.abs(cur_y -map[i][1]);

                    if(dist<=1000) {
                        chk[i] = true;
                        q.add(i);
                    }
                }
            }
        }
        System.out.println("sad");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int k=0; k<t; k++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n+2][2];
            chk = new boolean[n+2];

            for( int j =0; j<n+2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                map[j][0] = Integer.parseInt(st.nextToken());
                map[j][1] = Integer.parseInt(st.nextToken());
            }
            bfs();            
        }

        
    }
}
