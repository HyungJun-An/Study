/*
    백준 1377
    10 5 1 2 3
    1 2 3 5 10
    1. 아이디어
        swap 이 일어난 개수를 출력해야 함
        하지만 swap 메서드가 2중 for문 안에있어서 시간복잡도가 N^2 N=500,000
        가장 많이 왼쪽으로 이동한 index를 찾고 이동한 거리를 출력하면 됨
    2. 시간복잡도
        O(nlogn)
    3. 자료구조
        결과저장: List<Integer>  Collections.sort 사용을 위해
    4. 오답노트
        Collections.get은 순차적으로 탐색하기 때문에 O(N^2)과 같다 시간초과!!
        Point객체를 선언하여 idx, value를 저장하고 관리 하는게 효율적!
*/
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point[] arr = new Point[N];
        
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = new Point(num,i);
        }
        Arrays.sort(arr);
        int max=0;
        for(int i=0; i<N; i++) {
            if(max < arr[i].idx -i) {
                max = arr[i].idx -i;
            }
        }
        System.out.println(max+1);
    }
}

class Point implements Comparable<Point> {
    int value;
    int idx;

    public Point(int value, int idx) {
        this.value = value;
        this.idx = idx;
    }
    @Override
    public int compareTo(Point o) {
        return this.value - o.value;
    }
}