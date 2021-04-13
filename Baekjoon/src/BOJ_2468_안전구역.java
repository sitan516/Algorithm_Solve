import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_2468_안전구역 {
	
	static int N;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dirY = {1,-1,0,0};
	static int[] dirX = {0,0,1,-1};
	
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 집의 크기
		map = new int[N][N];
		
		int maxH = 0;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, map[r][c]);
			}
		}
		
		
		for (int h = 1; h <= maxH; h++) {
			boolean[][] tempMap = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					tempMap[r][c] = map[r][c] >= h ? true : false;
				}
			}
			
			visited = new boolean[N][N];
			
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(tempMap[r][c] && !visited[r][c]) {
						DFS(r,c, tempMap);
						cnt++;
					}
				}
			}
			answer = Math.max(answer, cnt);
			
		}

		System.out.println(answer);
		
	}

	private static void DFS(int r, int c, boolean[][] map) {
		visited[r][c] = true;
		for (int dir = 0; dir < 4; dir++) {
			int newR = r + dirY[dir];
			int newC = c + dirX[dir];
			if(newR < 0 || newR >= N || newC <0 || newC>=N) {
				continue;
			}
			if(!visited[newR][newC] && map[newR][newC]) {
				DFS(newR, newC, map);				
			}
		}
	}

	
	
	
	
}
