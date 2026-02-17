/*
    백준 
    1. 아이디어

    2. 시간복잡도

    3. 자료구조

*/
import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] chk;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int x,y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        map = new int[y][x];
        chk = new boolean[y][x];
        for(int j=0; j<y; j++) {
            String line = br.readLine();
            for(int i=0; i<x; i++) {
                if(line.charAt(i) -'0' == 1){
                    map[j][i] = line.charAt(i) -'0';
                } else {
                    chk[j][i] = true;
                }
            }
        }
        bfs();

    }
    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        chk[0][0] = true;

        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            int cnt = cur[2];
            if(cur_x==x-1 && cur_y ==y-1) System.out.println(cnt);
            for(int i=0; i<4; i++) {
                int nx = cur_x + dx[i];
                int ny = cur_y + dy[i];
                if(ny>=0 && ny<y && nx>=0 && nx<x && !chk[ny][nx]) {
                    q.add(new int[]{nx,ny,cnt+1});
                    chk[ny][nx] = true;
                }
            }
        }
    }
}