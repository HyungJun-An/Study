/*
    백준11724
    1. 아이디어
        DFS for(int i=0; i<N; i++) dfs몇 번 호출?
    2. 시간복잡도
        O(V+E) V = 1,000 E = V * V/2 1000*500  ==> 1,000 + 500,000 ==> 501,000
    3. 자료구조
        입력저장: Queue<int[]>
        방문여부: boolean[]
        결과저장:Stack<int[]>
 */
import java.io.*;
import java.util.*;
    
    public class Main {
        static ArrayList<Integer>[] A;
        static boolean[] chk; 

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            
            chk = new boolean[V+1];
            A = new ArrayList[V+1]; 
    
            for(int i=1; i<=V; i++) {
                A[i] = new ArrayList<Integer>();
            }
    
            for(int i=0; i<E; i++) { // 간선 개수만큼 반복
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                A[u].add(v);
                A[v].add(u);
            }
    
            int cnt = 0;
            for(int i=1; i<=V; i++) {
                if(!chk[i]){
                    cnt++; 
                    dfs(i);
                }
            }
            System.out.println(cnt);
        }
    
        public static void dfs(int node) {
            if (chk[node]) return;
    
            chk[node] = true;
            for(int next : A[node]) { 
                if(!chk[next]) {
                    dfs(next);
                }
            }
        }
    }