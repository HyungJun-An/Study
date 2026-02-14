/*
    백준 1427
    1. 아이디어
        String 을 받아서 charAt으로 int로 형변환 후 배열을 선택정렬로 정렬
    2. 시간복잡도
        O(N) N = 10 ==> 100
    3. 자료구조
        결과저장: int[]
 */
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = line.length();
        int[] res = new int[N];

        for(int i=0; i<N; i++) {
            res[i] = line.charAt(i) -'0';
        }
        for(int i=0; i<N; i++) {
            int max = i;
            for(int j=i+1; j<N; j++) {
                if(res[j]>res[max]){
                    max = j;
                }
                if(res[i] < res[max]) {
                    int temp = res[i];
                    res[i] = res[max];
                    res[max] = temp;
                }
            }
        }
        for(int i=0; i<N; i++) {
            System.out.print(res[i]);
        }
    }
}
