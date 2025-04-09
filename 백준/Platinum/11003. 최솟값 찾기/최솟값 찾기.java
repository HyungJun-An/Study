import java.util.*;
import java.io.*;
public class Main {
    //최소값 찾기
    public static void main(String[] args)throws IOException{
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(bf.readLine());
        int n=Integer.parseInt(st.nextToken());
        int l=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(bf.readLine());
        Deque<int []> q=new ArrayDeque<>();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<n; i++) {
            int num=Integer.parseInt(st.nextToken());
            while(!q.isEmpty()&&q.peekLast()[0]>num) 
                q.pollLast();  //q배열이 비어있지 않거나 q배열 첫번째가 num보다 크면
                               //q배열 마지막 값을 삭제하라

            q.offer(new int[]{num ,i});	//q배열에 인덱스와 num을 저장하라

            if(q.peekFirst()[1]<i-(l-1)) { //첫번째 값이 윈도우를 벗어났을
                q.pollFirst();

            }
            sb.append(q.peekFirst()[0] +" ");
        }
        System.out.println(sb.toString());
    }
}
