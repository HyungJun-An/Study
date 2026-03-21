/*
    백준 1948
    1. 아이디어
    모든 도로가 일방통행 --> 단방향 노드
    한걸음 - > 로마 
    가장 오래 걸린 사람의 시간 출력
    가장 오래 걸린 시간이 걸린 사람의 경로수 출력 시작 지점부터 도착은 칠하기 x 
    월드나라 도시 개수 : 1 <= N <= 10,000
    월드나라 도로 개수 : 1 <= M <= 100,000
    u = 도시 1 1<= u <= 10,000
    v = 도시 2 1<= v <= 10,000
    w = 도시1 -> 도시2 로 이동시간 1<= w <= 10,000
    s = 한걸음 도시 주소
    e = 로마 도시 주소
    ////////////
    위상정렬로 풀기 예시로 풀기
    진입차수 배열
    0  1  1  1  1  3  2
    임계경로 배열에 다음 노드까지 이동 후 걸린 시간을 idx에 더함 진입차수 배열 --
    0  4  2  3  3  7  12  --> 최대값 12 출력
    ***************
    가장 오래걸린 사람의 도로 개수를 출력하려면 역순 노드로 탐색해야함
    2. 시간복잡도
    O(V+E) = V = 10,000 E = 100,000 ~= 11,000
    3. 자료구조
    노드 정보 : ArrayList<<ArrayList<Edge>>
    역순 노드 : ArrayList<<ArrayList<Edge>>
    진입차수 배열 : int[]
    임계경로 배열 : int[]
*/  
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<ArrayList<Edge>> node = new ArrayList<>(); // 일반 노드
        ArrayList<ArrayList<Edge>> reverse = new ArrayList<>(); //역추적 노드
        int[] dim = new int[n+1]; // 진입차수
        int[] ins = new int[n+1]; // 임계경로

        for(int i=0; i<=n; i++) {
            node.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }
        int m = sc.nextInt();
        for(int i=0; i<m; i++) {
            int u=0,v=0,w=0;
            u = sc.nextInt();
            v = sc.nextInt();
            w = sc.nextInt();
            node.get(u).add(new Edge(v,w));
            reverse.get(v).add(new Edge(u,w));
            dim[v]++;
        }
        int s,e;
        s = sc.nextInt();
        e = sc.nextInt();

        // 임계경로 배열 구하기
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while(!q.isEmpty()) {
            int now = q.poll();
            for(Edge next : node.get(now)) {
                int d = next.d; // 목적지
                int w = next.w; // 가중치

                // 값이 더 클 때 임계경로 배열에 저장
                ins[d] = Math.max(ins[d],ins[now]+w);
                dim[d]--;
                if(dim[d] == 0) {
                    q.add(d);
                }
            }
        }
        // ReverseNode 끝지점에서 역추적
        Queue<Integer> rq = new LinkedList<>();
        rq.add(e);
        int count =0;
        boolean[] visited = new boolean[n+1];
        while(!rq.isEmpty()) {
            int now = rq.poll();
            for(Edge prev : reverse.get(now)) {
                // 이 경로가 기여를 했는가?
                if(ins[now] == ins[prev.d]+prev.w) {
                    count++;

                    if(!visited[prev.d]) {
                        visited[prev.d] = true;
                        rq.add(prev.d);
                    }
                }
            }
        }
        System.out.println(ins[e]);
        System.out.println(count);
    }
}

class Edge{
    int d; // 목적지
    int w; // 가중치

    Edge(int d, int w){
        this.d = d;
        this.w = w;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Edge{");
        sb.append("d=").append(d);
        sb.append(", w=").append(w);
        sb.append('}');
        return sb.toString();
    }

}

