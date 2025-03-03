import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int array[] = new int[count];
        double darray[] = new double[count];
        int max = 0;
        double result = 0;
        for (int i = 0; i < count; i++) {
            array[i] = Integer.parseInt(st.nextToken(" "));
        }
        max = Arrays.stream(array).max().getAsInt();

        // 평균 조작하기
        for(int i=0; i<count; i++){
            darray[i] = ((double)array[i]/(double)max) *100;
            result += darray[i];

        }

        System.out.println(String.format("%.7f",result/count));

    }
}
