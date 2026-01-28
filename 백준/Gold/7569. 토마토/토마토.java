/*
    백준 7569번
    1. 아이디어
        BFS  시뮬레이션 구현 상범 빌딩과의 차이점은 처음 시작 때 큐에 값이 1인걸 모두 넣고 시작해야 함

    2. 시간복잡도
        O(V+E) = V = 100 * 100 * 100 = 1,000,000 E = 6V ==> 7,000,000

    3. 자료구조
        그림 : int[][][]
        결과저장 : Queue<int[]{x,y,z,day}>
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
    static int map[][][];
    static boolean chk[][][];
    static int x,y,z;
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dz = {0,0,0,0,-1,1};

    static Queue<int[]> q = new LinkedList<>();

    public static void bfs() {
        int res = 0;
        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cur_z = cur[2];
            int day = cur[3];
            res = day;

            for (int i=0; i<6; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];
                int nz = cur_z + dz[i];

                if(nx>=0 && nx<x && ny>=0 && ny<y && nz>=0 && nz<z && map[nz][ny][nx] == 0) {
                    map[nz][ny][nx] =1;
                    q.add(new int[]{nx,ny,nz,day+1});
                }
            }
        }
        if(all_done()) {
            System.out.println(res);
        } else {
            System.out.println(-1);
        }
    }
    public static boolean all_done () {
        for(int k=0; k<z; k++) {
            for(int j=0; j<y; j++) {
                for(int i=0; i<x; i++) {
                    if(map[k][j][i] == 0) return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());

        map = new int[z][y][x];
        chk = new boolean[z][y][x];

        for(int k=0; k<z; k++) {
            for(int j=0; j<y; j++ ) {
                st = new StringTokenizer(br.readLine());
                for(int i=0; i<x; i++) {
                    map[k][j][i] = Integer.parseInt(st.nextToken());
                    if( map[k][j][i] == 1 ) 
                        q.add(new int[]{i,j,k,0});
                }
            }
        }
        bfs();
    }
    
}
