/*
    백준 
    1. 아이디어
        BackTracking + DFS 
        1. 소수 = 2,3,5,7 ==> 홀수를 더함
        2. 21, 23, 25, 27 29 ==> 여기중 소수를 찾음
        3. 23, 29 ==> 1 반복
    2. 시간복잡도
        O(소수 후보 개수 * squrt(X) ) ==> 1000 * 루트 10^8 ==> 10,000,000 
    3. 자료구조
        결과저장: Queue<Integer>
*/
import java.io.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(2,1);
        dfs(3,1);
        dfs(5,1);
        dfs(7,1);
    }
    public static void dfs(int num, int jarisu) {
        if(jarisu == n) {
            System.out.println(num);
            return;
        }
        for(int i=1; i<10; i+=2) {
            int nextNum = num*10 + i;
            if(is_prime(nextNum)) {
                dfs(nextNum,jarisu+1);
            }
        }
    }
    public static boolean is_prime(int num) {
        if(num<2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}