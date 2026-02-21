/*
    백준 1715
    1. 아이디어
        PriorityQue 를 사용하여 오름차순으로 정렬 후 작은 수 끼리의 합 + 남은 수
        n=4
        20,30,40,90
        40,50,90            
        90,90 
        180
        n = 5
        20, 30, 40, 90,320
        40,50,90,320 sum = 50
        90,90,320 sum = 140
        180 ,320 sum = 320
        500  sum = 500
    2. 시간복잡도
        O(nlogn) 10,000 * log10,000  10,000log10,000
    3. 자료구조
        PriorityQue<Integer>
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            q.add(num);        
        }
        int sum =0;
        int temp=0;
        while(q.size()>1) {
            int first = q.poll();
            int sencond = q.poll();
            temp = first + sencond;
            sum += temp;
            q.add(temp);
        }
        System.out.println(sum);
    }
}