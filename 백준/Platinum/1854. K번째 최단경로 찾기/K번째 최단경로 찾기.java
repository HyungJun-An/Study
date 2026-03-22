/*
    백준 1854 - K번째 최단 경로 찾기
    1. 아이디어
        - 일반 다익스트라는 최단 거리 '하나'만 저장하지만, 이 문제는 'K개'를 저장해야 함.
        - 각 노드마다 우선순위 큐(최대 힙, PriorityQueue<Integer>)를 사용하여 K개의 경로를 유지함.
        - 다익스트라 탐색 중 새로운 경로(nextDist)를 발견했을 때:
            A. 해당 노드의 바구니가 K개 미만이면: 무조건 추가 후 탐색용 PQ에 넣음.
            B. 바구니가 K개 꽉 찼는데, 현재 경로가 K등(peek)보다 작으면: 
               기존 K등을 poll()하고 새 경로를 add()한 뒤 탐색용 PQ에 넣음.
        - 1번 노드에서 시작하는 다익스트라 한 번으로 모든 노드의 K번째 경로를 확정함.

    2. 시간복잡도
        - 다익스트라 기본: O(E log V)
        - 각 간선마다 K개들이 바구니를 관리(log K)하므로: O(E log V * log K)
        - 약 250,000 * 10 * 7 = 약 1,750,000 연산 (2초 제한 내 충분히 가능)

    3. 자료구조
        - 노드 정보: ArrayList<ArrayList<Edge>> (인접 리스트)
        - K등 기록 바구니: PriorityQueue<Integer>[] dists (최대 힙: Collections.reverseOrder())
        - 탐색 도구: PriorityQueue<Edge> pq (최소 힙: 가중치 작은 순서부터 탐색)
        - 데이터 클래스: Edge {int d 목적지 노드, int w 현재까지의 누적 가중치}
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Edge>> node = new ArrayList<>();
        for(int i=0; i<=n; i++) node.add(new ArrayList<>());
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            node.get(u).add(new Edge(v, w));
        }
        PriorityQueue<Integer>[] dists = new PriorityQueue[n+1];
        for(int i=1; i<=n; i++) dists[i] = new PriorityQueue<>(Collections.reverseOrder());
        find(n,k,node,dists);
        for(int i=1; i<=n; i++) {
            // 바구니가 K개만큼 안 찼다는 건 K번째 경로가 없다는 뜻
            if (dists[i].size() < k) {
                System.out.println("-1");
            } else {
                // 최대 힙의 root(peek)는 항상 K번째로 작은 값(K등)임
                System.out.println(dists[i].peek());
            }
        }
    }
    public static void find(int n, int k, ArrayList<ArrayList<Edge>> node, PriorityQueue<Integer>[] dists) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2) -> e1.w - e2.w);
        dists[1].add(0);
        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()) {
            Edge nowEdge = pq.poll();
            int now = nowEdge.d;
            int weight = nowEdge.w;

            //인접 노드 탐색
            for(Edge next : node.get(now)) {
                int nextDist = next.w + weight;  //지금까지 온 거리 + 다음 도로 거리

                //Case A : 아직 K등까지 성적표가 다 차지 않았을 때 (큐가 K개만큼 다 차지 않았을 때는 무조건 추가)
                if (dists[next.d].size() < k) {
                    dists[next.d].add(nextDist);
                    pq.add(new Edge(next.d, nextDist));
                }
                //Case B : K개가 꽉 찼을 때 기존 큐에 K등(가장 큰 값) 보다 새로들어온 값이 작을때
                else if (dists[next.d].peek() > nextDist) {
                    dists[next.d].poll(); // 기존 K값 버리기
                    dists[next.d].add(nextDist); //새로운 K값 넣기
                    pq.add(new Edge(next.d, nextDist)); // 큐에 넣고 다음탐색 진행
                }
            }
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