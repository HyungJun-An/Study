
/*
    백준 
    1. 아이디어
        DFS, BFS 로 각각 구현

    2. 시간복잡도
        O(V+E) ==> V = 1,000 E = 2V ==> 3V

    3. 자료구조
        그림: int[]
        방문여부: boolean[]
        결과저장:
            Queue<Integer> bq
            ArrayList<Integer>[] dq
*/
import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] q;
    static boolean chk[];
    static int n, m, s;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        q = new ArrayList[n+1];
        for (int i = 1; i < n + 1; i++)
            q[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            q[left].add(right);
            q[right].add(left);
        }
        for (int i = 1; i <= n; i++)
            Collections.sort(q[i]);
        chk = new boolean[n + 1];
        dfs(s);
        chk = new boolean[n + 1];
        sb.append("\n");
        bfs(s);
        System.out.println(sb);
    }

    static void dfs(int now) {
        chk[now] = true;
        sb.append(now).append(" ");
        for (int next : q[now]) {
            if (!chk[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int now) {
        Queue<Integer> bq = new LinkedList<>();
        bq.add(now);
        chk[now] = true;
        while (!bq.isEmpty()) {
            int cur = bq.poll();
            sb.append(cur).append(" ");
            for (int next : q[cur]) {
                if (!chk[next]) {
                    bq.add(next);
                    chk[next] = true;
                }
            }
        }
    }
}