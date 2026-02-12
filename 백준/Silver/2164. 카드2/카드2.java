/*
    백준 2164
    1. 아이디어
        큐 맨 윗 부분을 poll() 버림 그 다음 poll()한 값을 addLast로 맨 밑에 둠 if(q.size()==1) 일때 결과 출력

    2. 시간복잡도
        O(N) = 500,000

    3. 자료구조
        Deque<Integer>
 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> q = new ArrayDeque<>();
        if(N==1) {
            System.out.println(N);
            return;
        }
        for(int i=N; i>=1; i--) {
            q.push(i);
        }
        while(!q.isEmpty()) {
            
            q.poll(); //맨 윗장 버림
            if(q.size() == 1) {
                System.out.println(q.poll());
                return;
            }
            q.addLast(q.poll());// 큐에 맨 밑에 윗값 넣기
        }
    }    
}
