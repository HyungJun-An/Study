/*
    백준 13549

    1. 아이디어
        BFS for if (현재위치== 목표 && 방문 X)

    2. 시간복잡도
        O(V+E) = V = 100,000 E = 2V ==> 300,000

    3. 자료구조
        그림 : X
        방문여부 : boolean[]
        결과저장 : Deque<int[]>
 */
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean chk[] = new boolean[100001];

    public static void bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{N,0});

        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cnt = cur[1];

            if(cur_x < 0 || cur_x > 100000 || chk[cur_x]) continue;
            chk[cur_x] = true;

            if(cur_x == K) {
                System.out.println(cnt);
                return;
            }

            int twice = cur_x * 2;
            if(twice <= 100000 && twice>=0 && !chk[twice]) {
                q.addFirst(new int[]{twice, cnt});
            }

            int plus = cur_x + 1;
            if(plus <= 100000  && plus >= 0 && !chk[plus]) {
                q.addLast(new int[]{plus, cnt + 1});
            }
            int minus = cur_x - 1;
            if( minus <= 100000 && minus >= 0 && !chk[minus]) {
                q.addLast(new int[]{minus, cnt + 1});
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
    }
}
