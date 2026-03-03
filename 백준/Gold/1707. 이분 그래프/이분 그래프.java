/*
    백준 1707
    1. 아이디어
        각 노드마다 DFS를 돌며 방문했던 노드인지 확인 if(visited) syso(NO)
        DFS인 이뉴는 각 노드가 연결되어 있지 않을 수 있기 때문에 BFS는 X
    2. 시간복잡도
        O = K * (V+E) = 5 * (20,000 + 200,000) = 1,100,000
    3. 자료구조
        노드값 저장: ArrayList<Integer> //양방향 노드
        방문여부: int[] //0 unvisited 1 red 2 blue
        결과저장: boolean

*/
import java.io.*;
import java.util.*;

public class Main {
    static int[] chk; 
    static ArrayList<Integer>[] node;
    static boolean isBipartite;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        while(k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ver = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            // init graph
            node = new ArrayList[ver+1];
            for(int i=1; i<=ver; i++) node[i] = new ArrayList<>();
            chk = new int[ver+1];
            isBipartite = true;
            
            for(int i=0; i< e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                node[u].add(v);
                node[v].add(u);
            }

            for(int i=1; i<=ver; i++) {
                if(!isBipartite) break;
                if(chk[i] ==0) {
                    dfs(i,1);
                }
            }
            System.out.println(isBipartite ? "YES" : "NO");
        }
    }
    public static void dfs(int cur, int color) {
        chk[cur] = color;

        for(int next: node[cur]) {
            if(chk[next] == 0) {
                dfs(next, -color);
            } else if (chk[next] == chk[cur]) {
                isBipartite = false;
                return;
            }
            if(!isBipartite) return;
        }
    }
}