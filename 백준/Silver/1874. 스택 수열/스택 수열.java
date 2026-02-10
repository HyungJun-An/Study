/*
    백준 1874
    1. 아이디어
        peek < 입력값{ push } peek 입력값 == pop 
    2. 시간복잡도
        O(N) ==> 10,000
    3. 자료구조
        값 입력 : Queue<Integer>
        결과: Stack<Integer>
 */
import java.io.*;
import java.util.*;
public class Main {
    static Stack<Integer> st = new Stack<>();
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<=N; i++) {
            q.add(i);
        }
        st.push(0);
        try {
            for(int i=0; i<N; i++) {
                int cur = Integer.parseInt(br.readLine());
    
                while(true) {
                    if(st.peek() < cur) {
                        st.push(q.poll());
                        sb.append("+\n");
                    } else if(st.peek() == cur) {
                        st.pop();
                        sb.append("-\n");
                        break;
                    } else if(st.peek() > cur) {
                        st.pop();
                        sb.append("-\n");
                    }
                }
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println("NO");
        }
        
    }
    

}
