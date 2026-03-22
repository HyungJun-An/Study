/*
    백준 1854
    1. 아이디어
        다익스트라 PriorityQueue 를 사용해 가중치 배열 출력하면 됨
        while(!q.isEmpty) {
            if(ans[now]  < weight) continue;
            for(Edge next: node.get(now)) {
                if(ans[next.next] > ans[now] + weight) {
                    ans[next.next] = ans[now] + weight // 가중치 업데이트
                    q.add(new Edge(next.next,ans[next.next]));
                }
            }
            ans[k] 출력
        }
    2. 시간복잡도
        O(ElogV) 250,000 log 1,000

    3. 자료구조
        노드정보: ArrayList<ArrayList<Edege>>
        가중치배열: int[] // 초기값 = Integer.MAX_VALUE
        탐색도구: PriorityQueue<Edge>
        노드클래스: 노드클래스 : Edge {int d 목적지, int w 가중치}

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