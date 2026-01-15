/*
    1. 아이디어
        - BFS 방식으로 if(A * 2 < B) 일때 count++ 하고 queue에 추가 if( A * 10 + 1 < B ) 일때 count ++ 하고 queue 추가
        - 방문여부 X 숫자가 커지기만 함 

    2. 시간복잡도
        - 각 단계에서 2개씩 가지가 뻗어나가지만, 숫자가 기하급수적으로 커짐.
        - log 기반의 탐색이 이루어지므로 시간 내 충분히 가능.

    3. 자료구조
        - Queue<long[]>: {현재값, 연산횟수} 저장.
        - long 타입: 오버플로우 방지.
*/

import java.io.*;
import java.util.*;

public class Main {
    
    static int cnt;
    static long A,B;
    

    public static int bfs() {
        Queue<long[]> q = new LinkedList<>();
        
        q.add(new long[]{A,1});
        
        while(!q.isEmpty()) {
            long[] curr = q.poll();
            long value = curr[0];
            long cnt = curr[1];

            if(value==B) {
                return (int) cnt;
            }
            long mul = value *2;
            if( mul <= B ) {
                q.add(new long[]{mul,cnt+1});
            }
            long add = value * 10 + 1;
            if(add <= B ) {
                q.add(new long[]{add,cnt+1});
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        System.out.println(bfs());
    }
}