import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // N 은 10^6 이기 때문에 integer 벗어날 수 있음
        long result = 0;
        long[] num = new long[n];
        long[] count = new long[m];

        st = new StringTokenizer(br.readLine());
        num[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i ++ ) {
            //합을 구하기
            long input = Integer.parseInt(st.nextToken());

            num[i] = num[i - 1] + input;
        }

        for (int i =0; i < n; i++ ) {
            // 구간합 배열을 m으로 나눈 나머지 배열로 바꾸기
            num[i] %= m;
            
            // 초기값 0 의 개수 세기
            if (num[i] == 0 )
                result++;
            
            // 같은 원자 개수 세기
            count[(int)num[i]]++;
        }


        for (int i=0; i<m; i++) {
            if(count[i] >1 ){
                // N C 2 N개중 2개를 뽑을 수 있는 가지 수 세기
                result += (count[i] * (count[i]-1)) /2;
            }
        }
        System.out.println(result);
    }
}