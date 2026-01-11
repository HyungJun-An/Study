import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long factorial(long res) {
        if(res==1){
            return res;
        }
        if(res==0) {
            return 1;
        }
        return  res*factorial(res-1);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(factorial(n)); 
    }
}
