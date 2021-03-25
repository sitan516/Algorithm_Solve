package SOLVED;

import java.io.*;
import java.util.*;

// (0,0) 으로부터 시작하여 DFS로 탐색.


public class Main_백준_2636_치즈_조규홍 {
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] melt;
	
	static int[] dirR = {0, 0, 1, -1}; 
	static int[] dirC = {1, -1, 0, 0}; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int cheese = 0; // 치즈의 갯수
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) {
					cheese++;
				}
			}
		}
		
		
		
		int time = 0; // 걸린 시간
		while(true) {
			time++;
			melt = new boolean[N][M]; // 다음 시간에 녹아있는 자리
			findMelt(0, 0); // DFS 탐색 시작
			
			int meltingCheese = 0; // 다음 시간에 녹을 치즈
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(map[r][c] == 1 && melt[r][c]) {
						meltingCheese++;
						map[r][c] = 0;
					}
				}
			}
			
			// 남아있는 치즈와 다음 시간에 녹는 치즈의 갯수가 같으면
			// 남아있는 치즈가 마지막까지 남아있는 치즈
			if(cheese == meltingCheese) break;
			cheese -= meltingCheese;
		}
		System.out.println(time);
		System.out.println(cheese);
	}
	static void findMelt(int r, int c){
		
		// 현재 자리는 다음시간에 녹아있는 자리이다.
		melt[r][c] = true;
		
		// 현재 자리가 치즈라면 더 이상 탐색하면 안되므로 return
		if(map[r][c] == 1) {
			return;
		}
		
		// 4방향으로 탐색
		for (int dir = 0; dir < 4; dir++) {
			if(r + dirR[dir] < 0 || r + dirR[dir] >= N || c + dirC[dir] < 0 || c + dirC[dir] >= M) {
				continue;
			}
			if(!melt[r + dirR[dir]][c + dirC[dir]]) {
				findMelt(r + dirR[dir], c + dirC[dir]);
			}
		}
	}
}
