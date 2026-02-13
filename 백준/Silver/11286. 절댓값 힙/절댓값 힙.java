/*
    백준 11286
        x==0
        큐가 비어있을때는 0 을 출력하고 비어있지 않을때는 절댓값이 최소인 값을 출력한다.
        절대값이 같을땐 음수를 우선 출력
        x!=0
        add로 큐에 새로운 값을 추가하고 우선순위 정렬기준으로 자동정렬한다.
        

    2. 시간복잡도
        O( N * logN ) => 100,000*log100,0000

    3. 자료구조
        결과저장: PriorityQueue<Integer> 
 */
import java.io.*;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num;
        PriorityQueue<Integer> q = new PriorityQueue<>((o1,o2) -> {
            int abs_o1 = Math.abs(o1);
            int abs_o2 = Math.abs(o2);
            if(abs_o1 == abs_o2) 
                return o1 > o2 ? 1 : -1;
            else 
                return abs_o1 - abs_o2;
            
        });
        
        for(int i=0; i<N; i++) {
            num = Integer.parseInt(br.readLine());
            if( num == 0 ) {
                if(q.isEmpty()) System.out.println(0);
                else {
                    System.out.println(q.poll());
                }
           } else {
                q.add(num);
           }
        }
    }

}
