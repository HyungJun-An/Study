import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//입력값 받기
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(bf.readLine());
	
		int start = 1; 
		int end;
		
		int sum = 0;
		int i = 0;
		while(sum < n) {
			i++;
			sum += i;
		}
		// sum이 15가 되는 구간 r에 저장 
		end = i;
		int count = 0;
		
		while(start <= n) {
			
			if(sum < n) {
				end++;
				sum=sum+end-start;
			}
			else if(sum == n) {
				end++;
				count++;
				sum=sum+end-start;
			}
			else if(sum > n) {
				sum=sum-start;
			}
			
			
			start++;
			
		}
		System.out.print(count);
	}
}








