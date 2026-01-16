/*
    1. 아이디어
        BFS 방식으로 풀자 방문 체크 필요 while문에 if ( S < G && 방문 X ) 올라가기 if ( S > 0 && 방문 &&X) 내려가기

    2. 시간복잡도
        O(V+E) = 3V = 3*10^6 >> 가능

    3. 자료구조
        그림 : N < 10^ 9 ==> int [5] 
        결과 저장 : Queue<int[]>{현재층,횟수}
        방문 여부 : boolean[5] chk;

 */
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
    static int map[] = new int[5];

    static String bfs() {
        Queue<int[]> q = new LinkedList<>();
        int F = map[0];
        int S = map[1];
        int G = map[2];
        int U = map[3];
        int D = map[4];
        boolean chk[] = new boolean[F+1];
        q.add(new int[]{S,0});
        chk[S] = true;
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int now = curr[0];
            int cnt = curr[1];
            if(now == G ) {
                return ""+cnt;
            }

            // UP
            int up = now + U;
            if(up <= F && !chk[up]) {
                q.add(new int[]{up,cnt+1});
                chk[up]=true;
            }
            // Down
            int down = now - D;
            if(down >= 1 && !chk[down]) {
                q.add(new int[]{down,cnt+1});
                chk[down]=true;
            }
        }
        return "use the stairs";
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // drawing map
        for(int i=0; i<5; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bfs());
    }
}
