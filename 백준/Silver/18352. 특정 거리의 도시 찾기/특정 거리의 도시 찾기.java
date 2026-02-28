/*
    백준 13852
    1. 아이디어
        BFS를 순회하며 횟수가 k일때 현재지점이 m 일 때 출력
        if(현재지점==m && !visited[현재지점] && 현재횟수==k) syso(현재지점)
    2. 시간복잡도
        O(V+E) = N + M = 300,000 + 1,000,000 = 1,300,000
    3. 자료구조
        노드정보: Node<int[]> cur, next
        벙문여부 : boolean[]
        결과저장: Queue<Integer>

*/
import java.io.*;
import java.util.*;

public class Main { 
    static int n, m, k, x;
    static ArrayList<Integer>[] adj;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v); // 단방향 도로
        }

        bfs();
    }

    public static void bfs() {

        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        dist[x] = 0; // 시작점 거리는 0

        List<Integer> answer = new ArrayList<>();

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (dist[cur] > k) break; 
            
            if (dist[cur] == k) {
                answer.add(cur);
                continue;
            }

            for (int next : adj[cur]) {
                if (dist[next] == -1) { // 아직 방문 안 했다면
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }

        // 결과 출력
        if (answer.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(answer); // 오름차순 정렬
            StringBuilder sb = new StringBuilder();
            for (int city : answer) sb.append(city).append("\n");
            System.out.print(sb);
        }
    }
}