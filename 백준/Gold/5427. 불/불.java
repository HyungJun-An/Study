/*
    백준 5427

    1. 아이디어
        BFS 불은 1초마다 상하좌우 번짐, 상근이는 이동하는데 1초 걸림 
        1. 출구가 있는지부터 체크
        2. 불을 먼저 붙이고 이동 이후 빈공간 및 방문여부 체크
        3. 결과출력
        . 빈공간
        # 벽
        @ 출발위치
        * 불
    2. 시간복잡도
        O(V+E) V = w * h = 1,000 * 1,000 = 1,000,000 E = 4V ==> 5,000,000 

    3. 자료구조
        그림: char[][]
        방문여부: boolean[][]
        결과저장: Queue<int[]{현재위치, 시간}>
        불 : Queue<int[]{현재위치, 시간}>
    
    4. 오답노트
        처음에 Queue에 시간정보를 넣어서 시간을 동기화 하려고했지만 그냥 map에서 불을 붙은걸 구현하는 시뮬이 더 간단해보임
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int N, w, h;
    static char[][] map = new char[1000][1000];
    static boolean[][] chk = new boolean[1000][1000];
    static Queue<int[]> fire = new LinkedList<>();
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void bfs() {
        while (!q.isEmpty()) {
            
            // 1. 불의 확산 (현재 큐에 들어있는 '1초치' 불만 처리)
            int fireSize = fire.size();
            for (int i = 0; i < fireSize; i++) {
                int[] curF = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = curF[0] + dx[d];
                    int ny = curF[1] + dy[d];
    
                    if (nx >= 0 && nx < w && ny >= 0 && ny < h) {
                        // 빈 공간이나 상근이 시작점이면 불이 번짐
                        if (map[ny][nx] == '.' || map[ny][nx] == '@') {
                            map[ny][nx] = '*'; 
                            fire.add(new int[]{nx, ny});
                        }
                    }
                }
            }
    
            // 2. 상근이의 이동 (현재 큐에 들어있는 '1초치' 이동만 처리)
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int[] curP = q.poll();
                int cx = curP[0];
                int cy = curP[1];
                int time = curP[2];
    
                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];
    
                    // [성공] 맵의 경계를 넘어가는 순간이 탈출!
                    if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
                        System.out.println(time + 1);
                        return;
                    }
    
                    // [조건] 빈 공간이고, 방문한 적 없으며, 불이 없는(*) 곳
                    if (map[ny][nx] == '.' && !chk[ny][nx]) {
                        chk[ny][nx] = true;
                        q.add(new int[]{nx, ny, time + 1});
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); 
        
        for(int k=0; k<T; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            
            // 매 케이스마다 초기화 필수!
            map = new char[h][w];
            chk = new boolean[h][w];
            q.clear();
            fire.clear();

            for(int j=0; j<h; j++) {
                String line = br.readLine();
                for(int i=0; i<w; i++) {
                    map[j][i] = line.charAt(i);
                    if (map[j][i] == '@') {
                        q.add(new int[]{i, j, 0});
                        chk[j][i] = true; // 시작 지점 방문 처리
                    } else if (map[j][i] == '*') {
                        fire.add(new int[]{i, j});
                    }
                }
            }
            bfs();
        }
    }
}
