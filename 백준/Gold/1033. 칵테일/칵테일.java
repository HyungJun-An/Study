import java.io.*;
import java.util.*;

// 재료 간의 연결 정보와 비율을 담을 클래스
class Node {
    int b, p, q;
    Node(int b, int p, int q) {
        this.b = b;
        this.p = p;
        this.q = q;
    }
}

public class Main {
    static ArrayList<Node>[] adj;
    static long[] result;
    static boolean[] visited;
    static long lcm = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(1);
            return;
        }

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        result = new long[n];
        visited = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            // 양방향 그래프 생성
            adj[a].add(new Node(b, p, q));
            adj[b].add(new Node(a, q, p));

            // 모든 p, q를 고려한 전체 최소공배수 업데이트
            // 다음 노드 값 = 현재값 * q / p 이므로, 분모 p를 없애기 위해 p가 필요함
            lcm *= (p * q / gcd(p, q)); 
        }

        // 0번 재료를 LCM으로 설정하고 DFS 시작
        result[0] = lcm;
        dfs(0);

        // 결과값들의 최대공약수를 구해 최소 정수비로 만들기
        long finalGcd = result[0];
        for (int i = 1; i < n; i++) {
            finalGcd = gcd(finalGcd, result[i]);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i] / finalGcd).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    // DFS를 통해 비율에 따른 값 전파
    static void dfs(int cur) {
        visited[cur] = true;
        for (Node node : adj[cur]) {
            if (!visited[node.b]) {
                // 다음 노드 값 = 현재 노드 값 * (q / p)
                result[node.b] = result[cur] * node.q / node.p;
                dfs(node.b);
            }
        }
    }

    // 최대공약수 (유클리드 호제법)
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}