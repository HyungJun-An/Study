/*
    백준 
    1. 아이디어
        위상정렬을 사용하여 해결
        3 2     1 2 3    조회할 idx - 후 0일 때 출력
        1 3     0 0 1    0 0 1 -> 1
        2 3     0 0 2    0 0 0 -> 2
                         0 0 0 -> 3

        4 2     1 2 3 4  1 1 0 0 -> 3
        4 2     0 1 0 0  1 0 0 0 -> 4
        3 1     1 1 0 0  0 0 0 0 -> 1
                         0 0 0 0 -> 2
        1. 진입 차수가 0인 노드를 큐에 저장
        2. 큐에서 데이터를 poll하고 결과값을 차수배열에 저장, 해당 노드의 진입차수를 1씩 감소
        3. 감소했을 때 진입차수 0 되는 노드를 큐에 push

    2. 시간복잡도
        O(N+M) = 132,000

    3. 자료구조
        차수배열: int[]
        진입차수배열: int[]
        노드정보: ArrayList<Integer>
        결과값: Queue<Integer>

*/
import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            A.add(new ArrayList<>());
        }
        int[] indegree = new int[n+1];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A.get(s).add(e);
            indegree[e]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");
            for(int next: A.get(now)) {
                indegree[next]--;
                if(indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
        System.out.println(sb);
    }
}