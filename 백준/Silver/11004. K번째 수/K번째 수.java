/*
    백준 11004
    1. 아이디어
        정렬 후 k-1 인덱스 출력
        퀵정렬로 직접 구현할거임
    2. 시간복잡도
        O(nlogn) == 5,000,000 log 5,000,000
    3. 자료구조
        배열: int[N]
 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int[] A = new int [N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        quickSort(A, 0, N - 1, K - 1);
        System.out.println(A[K - 1]);
    }
    public static void quickSort(int[] A, int S, int E, int K) {
        while (S < E) {
            int pivotIndex = partition(A, S, E);
            
            if (pivotIndex == K) {
                return;
            } else if (K < pivotIndex) {
                // K가 피벗보다 왼쪽에 있으면 오른쪽 끝을 줄임
                E = pivotIndex - 1;
            } else {
                // K가 피벗보다 오른쪽에 있으면 왼쪽 끝을 늘림
                S = pivotIndex + 1;
            }
        }
    }
    
    public static int partition(int[] A, int S, int E) {
        int M = (S + E) / 2;
        swap(A, S, M); // 중간값을 피벗으로 설정 (최악의 경우 방지)
        int pivot = A[S];
        int i = S + 1, j = E;
    
        while (i <= j) {
            // 피벗보다 작은 동안 i 이동
            while (i <= E && A[i] < pivot) i++;
            // 피벗보다 큰 동안 j 이동
            while (j >= S + 1 && A[j] > pivot) j--;
    
            if (i <= j) {
                swap(A, i++, j--);
            }
        }
        
            // j가 피벗이 위치해야 할 최종 자리입니다.
            A[S] = A[j];
            A[j] = pivot;
            return j;
        
    }
    public static void swap(int[] A, int S, int E) {
        int temp = A[S];
        A[S] = A[E];
        A[E] =temp;

    }
}

