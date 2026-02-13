/*
    백준2750
    1. 아이디어
        버블정렬로 풀자
    2. 시간복잡도
        O(N^2) ==> 1,000 * 1,000 = 1,000,000

    3. 자료구조
        배열저장 int[]
 */
import java.io.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        for(int j=0; j<N; j++) {
            for(int i=0; i<N-1; i++) {
                if(num[i] > num[i+1]) {
                    int tmp = num[i+1];
                    num[i+1] = num[i];
                    num[i] = tmp;
                }
            }
        }
        for(int i=0; i<N; i++) {
            System.out.println(num[i]);
        }
    }
}
