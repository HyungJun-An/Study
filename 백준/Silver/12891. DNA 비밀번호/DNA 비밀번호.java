
//DNA 비밀번호
import java.util.*;
import java.io.*;
public class Main {
static int[] ACGT,ACGTcount;
	public static void main(String[] args) throws NumberFormatException,IOException {
		//입력값 받기
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(bf.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int count=0;
		String text=bf.readLine();
		ACGT=new int[4];
		ACGTcount=new int[4];
		st=new StringTokenizer(bf.readLine());
		for (int i=0; i<4; i++)ACGT[i]=Integer.parseInt(st.nextToken());
		//text ACG개수 count하기
		for (int i=0; i<m; i++) {
			if(text.charAt(i)=='A')ACGTcount[0]++;
			else if(text.charAt(i)=='C')ACGTcount[1]++;
			else if(text.charAt(i)=='G')ACGTcount[2]++;
			else if(text.charAt(i)=='T')ACGTcount[3]++;
		}
		//투포인터 움직이기
		int start=0;
		int end=m-1;
		if(check())count++;
		while(end+1<n) {
		char s=text.charAt(start);
		if(s=='A')ACGTcount[0]--;
		if(s=='C')ACGTcount[1]--;
		if(s=='G')ACGTcount[2]--;
		if(s=='T')ACGTcount[3]--;
		start++;
		
		end++;
		char e=text.charAt(end);
		if(e=='A')ACGTcount[0]++;
		if(e=='C')ACGTcount[1]++;
		if(e=='G')ACGTcount[2]++;
		if(e=='T')ACGTcount[3]++;
		if(check())count++;
		}
		
System.out.print(count);

	}

	static boolean check() {
		boolean flag=true;
		if(ACGT[0]>ACGTcount[0])flag=false;
		if(ACGT[1]>ACGTcount[1])flag=false;
		if(ACGT[2]>ACGTcount[2])flag=false;
		if(ACGT[3]>ACGTcount[3])flag=false;
		return flag;
}
}
