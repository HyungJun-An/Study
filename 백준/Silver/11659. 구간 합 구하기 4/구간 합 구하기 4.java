import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken(" "));
        int M = Integer.parseInt(st.nextToken(" "));

        int[] array = new int[N+1];
        array[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            array[i] =array[i-1] + Integer.parseInt(st.nextToken(" "));

        }

        for (int i =0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken(" "));
            int end = Integer.parseInt(st.nextToken(" "));
            System.out.println(array[end] - array[start-1]);
        }
    }
}
