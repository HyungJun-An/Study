import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int result =0;
        char[] ctemp = new char[count];
        String temp = br.readLine();

        ctemp = temp.toCharArray();

        for(int i=0;i<count;i++){
            result += ctemp[i]-'0';
        }
        System.out.println(result);
    }
}