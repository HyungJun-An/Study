/*
    백준 1043
    1. 아이디어
        1. 각 파티마다 union 연산을 통해 대표노드를 이어준다. 파티인원 진신을 아는사람인지 확인,  작은 값을 대표 노드로
        2. 진실을 아는사람의 수만큼 각 파티의 인원 번호를 비교한다.
        union, find

    2. 시간복잡도
        O(N^2) 50 * 50 = 2,500

    3. 자료구조
        사람번호: int[]
        파티 참가자: ArrayList<Integer>[]
        진실을 아는사람: int[]
        결과값 저장: int

*/
import java.io.*;
import java.util.*;

public class Main {
    static int n,m,t;
    static int[] people, k_people;
    static int ans;
    static ArrayList<Integer>[] party;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 전체 인원 수
        m = Integer.parseInt(st.nextToken()); // 파티 수
        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken()); //진실을 아는사람 수

        if(t == 0 ) { // 진실을 아는 사람이 없으면 모든 파티에서 거짓말 할 수 있음
            System.out.println(m);
            return;
        }

        k_people = new int[t];
        for(int i=0; i<t; i++) { //진실을 아는 사람 저장
            k_people[i] = Integer.parseInt(st.nextToken());
        }
        party = new ArrayList[m+1];
        for(int i=0; i<m; i++) {
            party[i] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            int party_size = Integer.parseInt(st.nextToken());
            for(int j=0; j<party_size; j++) {
                party[i].add(Integer.valueOf(st.nextToken()));
            }
        }
        people = new int[n+1];
        for(int i=0; i<=n; i++) people[i] = i;

        for(int i=0; i<m; i++) { //파티 union을 통해 그룹으로 묶기
            int first = party[i].get(0);
            for(int j=1; j<party[i].size(); j++) {
                union(first,party[i].get(j));
            }
        }

        for(int i=0; i<m; i++) {
            boolean isPossible = true;
            int cur = party[i].get(0);
            for(int j =0; j<k_people.length; j++) {
                if(find(cur) == find(k_people[j])) {
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) ans++;
        }
        System.out.println(ans);
    } // main end

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a!=b) people[b]=a;
    }

    public static int find(int a) {
        if(a == people[a]) return a;
        return people[a] = find(people[a]);
    }
}