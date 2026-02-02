
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    백준 14442
    1. 아이디어
        BFS 최단거리 탐색 
        수정 전 : 벽을 부쉈을 때가 더 빠를 수 있음 --> Deque?  

        수정 후 : 벽을 부수던 부수지 않던 결국 이동하면 cnt+1 임 방문여부가 겹치지 않는게 중요

        if(현재위치 == 방문 X ) 일 때 if(다음경로 == 벽 && 부술 수 있음 )

    2. 시간복잡도
        수정 전 : O(V+E) = 1,000 * 1,000 = 1,000,000 E = 4V ==> 5,000,000 ==> 가능 
        수정 후 : O(V+E) = 1,000 * 1,000 * 10 10,000,000 ==> 50,000,000 ==> 가능 

    3. 자료구조
        그림 : int[N][M]
        방문여부 : boolean[N][M] --> boolean[N][M][K] 
        결과저장 : Deque<int[]{현재위치, 부술 수 있는 횟수}> --> Queue<int[]{현재위치, 부술 수 있는 횟수}>
    
    4. 오답노트
        이 문제는 최단거리를 찾는 것 이기 때문에 가중치가 똑같다
        벽을 부수고 가는것이 더 빠르다고 생각해 Deque를 사용해 벽을 부수는것에 가중치를 뒀음
        하지만 같은 위치에 있더라도 벽을 더 많이 부술 수 있는 자료는 그렇지 않은 자료와 겹치면 안된다.
        벽을 더이상 부술 수 없는 자료가 방문지를 체크해 버리면 벽을 더 부술 수 있는 자료가 그 지점에 도달 할 수 없는 문제가 생기기 때문에
        배열을 하나 더 추가하여 방문여부를 체크하면 해결 된다.
 */

public class Main {
    static int N,M,K;
    static int[][] map;
    static boolean[][][] chk;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};


    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
                    //x,y,w,cnt
        q.add(new int[]{1,1,K,1});
        chk[1][1][K] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt_w = cur[2]; //count of times a wall can be broken
            int cnt = cur[3];

            if(cur_x == M && cur_y == N) {
                System.out.println(cnt);
                return;
            }

            for (int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];

                if(nx>0 && nx <=M && ny>0 && ny<=N) {

                    if(map[ny][nx] == 0 && !chk[ny][nx][cnt_w]) {
                        q.add(new int[]{nx,ny,cnt_w,cnt+1});
                        chk[ny][nx][cnt_w] = true;
                    } else if(cnt_w >0 && map[ny][nx] == 1 && !chk[ny][nx][cnt_w]) {
                        q.add(new int[]{nx,ny,cnt_w-1,cnt+1});
                        chk[ny][nx][cnt_w-1] = true;
                    }
                }

            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][M+1];
        chk = new boolean[N+1][M+1][K+1];

        for(int j=1; j<=N; j++) {
            String line = br.readLine();
            for (int i=1; i<=M; i++) {
                map[j][i] = line.charAt(i-1) - '0';
            }
        }

        bfs();
    }
}
