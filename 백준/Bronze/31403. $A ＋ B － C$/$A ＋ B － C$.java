import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 변수 A, B, C를 정의합니다.
    static int a, b, c;
    public static void main(String[] args) throws IOException {
    	// BufferedReader를 이용해 A, B, C 를 입력받습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        
        // 2번째 출력값을 만들기 위해 변수 A, B를 문자로 취급해서 합해줍니다.
        String ab = String.valueOf(a)+String.valueOf(b);
        
        // 1번째 출력: A, B, C를 모두 숫자로 보고 계산합니다.
        System.out.println(a+b-c);
        
        // 2번째 출력: 문자(AB) 와 숫자 C를 빼줍니다.
        System.out.println(Integer.parseInt(ab)-c);
    }
}
