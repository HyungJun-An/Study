import java.io.*;
import java.util.*;
/*
    1. 아이디어
        2중 for문 BFS 2차원 배열 [y][x]== 1 && 방문 X 일 때 queue에 넣기

    2. 시간 복잡도
        BFS : O(V+E) 5V^2 500*500 * 5 1,250,000 < 2억 >> 가능

    3. 자료구조
        그래프 그림 2차원 배열 int[][]
        방문 여부 boolean[][]
        결과 저장 queue int[]
*/

public class Main {

    static int[] dy = {0,1,0,-1};
    static int[] dx = {1,0,-1,0};
    static int[][] graph;
    static boolean[][] chk;
    static int max_cnt = 0;
    static int cnt=0;
    static int n;
    static int m;
    static Queue<int[]> q = new LinkedList<>();
    public static int bfs(int y, int x) {
        int rs = 1;
        q.add(new int[]{y, x});
        while(!q.isEmpty()){
            int[] curr  = q.poll();
            int ey = curr[0];
            int ex = curr[1];
            for(int k=0; k<4; k++){
                int ny = ey + dy[k];
                int nx = ex + dx[k];

                // 범위 확인
                if(0 <= ny && ny < n && 0 <= nx && nx < m) {
                    if(graph[ny][nx] == 1 && !chk[ny][nx]) {
                        chk[ny][nx] = true;
                        q.add(new int[]{ny,nx});
                        rs++;
                    }
                }
            }
        }
        return rs;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        

        
        // 그림그리기
        graph = new int[n][m];
        chk = new boolean[n][m];
        for (int j=0; j<n; j++) {
            st = new StringTokenizer(br.readLine());
            for (int i =0; i<m; i++) {
                graph[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        for (int j=0; j<n; j++) {
            for (int i =0; i<m; i++) {
                if(graph[j][i]==1 && chk[j][i]==false) {
                chk[j][i]=true;
                cnt++;
                max_cnt = Math.max(max_cnt, bfs(j,i));
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max_cnt);
    }
}