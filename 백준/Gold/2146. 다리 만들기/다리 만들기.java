/*
백준 2146

1. 아이디어
가장 짧은 다리를 잇는 것이 목표
        육지의 끝에서 bfs가 도는게 맞는것 같음
        1. 탐색 시작점을 어떻게 잡을 지 ==> 일단 각 섬에 라벨링 필요
        2. 이어져 있는 섬인지 어떻게 확인 할 지 ==> 라벨링을 통해 같은 섬인지 확인

    2. 시간복잡도
        O(V+E) == 100 * 100 * 10,000  V = 10,000 E = 4V ==> 50,000
        
        3. 자료구조
        그림 : int[][]
        방문여부: boolean[][]
        결과저장: Queue<int[]{현재위치,섬 번호}
*/
import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int map[][];
    static boolean chk[][];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int dist = Integer.MAX_VALUE;

    public static void bridge(int y, int x, int id) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,0});
        boolean bfs_chk[][] = new boolean[N][N];
        bfs_chk[y][x] = true;

        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cur_dist  = cur[2];

            if (cur_dist >= dist) return;

            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];

                if(nx>=0 && nx<N && ny>=0 && ny<N &&!bfs_chk[ny][nx]) {
                    if(map[ny][nx] == id) continue;
                    bfs_chk[ny][nx] = true;
                    if(map[ny][nx] == 0) {
                        q.add(new int[]{nx,ny,cur_dist+1});
                    } else {
                        dist = Math.min(dist,cur_dist);
                        return;
                    }
                }
            }
        }
    }
    
    public static void labeling(int y, int x, int id) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        chk[y][x] = true;
        map[y][x] = id;
        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];

            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];

                if(nx>=0&& nx<N && ny>=0 && ny<N && map[ny][nx] == 1 && !chk[ny][nx]) {
                    map[ny][nx] = id;
                    chk[ny][nx] = true;
                    q.add(new int[]{nx,ny});
                }
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        chk = new boolean[N][N];
        StringTokenizer st;

        // initializing
        for(int j=0; j<N; j++) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                map[j][i]=Integer.parseInt(st.nextToken());
            }
        }

        // labeling
        int islandId = 1;
        for(int j=0; j<N; j++) {
            for(int i=0; i<N; i++) {
                if(map[j][i] == 1 && !chk[j][i]) {
                    labeling(j,i,islandId++);
                }
            }
        }
        chk = new boolean[N][N];
        // shortest bridge
        for(int j=0; j<N; j++) {
            for(int i=0; i<N; i++) {
                if(map[j][i] > 1 && !chk[j][i]) {
                    bridge(j,i,map[j][i]);
                }
            }
        }
        System.out.println(dist);
    }
}
