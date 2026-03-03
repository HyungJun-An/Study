/*
    백준 1717
    1. 아이디어
        Union Find를 활용해 루트 노드에 빠르게 접근
        초기 node값은 항상 자신이 대표 노드
        union 연산을 통해 자신의 값이 index가 아니라면 대표노드 합친다
        find 연산도 자신의 index가 index value인지 확인
    2. 시간복잡도
        O( mlogn )  100,000 log1,000,000 ~= 10^6
    3. 자료구조
        자료저장:int[]
*/
import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        for(int i=1; i<=n; i++) arr[i] = i; //init val

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(flag==1)
                System.out.println(find(u)==find(v)?"YES":"NO");
            else {
                union(u,v);
            }
        }
    }
    public static int find(int s) {
        if (s == arr[s]) return arr[s];
        return arr[s] = find(arr[s]);
    }
    public static void union(int u, int v) {
        //check present node
        int root1 = find(u);
        int root2 = find(v);

        if (root1!=root2) {
            if(root1<root2) arr[root2]=root1;
            else arr[root1]= root2;
        }

    }
}