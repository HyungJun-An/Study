/*
    백준 1976
    1. 아이디어
        n 도시의 개수<200 , m 여행 계획 도시 수 <1,000
        union을 진행하며 둘 다 대표노드일 때 큰 값을 대표노드로 덮어쓰기

    2. 시간복잡도
        O(m * log n) = 200 * log 1000 

    3. 자료구조
        도시값: int[][]
        여행입력값: int[]
        결과저장: int[]
*/
import java.io.*;
import java.util.*;


public class Main {
static int[] parent; // 부모 노드 저장 (Union-Find용)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 1. parent 배열 초기화 (도시 개수 n만큼!)
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        StringTokenizer st;
        // 2. 연결 정보 입력 및 Union
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int connect = Integer.parseInt(st.nextToken());
                if (connect == 1) union(i, j); // 연결되어 있으면 한 가족으로!
            }
        }

        // 3. 여행 계획 입력
        st = new StringTokenizer(br.readLine());
        int[] route = new int[m];
        for (int i = 0; i < m; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        // 4. 여행 가능 여부 확인 (모든 경로 도시의 루트가 같은지)
        boolean possible = true;
        int root = find(route[0]); 
        for (int i = 1; i < m; i++) {
            if (root != find(route[i])) {
                possible = false;
                break;
            }
        }

        System.out.println(possible ? "YES" : "NO");
    }

    public static int find(int s) {
        if (s == parent[s]) return s;
        return parent[s] = find(parent[s]); // 경로 압축
    }

    public static void union(int u, int v) {
        int root1 = find(u);
        int root2 = find(v);
        if (root1 != root2) {
            parent[root2] = root1; // 합치기
        }
    }
}