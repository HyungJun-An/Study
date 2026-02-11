/*
    백준 17298
    1. 아이디어
        스택에 인덱스를 저장하여 while(!stack.isEmpty() && num[stack.peek()] < num[i] )
    2. 시간복잡도
            O(N) => 1,000,000
    3. 자료구조
        결과저장: Stack<Integer>
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        int[] ans = new int[N];
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<N; i++) {
            while(!stack.isEmpty() && num[stack.peek()] < num[i]) {
                ans[stack.pop()] = num[i];
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            ans[stack.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
