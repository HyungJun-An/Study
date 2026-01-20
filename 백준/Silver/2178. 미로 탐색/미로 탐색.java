/*
    백준 2178
    1. 아이디어
        BFS 사용 if(현재위치=={N,M}) return cnt if (다음위치==1 && 방문 X) 이동 cnt+1

    2. 시간복잡도
        O(V+E) = 5V  = 500 >> 가능

    3. 자료구조
        그림 : int[][]
        방문여부 : boolean[][]
        결과저장 : Queue<int[]>
 */
import java.io.*;
import java.util.*;

public class Main {
    static int map[][];
    static boolean chk [][];
    static int N;
    static int M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        chk[0][0] = true;
        while(!q.isEmpty()) {

            int cur[] = q.poll();

            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt = cur[2];

            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];
                
                // check boundary
                if(0<=nx && nx < M && 0<=ny && ny< N) {

                    if(nx==M-1 && ny == N-1) {
                        return cnt+1;
                    }
                    
                    if(map[ny][nx] == 1 && !chk[ny][nx]) {
                        chk[ny][nx] = true;
                        q.add(new int[] {nx,ny,cnt+1} );
                    }
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
            String line = br.readLine();
            for(int i =0; i<M; i++) {
                map[j][i] = line.charAt(i) - '0' ; // char to int
            }
        }
        
        System.out.println(bfs());
    }
}
