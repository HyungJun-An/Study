/*
    백준 1300
    1. 아이디어
        이분트리를 사용히야 우리가 찾고자 하는 값 K 보다 작은 자연수의 개수를 출력하면 된다.
        mid 이하의 숫자 개수가 K보다 적으면 범위를 키우고, 
        K보다 크거나 같으면 일단 답으로 저장한 뒤 더 작은 값이 있는지 범위를 좁히는 매개 변수 탐색.
    2. 시간복잡도
        O(nlogn) n = 10^5 log 10^5 k = 10^9
    3. 자료구조
        결과 저장: long
*/
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long K = Long.parseLong(br.readLine());

        long start =1;
        long end = K;
        long ans = 0;
        while(start<=end) {
            long mid = (start+end) /2;
            long cnt =0;

            for(long i=1; i<=N; i++) {
                cnt += Math.min(mid/i,N);
            }
            if(cnt<K) {
                start = mid +1;
            } else {
                ans = mid;
                end = mid -1;
            }
        }
        System.out.println(ans);
    }
}