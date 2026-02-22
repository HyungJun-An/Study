/*
    백준 1931
    1. 아이디어
        회의가 끝나는 시점을 오름차순으로 정렬 후 첫번째 노드부터 쭉 따라가면서 회의 수 를 카운트하고 출력한다

    2. 시간복잡도
        O(nlogn) = 100,000 log 100,000
    3. 자료구조
        ArrayList<Meet>
        Meet<int[]> int start, int value

*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Meet> list =new ArrayList<>();
        
        StringTokenizer st;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Meet(start,end));
        }
        Collections.sort(list);
        int cnt =0 ,lastEnd = 0;
        for(int i=0; i<n; i++) {
            Meet now = list.get(i);
            if(now.start >= lastEnd) {
                lastEnd = now.end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    
}
class Meet implements Comparable<Meet>{
    int start, end;
    public Meet(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Meet o) {
        if(this.end == o.end) {
            return this.start - o.start;
        }
        return this.end - o.end;
    }
}
