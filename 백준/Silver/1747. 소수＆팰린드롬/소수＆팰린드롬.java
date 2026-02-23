/*
    백준 1747
    1. 아이디어
        n보다 큰 소수를 구한 뒤 팰린드롬 여부를 확인한다.
    2. 시간복잡도
        O(n logn) =1,000,000 * log1,000,000
    3. 자료구조
        소수 판별 여부: boolean[1,100,000]
*/
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int limit = 1100000;
        boolean[] decimal= new boolean[limit + 1];
        decimal[1] = true;
        for(int i=2; i*i<=limit; i++) {
            if(decimal[i]) continue;
            for(int j=i*i; j<=limit; j+=i) {
                decimal[j] = true;
            }
        }
        for(int i=n; i<=limit; i++) {
            if(!decimal[i]) {
                if(pelindrom(i)){
                    System.out.println(i);
                    return;
                }
            }
        }
    }
    public static boolean pelindrom(int decimal) {
        String str =String.valueOf(decimal);
        int start = 0;
        int end = str.length()-1;

        while(start<end) {
            if(str.charAt(start)!=str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}