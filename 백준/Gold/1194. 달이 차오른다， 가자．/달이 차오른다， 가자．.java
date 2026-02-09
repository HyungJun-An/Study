/*
    백준 1194
    1. 아이디어
        방문 여부를 어떻게 체크해야 될 지 생각해봐야 함
        1. 열쇠를 집으면 소문자를 . 으로 바꾸는게 좋겠음
        2. 각 지점에 열쇠의 개수를 세며 체크를 해봐야 하는게 좋아보임 (열쇠가 없을 때 같은 곳 가지 않게 )

    2. 시간복잡도
        O(V+E) = V = 50 * 50 E = 4V ==> 1000

    3. 자료구조
        그림 : char[][]
        방문여부 : boolean [][][열쇠개수]
        결과저장 : Queue<int[]{현재 위치, 이동횟수, 열쇠종류}>  //000000 a만 있을 때 100000
 */
import java.io.*;
import java.util.*;

public class Main {
    static char map[][];
    static boolean chk[][][];
    static int N,M;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void bfs(int y, int x) { 
        Map<Character,Integer> key = new HashMap<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y,0,0});
        chk[y][x][0] = true;

        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt_m = cur[2];
            int key_c = cur[3];

            if(map[cur_y][cur_x] == '1') {
                System.out.println(cnt_m);
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];
                
                if(nx>=0 && nx<M && ny>=0 && ny<N && map[ny][nx]!='#') {
                    char next_c = map[ny][nx];
                    int next_key = key_c;
                    
                    // (a-f)
                    if(next_c >='a' && next_c<='f') {
                        next_key |= (1<< (next_c - 'a'));
                    }
                    // (A-F)
                    if(next_c>='A' && next_c<='F') {
                        if((next_key & (1 << (next_c - 'A'))) == 0) continue;
                    }
                    // chk
                    if(!chk[ny][nx][next_key]) {
                        chk[ny][nx][next_key] = true;
                        q.add(new int[]{nx,ny,cnt_m+1,next_key});
                    }
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        chk = new boolean[N][M][64];
        for(int j =0; j<N; j++) {
            String line = br.readLine();
            for(int i=0;  i<M; i++) {
                map[j][i] = line.charAt(i);
            }
        }
        for(int j=0; j<N; j++) {
            for(int i=0; i<M; i++) {
                if(map[j][i] == '0') {
                    bfs(j,i);
                }
            }
        }
    }
}
