import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws NoSuchElementException,NumberFormatException,IOException {
	//입력값 받기
	BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
	int n=Integer.parseInt(bf.readLine());
	int m=Integer.parseInt(bf.readLine());	
	//N개의 배열 선언후 값 입력하기
	int[] num=new int[n];
	
	StringTokenizer st=new StringTokenizer(bf.readLine());
	for(int i=0; i<n; i++) {
		num[i]=Integer.parseInt(st.nextToken());		
	}
	//배열을 오름차순으로 정렬
	Arrays.sort(num);
	//투포인터 움직임 설
	int start=0;
	int end=n-1;
	int sum=0;
	int count=0;
	while(start<end) {
		sum=num[start]+num[end];
		if(sum<m) {
			start++;
			continue;
		}
		else if(sum==m) {
			count++;
			end--;
			start ++;
			continue;
		}
		else if(sum>m) {
			end--;
			continue;
		}

		
	}
	System.out.println(count);
	}

}
