/*
    1. 아이디어
        L : 층 수 R: 각 층의 행 C: 각 층의 열
        각 층의 층과 열이 이동 가능해야만 상 하 로 이동가능
        BFS 접근 if(범위 안)&& 방문 X && 현재위치 !='#') cnt++ 
        
    2. 시간복잡도
        O(V+E) 30 * 30 * 30 * 6 = 162,000 >> 가능

    3. 자료구조
        그림 : char[][][]
        방문여부 : boolean[][][]
        결과 저장 : Queue<int[]> {현재위치, 이동횟수}
 */
import java.io.*;
import java.util.*;

public class Main {
    static char[][][] map;
    static boolean[][][] chk;
    static int L, R, C ;
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};

    public static void bfs(int x, int y, int z) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,z,0});
        chk[z][y][x] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ex = cur[0];
            int ey = cur[1];
            int ez = cur[2];
            int cnt= cur[3];

            if(map[ez][ey][ex] == 'E') {
                System.out.println("Escaped in " + cnt + " minute(s).");
                return;
            }
            for(int i=0; i<6; i++) {
                int nx = ex + dx[i];
                int ny = ey + dy[i];
                int nz = ez + dz[i];

                // check boundary
                if(nz >=0 && nz< L && ny >=0 && ny<R && nx >=0 && nx<C ) {
                    // check visited && not #
                    if(!chk[nz][ny][nx] && map[nz][ny][nx] !='#') {
                        q.add(new int[]{nx,ny,nz,cnt+1});
                        chk[nz][ny][nx] = true;
                    }
                }
            }
        }
        System.out.println("Trapped!");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L==0 && R==0 && C==0) break;

            map = new char[L][R][C];
            chk = new boolean[L][R][C];
            
            int z=0, y=0, x=0;

            for(int k=0; k<L; k++) {
                for(int j=0; j<R; j++) {
                    String floor = br.readLine();
                    for(int i=0; i<C; i++) {
                        map[k][j][i] = floor.charAt(i);
                        if(map[k][j][i] == 'S') {
                            z = k; y = j; x = i;
                        }
                    }
                }
                br.readLine(); // empty line
            }
            bfs(x,y,z);
                
        }
    }
}
