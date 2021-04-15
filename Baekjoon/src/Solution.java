import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Solution {

	static int N;
	static int W;
	static int H;
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
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			int[][] map = new int[H][W];
			
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// DFS 방식을 이용함
			DFS(map, 0); 
			
			sb.append("#").append(testCase).append(" ").append(min).append("\n");
			
			
		
		}
			
		System.out.println(sb);
		
		

		
	}

	private static void DFS(int[][] map, int cnt) {
		
		// 다 떨어뜨렸으면 검사하기
		if(cnt == N) {
			int brick = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if(map[r][c] != 0) brick++;
				}
			}
			min = Math.min(brick, min);
			return;
		}
		
		tempMap = new int[H][W];
		// 하나씩 벽돌 떨어뜨리기
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				tempMap[r] = map[r].clone();
			}
			
			// 벽돌 떨어뜨리기
			dropBrick(c, tempMap);
			// 벽돌 정리하기
			arrangeBrick(tempMap);
			
			DFS(tempMap, cnt+1);
		}
	}

	private static void arrangeBrick(int[][] map) {		
		for (int c = 0; c < W; c++) {
			int stackPointer = H-1;
			for (int r = H-1; r >= 0; r--) {
				if(map[r][c] != 0) {
					map[stackPointer--][c] = map[r][c];
				}
			}
			for (int r = stackPointer; r >= 0; r--) {
				map[r][c] = 0;
			}
		}
	}

	private static void dropBrick(int c, int[][] map) {
		for (int r = 0; r < H; r++) {
			if(map[r][c] != 0) {
				bomb(r,c,map);
				break;
			}
		}
	}

	private static void bomb(int r, int c, int[][] map) {
		
		int size = map[r][c];
		map[r][c] = 0;
		
		int rStart = (r - size + 1) >= 0 ? (r - size + 1) : 0;
		int rEnd = (r + size - 1) < H ? (r + size - 1) : H-1;
		int cStart = (c - size + 1) >= 0 ? (c - size + 1) : 0;
		int cEnd = (c + size - 1) < W ? (c + size - 1) : W-1;
		
		for (int dr = rStart; dr <= rEnd; dr++) {
			if(map[dr][c] != 0) {
				bomb(dr, c, map);
			}
		}
		for (int dc = cStart; dc <= cEnd; dc++) {
			if(map[r][dc] != 0) {
				bomb(r, dc, map);
			}
		}
	}
	
}
