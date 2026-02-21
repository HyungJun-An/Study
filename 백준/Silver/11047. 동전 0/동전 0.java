/*
    백준 11047
    1. 아이디어
        주어진 숫자로 입력값 만드는 것. 우선순위 큐를 홣용해 그리디 알고리즘 구현
    2. 시간복잡도
        O(N) 10^6
    3. 자료구조
        PriorityQue<Integer>

*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int n = Integer.parseInt(st.nextToken());
        int sum =Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            int value = Integer.parseInt(br.readLine());
            q.add(value);
        }
        int count=0;
        while(sum!=0) {
            if(q.peek() > sum) {
                q.poll();
            } else {
                sum -= q.peek();
                count++;
            }
        }
        System.out.println(count);
    }
}