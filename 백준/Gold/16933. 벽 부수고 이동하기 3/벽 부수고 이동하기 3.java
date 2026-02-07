/*
    백준16933

    1. 아이디어
        벽을 낮에만 부술 수 있음 
        밤이 되면 이동 할 지, 현재 위치에서 기다려야 함 --> 둘 다 Queue에 넣기
        Deque가 아닌 K를 상태값을 넣어서 방문여부를 관리

    2. 시간복잡도
        O(V+E) = 1,000 * 1,000 * 10 * 2, V = 20,000,000 E = 5V (상하좌우+기다리기) ==> 100,000,000

    3. 자료구조
        그림 : int [][]
        방문여부: 
        수정 전: boolean[][][]
        수정 후: boolean[][][][2] //// 낮에 도착한 상태와 밤에 도착한 상태를 추가해줘야 함

        결과저장: 
        수정 전: Queue<int[]{현재위치, 이동횟수, 부술 수 있는 횟수, 밤낮 상태}>
        수정 후: Deque<int[]{현재위치, 이동횟수, 부술 수 있는 횟수, 밤낮 상태}> // LinkedList보다 빠른 ArrayDeque 권장
        // 낮 : 1 밤 : 0
 */
import java.io.*;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] map;
    static boolean[][][][] chk;

    static void bfs() {
        // LinkedList보다 빠른 ArrayDeque 권장
        Deque<int[]> q = new ArrayDeque<>();
        // {y, x, 이동횟수, 남은벽개수, 밤낮상태(0낮, 1밤)}
        q.add(new int[]{0, 0, 1, K, 0});
        chk[0][0][K][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int cnt = cur[2];
            int wall = cur[3];
            int isNight = cur[4];

            // 목표 지점 도달 시 즉시 종료
            if (y == N - 1 && x == M - 1) {
                System.out.println(cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                // 1. 이동할 곳이 빈 칸(0)인 경우
                if (map[ny][nx] == 0) {
                    if (!chk[ny][nx][wall][1 - isNight]) {
                        chk[ny][nx][wall][1 - isNight] = true;
                        q.add(new int[]{ny, nx, cnt + 1, wall, 1 - isNight});
                    }
                } 
                // 2. 이동할 곳이 벽(1)인 경우
                else if (wall > 0) {
                    // 낮(0)이면 부수고 이동 가능
                    if (isNight == 0) {
                        if (!chk[ny][nx][wall - 1][1]) {
                            chk[ny][nx][wall - 1][1] = true;
                            q.add(new int[]{ny, nx, cnt + 1, wall - 1, 1});
                        }
                    } 
                    // 밤(1)이면 부술 수 없으므로 제자리 대기
                    else {
                        // 현재 위치에서 시간만 보냄 (밤 -> 낮)
                        if (!chk[y][x][wall][0]) {
                            chk[y][x][wall][0] = true;
                            q.add(new int[]{y, x, cnt + 1, wall, 0});
                        }
                    }
                }
            }
        }
        System.out.println("-1");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[1000][1000];
        chk = new boolean[1000][1000][11][2];

        for(int j=0; j<N; j++) {
            String line = br.readLine();
            for(int i=0; i<M; i++) {
                map[j][i] = line.charAt(i) - '0';
            }
        }
        bfs();
    }
}
