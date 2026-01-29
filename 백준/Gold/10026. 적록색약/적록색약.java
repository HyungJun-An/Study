/*
    백준 10026번
    1. 아이디어
        case 1 : 색맹 아닐때 개수
        case 2 : 색맹 일 때 개수
        BFS : if(현재 색상 == 다음 색상) queue에 추가
        Main :for if(현재위치 방문 X ) 일 때 bfs() bfs호출 횟수가 정답

    2. 시간복잡도
        O(V+E) = V = 100 * 100 , E = 4V ==> 50,000

    3. 자료구조
        그림: char[N][N]
        방문여부: boolean[N][N]
        결과저장 : Queue<int[]{현재위치}> 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][] chk;
    static boolean isColorBlind = false;
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    static int cnt;
    public static void bfs(int y, int x) {
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        chk[y][x] = true;
        char color = map[y][x];

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            
            

            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];
                boolean isSameColor = false;

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !chk[ny][nx]) {
                    char next_color = map[ny][nx];
                    if (!isColorBlind) {
                        if (color == next_color) 
                            isSameColor = true;

                    } else { //ColorBlind
                        if (color == 'G' || color =='R') {
                            if (next_color == 'G' || next_color == 'R') 
                                isSameColor = true;
                        } else {
                            if (color == next_color) //when color 'B' 
                                isSameColor = true;
                        }
                    }   
                }
                if(isSameColor) {
                    q.add(new int[]{nx,ny});
                    chk[ny][nx] = true;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        map = new char[N][N];
        
        
        for(int j=0; j<N; j++) {
            String line = br.readLine();
            for(int i=0; i<N; i++) {
                map[j][i] = line.charAt(i);
            }
        }

        chk = new boolean[N][N];
        for (int j=0; j<N; j++ ) {
            for( int i=0; i<N; i++ ) {
                if (!chk[j][i]) {
                    bfs(j,i);
                    cnt++;
                }
            }
        }
        System.out.print(cnt +" ");
        cnt = 0;
        chk = new boolean[N][N];
        isColorBlind = true;
        for (int j=0; j<N; j++ ) {
            for( int i=0; i<N; i++ ) {
                if (!chk[j][i]) {
                    bfs(j,i);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}