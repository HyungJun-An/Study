import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        String[] sanswer = new String[count];
        String temp= "";
        int[] answer = new int[count];
        for(int i=0; i<count; i++) {

            temp = br.readLine();

            //중복 제거
            if(Arrays.asList(sanswer).contains(temp)) {
                continue;
            }
            sanswer[i] = temp;


        }
        for(int i=0; i<sanswer.length; i ++){
            answer[i] = Integer.parseInt(sanswer[i]);
        }
        Arrays.sort(answer);

        for(int num: answer) {
            System.out.print(num + "\n");
        }
    }
}