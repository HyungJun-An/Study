import java.util.*;
import java.io.*;
public class Main  {
	public static void main(String[] args)throws NumberFormatException,IOException {
		//입력값 받기
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(bf.readLine());
		int []num=new int[n];
		StringTokenizer st=new StringTokenizer(bf.readLine());
		//배열 넣기
		for(int i=0; i<n; i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		//start end index 움직이기
		int count=0;
		for(int index=0; index<n; index++) {
			int start=0;
			int end=n-1;
			while(true) {
				//start index값이 같을때
				if(start==index)start++;
				
				//end index값이 같을때
				else if(end==index)end--;
				
				//start>=end 이면 반복문 종료
				if(start>=end)break;
				
				//start end값이 <index일때 
				else if(num[start]+num[end]<num[index])start++;
				
				//start end값이 >index일때
				else if(num[start]+num[end]>num[index])end--;
				//start end값이 index랑 같을때
				else{ count++; start++; break;}
			}
		}
		System.out.print(count);
	}	
}