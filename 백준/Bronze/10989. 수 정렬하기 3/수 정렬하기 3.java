/*
    백준10989
    1. 아이디어
        기수정렬로 풀기 K<= 10,000 K = 5
    2. 시간복잡도
        O(kn) k=5 n=50,000,000 ==> 50,000,000
    3. 자료구조
        List<Queue<String>> q
 */
import java.io.*;


public class Main {
/*기수정렬 풀이
    static List<Queue<Integer>> bucket = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        for(int i=0; i<10; i++ ) {
            bucket.add(i,new LinkedList<>());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        radix_sort(arr, N);
        
    }
    static void radix_sort(int[] A, int N) {
        int jarisu = 1;
        for(int d=0; d<5; d++) {
            for(int i=0; i<N; i++) {
                int digit = (A[i]/jarisu) %10;
                bucket.get(digit).add(A[i]);
            }
            
            int idx = 0;
            for(int i=0; i<10; i++) {
                while(!bucket.get(i).isEmpty()){
                    A[idx++] = bucket.get(i).poll();
                }
                
            }
            jarisu *= 10;
        }
        for(int i=0; i<N; i++) {
            System.out.print(A[i] + " ");
        }
    }
*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] counts = new int[10001];
        for(int i=0; i<N; i++) {
            counts[Integer.parseInt(br.readLine())]++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<10001; i++) {
            while(counts[i] > 0) {
                sb.append(i).append("\n");
                counts[i]--;
            }
            if(sb.length()>8000) {
                System.out.print(sb);
                sb.setLength(0);
            }
        }
        System.out.print(sb);
    }
}