package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main_백준_14502_연구소_조규홍 {

	static int safe = 0;
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] virusVisited;
	
	static int[] dirR = {1, -1, 0 ,0};
	static int[] dirC = {0, 0, 1 ,-1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String s = br.readLine();
		N = s.charAt(0) - '0';
		M = s.charAt(2) - '0';
		
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			s = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c*2) - '0';
			}
		}
		
		makeWall(0,-1,0);
		
		// 벽 세우기
		
		// 바이러스 퍼뜨리기

		// 갯수 세기

		System.out.println(safe);
	}
	
	static void makeWall(int rIdx, int cIdx, int cnt) {
		
		if(cnt == 3) {
			spreadVirus();
			return;
		}
		
		for (int r = rIdx; r < N; r++) {
			cIdx = -1;
			for (int c = cIdx+1; c < M; c++) {
				cIdx = -1;
				if(map[r][c] == 0) {
					map[r][c] = 1;
					makeWall(r,c,cnt+1);
					map[r][c] = 0;
				}
			}		
		}
	}
	
	static void spreadVirus() {
		virusVisited = new boolean[N][M];
		
		int[][] tempMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempMap[i][j] = map[i][j];
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 2 && !virusVisited[r][c]) {
					virusMove(r,c, tempMap);
				}
			}
		}
		
		countSafe(tempMap);
	}
	
	static void virusMove(int r, int c,int[][] tempMap) {
		for (int dir = 0; dir < 4; dir++) {
			int rNext = r + dirR[dir];
			int cNext = c + dirC[dir];
			
			if(rNext < 0 || rNext >= N || cNext<0 || cNext >= M) {
				continue;
			}
			
			if(tempMap[rNext][cNext] == 0) {
				tempMap[rNext][cNext] = 2;
				virusVisited[rNext][cNext] = true;
				virusMove(rNext,cNext, tempMap);
			}
		}
	}
	
	static void countSafe(int[][] map) {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 0) cnt++;
			}
		}
		safe = Math.max(safe, cnt);
	}
}
