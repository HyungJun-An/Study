
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    백준1600
    1. 아이디어
        수정 전: BFS문제 인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다. 
                벽 부수고 이동하기 2와 비슷함 Queue 사용 3차원배열로 방문여부 체크
    2. 시간복잡도
        수정 전: O(V+E) V = 200 * 200 * 30 = 1,200,000 E = 12V ==> 12 * 1,200,000 ==> 14,400,000 ==> 가능

    3. 자료구조
        수정 전: 그림 int[][]
                방문여부 boolean[][][]
                자료구조: Queue<int[]{현재위치,이동횟수, 나이트 무빙 횟수}

 */
public class Main {
    static int K,W,H;
    static int[][] map;
    static boolean[][][] chk;
    /* knight moving */
    static int[] kx = {-2,-1,1,2,2,1,-1,-2}; 
    static int[] ky = {1,2,2,1,-1,-2,-2,-1};
    /* monkey moving */
    static int[] mx = {-1,0,1,0};
    static int[] my = {0,1,0,-1};

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0,K});
        chk[0][0][K] = true;

        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt   = cur[2];
            int chance= cur[3];

            if(cur_x==W-1 && cur_y == H-1) {
                System.out.println(cnt);
                return;
            }
            
            // moving like monkey
            for(int i=0; i<4; i++) { 
                int nx = cur_x + mx[i];
                int ny = cur_y + my[i];
                if( nx>=0 && nx<=W-1 && ny>=0 && ny<=H-1 && map[ny][nx] == 0 &&!chk[ny][nx][chance]) {
                    q.add(new int[]{nx,ny,cnt+1, chance});
                    chk[ny][nx][chance] = true;
                }
            }

            if(chance > 0 ) { //moving like knight
                for(int i=0; i<8; i++) {
                    int nx = cur_x + kx[i];
                    int ny = cur_y + ky[i];
                    int use = chance -1 ; //use knight moving chance
                    if( nx>=0 && nx<=W-1 && ny>=0 && ny<=H-1 && map[ny][nx] == 0 && !chk[ny][nx][use]) {
                        q.add(new int[]{nx,ny,cnt+1, use});
                        chk[ny][nx][use] = true;
                    }
                }   
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        chk = new boolean[H][W][K+1];
        for(int j=0; j<H; j++) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<W; i++) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }
}
