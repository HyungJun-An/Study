/* 
    1. 아이디어
        2중 for 문 재귀함수 dfs 2차원 배열 [y][x] == 1 && 방문 X 일 때 stack에 넣기

    2. 시간 복잡도
        O(V+E) = 5 * 25 * 25 = 3125 >> 가능
        
    3. 자료구조
        그림: 2차원 배열 int[][]
        방문여부: boolean [][]
        결과저장: stack 재귀 함수
 */

import java.io.*;
import java.util.*;


public class Main {
    
    // 전역변수
    static int n;
    static int[][] map;
    static boolean[][] chk;
    static Stack<Integer> stack = new Stack<>();
    static int size;
    static int cnt;
    static int[] dy = {0, 1 , 0, -1};
    static int[] dx = {1, 0 , -1, 0};

    // dfs 구현
    public static int dfs(int y, int x) {
        size++;
        for (int k =0; k<4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            // 그림 내 범위 확인
            if(0<=ny && ny< n && 0<=nx && nx<n) {

                if(map[ny][nx]==1 && !chk[ny][nx]) {
                    chk[ny][nx] = true;
                    dfs(ny,nx);        
                }
            }
        }
        return size;    
    }
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기값 설정
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        chk = new boolean[n][n];

        // 그림 그리기
        for(int j=0; j<n; j++) {
            String line = br.readLine();
            for(int i=0; i<n; i++) {
                map[j][i] = Character.getNumericValue(line.charAt(i));
            }
        }

        // 값이 1이고 방문하지 않았을 때
        for (int j=0; j<n; j++) {
            for(int i=0; i<n; i++) {
                if(map[j][i] == 1 && !chk[j][i]) {
                    size = 0;
                    chk[j][i] = true;
                    dfs(j,i);
                    stack.push(size);
                }
            }
        }

        
        System.out.println(stack.size());
        Collections.sort(stack);
        for(int s: stack) {
            System.out.println(s);
        }
    }
}
