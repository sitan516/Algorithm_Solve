package SOLVED;
// Main_백준_17182_우주탐사선_

import java.io.*;
import java.util.*;

public class BOJ_17182_우주탐사선 {
	
	static int N;
	static int K;
	static int[][] map;
	
	static int[][] memo; // [현재까지 방문한 나라][현재의 위치]
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		memo = new int[(1<<N)][N];
		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < N; j++) {
				memo[i][j] = Integer.MAX_VALUE;
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		move(K,0,1<<K);
		
		for (int i = 0; i < N; i++) {
			min = Math.min(min, memo[(1<<N) -1][i]); 
		}
		System.out.println(min);

	}
	
	static void move(int start, int sum, int visited) {
		
		// 탐사 불필요
		if(memo[visited][start] <= sum) {
			return;
		}
		memo[visited][start] = sum;
		
		// 탐사 완료
		if(visited == ((1<<N) - 1)) return;
		
		for (int i = 0; i < N; i++) {
			if(i == start) continue;
			move(i, sum + map[start][i], visited | 1 << i);
		}
		
	}
	
}