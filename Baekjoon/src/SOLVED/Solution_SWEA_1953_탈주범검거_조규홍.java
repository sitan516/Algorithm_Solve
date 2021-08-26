package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Solution_SWEA_1953_탈주범검거_조규홍 {

	static int N;
	static int M;
	static int R;
	static int C;
	static int L;
	
	static int map[][];
	static int visited[][];
	
	static int min;
	static int[][] tempMap;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
				
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new int[N][M];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					visited[r][c] = 21;
				}
			}
			
			move(R, C, 1);
			
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(visited[r][c] != 21) {
						cnt++;
					}
				}
			}
			
			
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n");
			
		}
			
		System.out.println(sb);
	}

	private static void move(int r, int c, int time) {
		
		visited[r][c] = time;
		if(time == L) return;
		
		if(isUp(r,c) && r-1 >= 0) {
			if(isDown(r-1,c) && visited[r-1][c] > time+1) {
				move(r-1, c, time+1);
			}
		}
		if(isDown(r,c) && r+1 < N) {
			if(isUp(r+1,c) && visited[r+1][c] > time+1) {
				move(r+1, c, time+1);
			}
		}
		if(isRight(r,c) && c+1 < M) {
			if(isLeft(r,c+1) && visited[r][c+1] > time+1) {
				move(r, c+1, time+1);
			}
		}
		if(isLeft(r,c) && c-1 >= 0) {
			if(isRight(r,c-1) && visited[r][c-1] > time+1) {
				move(r, c-1, time+1);
			}
		}
		
		
	}
	
	private static boolean isUp(int r, int c) {
		int pipe = map[r][c];
		if(pipe == 1 || pipe == 2 || pipe == 4 || pipe == 7) {
			return true;
		}
		return false;
	}
	private static boolean isDown(int r, int c) {
		int pipe = map[r][c];
		if(pipe == 1 || pipe == 2 || pipe == 5 || pipe == 6) {
			return true;
		}
		return false;
	}
	private static boolean isRight(int r, int c) {
		int pipe = map[r][c];
		if(pipe == 1 || pipe == 3 || pipe == 4 || pipe == 5) {
			return true;
		}
		return false;
	}
	private static boolean isLeft(int r, int c) {
		int pipe = map[r][c];
		if(pipe == 1 || pipe == 3 || pipe == 6 || pipe == 7) {
			return true;
		}
		return false;
	}
	
}
