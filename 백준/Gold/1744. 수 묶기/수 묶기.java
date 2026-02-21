/*
    백준 1744
    1. 아이디어
        양수는 내림차순으로 수를 정렬 후 수를 두개씩 두어서 곱한값 > 더한값 이라면 곱하기 진행
        음수는 오름차순으로 정리하는해야 함
        0은 따로 저장 후 음수가 홀수 일 때 곱해줘서 최대값 생성
    2. 시간복잡도
        O(nlogn)
    3. 자료구조
        PriorityQue
*/
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int oneCount = 0;
        PriorityQueue<Integer> q_plus = new PriorityQueue<>(new Comparator<Integer>() {
           @Override
           public int compare(Integer o1, Integer o2) {
                //양수만 내림차순 저장
                return o2-o1;
           }
        });
        PriorityQueue<Integer> q_minus = new PriorityQueue<>(new Comparator<Integer>() {
           @Override
           public int compare(Integer o1, Integer o2) {
                //음수 오른차순 저장
                return o1-o2;
           }
        });

        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num>1) {
                q_plus.add(num);
            } else if(num==1) oneCount++;
            else q_minus.add(num);
        }
        long totalSum =0;
        while(q_plus.size()>1) {
            totalSum += (long) q_plus.poll() * q_plus.poll();
        }
        if(!q_plus.isEmpty()) {
            totalSum += (long) q_plus.poll();
        }
        totalSum += oneCount;

        while(q_minus.size()>1) {
            totalSum += (long) q_minus.poll() * q_minus.poll();
        }
        if(!q_minus.isEmpty()) {
            totalSum+= q_minus.poll();
        }
        System.out.println(totalSum);
    }
}