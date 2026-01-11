import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(fabonachi(n));
    }
    public static long fabonachi(long res) {
        if(res == 0)
            return 0;
        else if(res == 1) {
            return 1;
        }
        else
            return fabonachi(res-1) + fabonachi(res -2);
    }
}