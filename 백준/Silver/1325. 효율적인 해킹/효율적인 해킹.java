/*
    백준 1325
    1. 아이디어
        단방향 노드를 연결 후 bfs탐색 중 방문횟수가 가장 많은 지점을 오름차순으로 저장
        for(노드개수) if(방문어부x)
    2. 시간복잡도
        O(N * logn)  100,000 * log 100,000
    3. 자료구조
        컴퓨터배열 : ArrayList<Integer>[] 
        방문여부 : boolean[]
        방문횟수 : int[];
        결과저장: Queue<Integer>, List<Integer>
*/
import java.io.*;
import java.util.*;

public class Main {
    static int[] answer;
    static boolean[] chk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        answer = new int[n+1];
        ArrayList<Integer>[] computers = new ArrayList[n+1];
        List<Integer> res = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            computers[i] = new ArrayList<>();    
        }

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            computers[B].add(A);
        }
        for(int i=1; i<=n; i++) {
            chk = new boolean[n+1];
            bfs(computers,res,i);
        }
        int maxVal=0;
        for(int num: answer) {
            if(num > maxVal){
                maxVal = num;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            if(answer[i] == maxVal)
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
    public static void bfs(ArrayList<Integer>[] computer, List<Integer> res, int i) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i); //출발 지점
        chk[i] = true;
        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            
            for(int next: computer[cur]) {
                if(!chk[next]) {
                    q.add(next);
                    chk[next] = true;
                }
            }
        }
        answer[i] = cnt;

    }
}