

/*
    백준 12886

    1. 아이디어
    총 돌의 개수는 정해져 있음 C = sum - A - B
    A + B + C 가 3의 배수여야 A + B == C 가 나올 가능성 있음 
    2차원 배열 만 필요하고 왔던 X, Y 라면 방문여부 체크 필수임
    A,B A,C, B,C 이 조합을 while문 안에서 시도

    2. 시간복잡도
    O(V+E) 500 + 500 + 500 = V = 1500 * 1500 * 3 ==> 6,750,000

    3. 자료구조
    그림 : X
    방문여부 : boolean[1500][1500]
    자료저장 : Queue<int[]>
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main {

    static int A, B, C;
    static boolean[][] chk = new boolean[1501][1501];
    

    public static void bfs() {
        Queue<int[]> q= new LinkedList<>();
        int sum = A+B+C;
        q.add(new int[]{A,B,C});


        while(!q.isEmpty()) {
            int [] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];

            if ( a ==b && b == c ) {
                System.out.println(1);
                return;
            }

            int[][] combination = {{a,b,c},{a,c,b},{b,c,a}};

            for(int[] comb : combination) {
                int x = comb[0];
                int y = comb[1];
                int z = comb[2];

                if ( x!=y ) {
                    int nx = (x<y) ? x * 2 : x-y;
                    int ny = (x<y) ? y-x : y * 2;
                    int nz = z;

                    // 방문 배열 인덱스 에러 방지 및 중복 체크
                    // 정렬해서 체크하면 방문 배열 사용 범위를 줄일 수 있습니다.
                    int min = Math.min(nx, Math.min(ny, nz));
                    int max = Math.max(nx, Math.max(ny, nz));

                    if (min >= 0 && max <= 1500 && !chk[min][max]) {
                        chk[min][max] = true;
                        q.add(new int[]{nx, ny, nz});
                    }

                }
            }
        }
        System.out.println(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        if( (A+ B + C) % 3 != 0) {
            System.out.println(0);
            return;
        }
        bfs();
    }
}
 