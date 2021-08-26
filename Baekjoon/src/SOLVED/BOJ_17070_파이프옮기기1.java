package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_17070_파이프옮기기1 {
	
	static int N;
	static int K;
	static int[][] map;
	
	static int[] dirY = {1,-1,0,0};
	static int[] dirX = {0,0,1,-1};
	
	static int[][][] memo;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 집의 크기
		map = new int[N][N];
		memo = new int[N][N][3];
		
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(DFS(0,1,0));
		
	}

	private static int DFS(int y, int x, int dir) {
		
		int sum = 0;
		
		if(y==N-1 && x == N-1) {
			return 1;
		}
		
		// dir = 0 가로, dir = 1  세로, dir = 2 대각선 
		if(dir == 0 || dir == 2) {
			int newX = x+1;
			int newY = y;
			if(newX >= 0 && newX < N && newY >= 0 && newY < N) {
				if(map[newY][newX] == 0) {
					sum += DFS(newY, newX, 0);					
				}
			}
		}
		
		if(dir == 1 || dir == 2) {
			int newX = x;
			int newY = y+1;
			if(newX >= 0 && newX < N && newY >= 0 && newY < N) {
				if(map[newY][newX] == 0) {
					sum += DFS(newY, newX, 1);					
				}
			}
		}
		
		int newX = x+1;
		int newY = y+1;
		if(newX >= 0 && newX < N && newY >= 0 && newY < N) {
			if(map[newY][newX] == 0 && map[newY-1][newX] == 0 && map[newY][newX-1] == 0) {
				sum += DFS(newY, newX, 2);					
			}
		}
		
		return sum;
	}
		
	
	
}
