/*
    1. 아이디어
        - 시작점에서 갈 수 있는 모든 정점을 탐색하는 DFS(깊이 우선 탐색) 활용.
        - 각 위치 x에서 이동 가능한 좌표: (x + jump_distance) 와 (x - jump_distance).
        - 이동하려는 좌표가 배열 범위 [1, n] 내에 있고, 아직 방문하지 않았을(chk[nx] == false) 경우에만 이동.
        - 방문할 때마다 카운트(cnt)를 증가시켜 총 방문 가능한 정점의 개수를 구함.

    2. 시간복잡도
        - O(V + E) 
        - V(정점의 수): n = 100,000
        - E(간선의 수): 각 정점마다 최대 2개의 방향(좌, 우)으로만 이동 가능하므로 E = 2V.
        - 총합: O(3N) ≈ 300,000 (제한 시간 1초 내에 충분히 가능).

    3. 자료구조
        - 돌다리(점프 거리): int[] map (크기 n + 1)
        - 방문 여부 확인: boolean[] chk (크기 n + 1)
        - 방문 정점 개수: int cnt
 */
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int n =0;
    static int map[];
    static int cnt = 1;
    static boolean chk[];
    public static int dfs(int x) {
        chk[x] = true;
        int nx = x + map[x]; // right
        if(nx < n+1 && chk[nx] == false) {
            cnt++;
            chk[nx]=true;
            dfs(nx);
        }
        int bx = x - map[x]; // left
        if(0 < bx && chk[bx] == false ) {
            cnt++;
            chk[bx]=true;
            dfs(bx);
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        map = new int[n+1];
        chk = new boolean[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =1; i<= n; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());
        dfs(x);
        System.out.println(cnt);
    }

}
