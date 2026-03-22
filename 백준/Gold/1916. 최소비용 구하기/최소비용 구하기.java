/*
    백준 1916
    1. 아이디어
    다익스트라 PriorityQueue 를 사용해 가중치 배열중 최소값을 출력하면 됨
    while(!q.isEmpty) {
        if(ans[now]  < weight) continue;
        for(Edge next: node.get(now)) {
            if(ans[next.next] > ans[now] + weight) {
                ans[next.next] = ans[now] + weight // 가중치 업데이트
                q.add(new Edge(next.next,ans[next.next]));
            }
        }
        ans[]배열 최소값 출력
    }
    2. 시간복잡도
    O(ElogE) == 10,000 * log10,000 

    3. 자료구조
    노드정보 : ArrayList<ArrayList<Edge>>
    가중치배열 : int[]  초기값 INTEGER.MAX_VALUE
    탐색도구 : PriorityQueue<Edege>
    노드클래스 : Edge {int d 목적지, int w 가중치}

*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Edge>> node = new ArrayList<>();
        for(int i=0; i<=n; i++) node.add(new ArrayList<>());
        int[] ans = new int[n+1];
        Arrays.fill(ans, Integer.MAX_VALUE);
        StringTokenizer st;
        for(int i=0; i<m; i++) {
             st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            node.get(u).add(new Edge(v, w));
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        ans[s] = 0;
        PriorityQueue<Edge> q = new PriorityQueue<>((e1,e2) -> e1.w - e2.w);
        q.add(new Edge(s, 0));
        while(!q.isEmpty()) {
            Edge nowNode = q.poll();
            int now = nowNode.d;
            int weight = nowNode.w;
            if(ans[now] < weight) continue;
            for(Edge next: node.get(now)) {
                int d = next.d;
                int w = next.w;
                if(ans[d] > ans[now] + w) {
                    ans[d] = ans[now] + w;
                    q.add(new Edge(d,ans[d]));
                }
            }
        }
        System.out.println(ans[e]);
    }
}

class Edge {
    int d;
    int w;
    Edge(int d, int w ) {
        this.d = d;
        this.w = w;
    }
}