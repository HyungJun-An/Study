/*
    백준 
    1. 아이디어
        int[] 배열 안에 각 노드에서 갈 수 있는 distance를 구함
        1의 경우 [0,9,2,5,11]
        2의 경우 [9.0.7.4,10] 
        3의 경우 [2,7,0,3,9]
        4의 경우 [5,4,0,3,6]
        5의 경우 [11,10,9,6,0]
        이후 각 루프마다 최대값을 저장
        [11,10,9,6,11]
        이후 최대값을 출력 11
    2. 시간복잡도
        O(N^2) = 10,000 * 10,000 = 100,000,000
    3. 자료구조
        노드정보 class Edge distance 와 목적지를 담음
        거리배열 int[]
        방문야부 boolean[]
*/
import java.io.*;
import java.util.*;

public class Main {
    
    static ArrayList<Edge>[] list;
    static int cost[];
    static boolean chk[];
    static int N;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++) list[i] = new ArrayList<>();
        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx =Integer.parseInt(st.nextToken());
            while(true) {
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break; // -1을 만나면 이 줄(현재 노드의 연결 정보) 읽기 끝!
                
                int cost = Integer.parseInt(st.nextToken());
                list[idx].add(new Edge(to, cost));
            }
        }
        chk = new boolean[N+1];
        distance = new int[N+1];
        bfs(1);
        int target = 1;
        for(int i=2; i<=N; i++) {
            if(distance[i] > distance[target]) target =i;
        }
        distance = new int[N+1];
        chk = new boolean[N+1];
        bfs(target);

        Arrays.sort(distance);
        System.out.println(distance[N]);
    }
    static void bfs(int index) {
        Queue<Integer> q = new LinkedList<>();
        q.add(index);
        chk[index] = true;
        while(!q.isEmpty()) {
            int idx = q.poll();
            for(Edge i: list[idx]) {
                int cost = i.cost;
                int to = i.to;
                if(!chk[to]){
                    chk[to] = true;
                    q.add(to);
                    distance[to] = distance[idx] + cost;
                }
            }
        }
    }
}
class Edge{
    int to;
    int cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}