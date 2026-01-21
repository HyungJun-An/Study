/*
    1. 아이디어
        BFS if(현재위치==0&& 방문 X) 생각해보니 하나씩 쭉 훑으면서 0일때만 움직여야 함 상하좌우 이동 및 경계값 확인
    2. 시간복잡도
        O(V+E) = 5V ==> 5,000,000 ==> 가능
    3. 자료구조
        그림: int[][]
        방문여부: boolean[][]
        결괴저장: Queue<int[]>

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][]map;
    static boolean[][]chk;
    static int N;
    static int M;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int res = 0;
    public static int bfs(int y, int x) {
        
        res++;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,1});
        chk[y][x] = true;
        while(!q.isEmpty()) {

            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt = cur[2];


            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];

                if (nx < 0 ) {
                    nx = nx+ M;
                }

                if (ny < 0 ) {
                    ny = ny + N;
                }
                if (nx >= M) {
                    nx = nx - M;
                }

                if (ny >= N) {
                    ny = ny - N;
                }
                if(map[ny][nx] == 0 && !chk[ny][nx]) {
                    chk[ny][nx] = true;
                    q.add(new int[]{nx,ny,cnt + 1});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        chk = new boolean[N][M];

        for(int j=0; j<N; j++) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        for(int j =0; j < N; j++) {
            for (int i=0; i< M; i++) {
                if(map[j][i] == 0 && !chk[j][i]) {
                    bfs(j,i);
                }
            }
        }
        System.out.println(res);
    }
}
