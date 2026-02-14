/*
    백준 11399
    1. 아이디어
        이중 for 문으로 풀어도 되고 N^2
        버블소트 문제처럼 copoarable로도 풀기 가능 nlogn
        정렬 전 bf_idx
        정렬후  af_idx
        결과 += 정렬배열[af_idx]
        공부 할 겸 Comparable로 품
        
    2. 시간복잡도
        O(nlogn)

    3. 자료구조
        결과 저장 : class Point
 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] bf = new int[N];

        Point[] arr = new Point[N];

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = new Point(i, num);
            bf[i] = num;

        }
        Arrays.sort(arr);
        int wait = 0;
        int res = 0;
        for(int i=0; i<N; i++) {
            wait += bf[arr[i].idx];
            res += wait;
        }
        System.out.println(res);
    }
    
}
class Point implements Comparable<Point> {
    int idx;
    int val;

    public Point(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
    
    @Override
    public int compareTo(Point o) {
        return val - o.val;
    }
}
