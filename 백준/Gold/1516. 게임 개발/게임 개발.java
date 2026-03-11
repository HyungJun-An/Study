/*
    백준 1516
    1. 아이디어
        위상배열 활용      내가 일을 끝내기 전 지어져야 하는 건물 개수          정답 배열
        5              idx  1  2  3  4  5                    idx 1   2  3   4  5
        10 -1               0  0  0  0  0            1           0  10  10  10  0  | 1 과 이어져 있는 배열에 1의 걸리는 시간
        10 1 -1             0  1  0  0  0            1,2         0  10  10  10  0  | 2 과 이어져 있는 배열에 2의 걸리는 시간
        4 1 -1              0  1  1  0  0            1,2,3       0  10  10  14  14 | 3 과 이어져 있는 배열에 3의 걸리는 시간 max(10, 14)
        4 3 1 -1            0  1  1  2  0            1,2,3,4,5   0  10  10  14  14 | 4 과 이어져 있는 배열에 4의 걸리는 시간 max(0, 14)
        3 3 -1              0  1  1  2  1

    2. 시간복잡도
        O(N) = 500
    3. 자료구조
        노드저장: ArrayList<ArrayList<Integer>>
        차수배열: int[]
        빌드시간: int[]
        정답배열: int[]
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dim = new int[n+1];
        int[] ans = new int[n+1];
        int[] selfbuild = new int[n+1];
        ArrayList<ArrayList<Integer>> node = new ArrayList<>();
        for(int i=0; i<=n; i++) node.add(new ArrayList<>());
        for(int i=1; i<=n; i++) {
            selfbuild[i] = sc.nextInt();
            while(true) {
                int tmp = sc.nextInt();
                if(tmp==-1) break;
                node.get(tmp).add(i);
                dim[i]++; //진입차수 초기화
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(dim[i] == 0) {
                q.add(i); //위상정렬 초기값 설정
            }
        }
        while(!q.isEmpty()) {
            int now = q.poll();
            for(int next :node.get(now)) {
                dim[next]--;
                ans[next] = Math.max(ans[next],ans[now]+selfbuild[now]);
                if(dim[next]==0) {
                    q.add(next);
                }
            }
        }
        for(int i=1; i<=n; i++) {
            System.out.println(ans[i]+selfbuild[i]);
        }
    }
}