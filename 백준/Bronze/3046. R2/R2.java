/*
    백준 3046
    1. 아이디어
        R1 + R2 = S 인데 R2를 까먹음
        R2 = 2S - R1
    2. 시간복잡도
        O(1)
    3. 자료구조
        int
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R1 = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        System.out.println(2*S - R1);
    }
}
