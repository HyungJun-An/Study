/*
    백준 7562번
    1. 아이디어
        BFS 가능 if( 현재 위치!=목적위치 &&  방문X ) cnt++
        탐색 중간에 현재 cnt가 다른 cnt보다 크면 return!

    2. 시간복잡도
        O(V+E) O = 9V = 90000 >> 가능!

    3. 자료구조
        그림 : int [][] // 그림 그릴 필요가 있나?
        방문여부 : chk [][]
        결과저장 : Queue<int [][]>
        횟수 저장 : int[]
 */
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {

    static int[] dx ={-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int size;
    static boolean [][] chk;
    public static int bfs(int[] s, int[]d) {
        if(s[0]==d[0] && s[1]==d[1]) {
            return 0;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{s[0],s[1],0});
        chk[s[1]][s[0]]= true;
        while(!q.isEmpty()){
            int curr[] = q.poll();
            int ex = curr[0];
            int ey = curr[1];
            int cnt = curr[2];
            for(int i=0; i<8; i++) {
                int nx = ex + dx[i];
                int ny = ey + dy[i];

                if(nx == d[0] && ny == d[1]) {
                    return cnt+1;
                }

                if( nx < size && 0 <= nx && 
                    ny < size &&  0 <= ny
                ) {
                    if(!chk[ny][nx]){
                        q.add(new int[]{nx,ny,cnt+1});
                        chk[ny][nx] = true;
                    }
                }
            }
        }
        return 0;
    }
    public static void main(String [] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0; i<n; i++) {
            size = Integer.parseInt(br.readLine());
            chk = new boolean[size][size];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int start[] = new int[]{x,y};
            
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int dest[] = new int[]{x,y};

            System.out.println(bfs(start, dest));
        }

    }
}
