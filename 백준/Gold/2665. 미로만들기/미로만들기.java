/*
    백준 2665

    1. 아이디어
        가중치 탐색 BFS에서 흰 색 먼저 탐방 후 검은색 방도 이후에 탐색 if( 현재 위치 == 1 && 방문 x ) q.addFirst 방문=true

    2. 시간복잡도
        O(V+E) V = 50 * 50 E = 4V ==> 50 * 50 * 5 ==> 12,500

    3. 자료구조
        그림: int[][]
        방문여부: boolean[][]
        결과저장: Deque<int[]{현재위치, 검은색 -> 흰색 횟수}> 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;



public class Main {
    static int N;
    static int[][] map = new int[51][51];
    static boolean[][] chk = new boolean[51][51];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1,1,0});
        chk[1][1] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt = cur[2];
            

            if(cur_x == N && cur_y == N) {
                System.out.println(cnt);
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];

                if (0<nx && nx<=N && 0<ny && ny<=N && !chk[ny][nx]) {
                    // case white room
                    if (map[ny][nx] == 1 ) {
                        q.addFirst(new int[]{nx,ny,cnt});
                        chk[ny][nx] = true;
                    // case black room
                    } else if(map[ny][nx] == 0){
                        map[ny][nx] = 1;
                        q.addLast(new int[]{nx,ny,cnt + 1});
                        chk[ny][nx] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int j=1; j<=N; j++) {
            String line = br.readLine();
            for(int i=1; i<=N; i++) {
                map[j][i] = line.charAt(i-1) - '0';
            }
        }
        bfs();
    }
    
}

