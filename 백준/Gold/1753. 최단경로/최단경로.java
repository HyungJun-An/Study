/*
    백준 1753
    1. 아이디어
    다익스트라로 구현 시작점 배열을 0 으로 두고 나머지는 Integer.max로 채워두며 각 인덱스에서 시작점까지 가중치를 더하며 최소값으로 경로배열을 업데이트
    PriorityQueue를 이용한 DFS탐색 방식으로 업데이트 우선순위 탐색을 통해 진행 일반 Queue를 사용하면 메모리문제 발생
    while(!q.isEmpty) {
        int now = q.poll();
        for(Edge next: node) {
            가중치배열[next.d] = Math.Min(가중치배열[now]+next.w, 가중치배열[next.d])
        }
    }
    2. 시간복잡도
    O(ElogV) = 300,000 log 20,000 

    3. 자료구조
    노드정보: ArrayList<ArrayList<Edge>>
    가중치배열 : int[] // 이때 초기값을 Integer.MAX_VALUE로 채워야 함
    탐색도구 : PriorityQueue<Integer>
    노드클래스 : Edge {int d 목적지, int w 가중치}
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(br.readLine());
        int[] ans = new int[v+1];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[s] = 0;
        ArrayList<ArrayList<Edge>> node = new ArrayList<>();
        for(int i=0; i<=v; i++) node.add(new ArrayList<>());
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            node.get(u).add(new Edge(d, w));
        }
        PriorityQueue<Edge> q = new PriorityQueue<>((e1,e2) -> e1.w - e2.w);
        q.add(new Edge(s,0));
        while(!q.isEmpty()) {
            Edge nowEdge = q.poll();
            int now = nowEdge.d;
            int weight = nowEdge.w;

            if(ans[now] < weight) continue;
            
            for(Edge next: node.get(now)) {
                int d = next.d;
                int w = next.w;
                // ans[d] = Math.min(ans[d], ans[now]+w); // 갱신되든 안되는 넣으면 큐에 너무 많은 데이터가 들어가 메모리 초과 발생
                if(ans[d] > ans[now] + w) { // 값이 갱신 될 때만 큐에 넣어줘야 함 
                    ans[d] = ans[now] +w; // 
                    q.add(new Edge(d,ans[d])); //가중치를 업데이트 해서 넣어줘야 함
                }
            }
        }
        for( int i=1; i<ans.length; i++) {
            System.out.println(ans[i]==Integer.MAX_VALUE ? "INF" : ans[i]);
        }
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