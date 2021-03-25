package SOLVED;

// Main_백준_1780_종이의개수

import java.io.*;
import java.util.*;

public class BOJ_9205_맥주마시면서걸어가기 {

	static int N;
	static int[][] map;
	
	static int homeX;
	static int homeY;
	static int[] storeX;
	static int[] storeY;
	static int rockX;
	static int rockY;
	
	
	static boolean visited[];
	static int beer;
	static boolean flag;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 0; testCase < T; testCase++) {
			
			N = Integer.parseInt(br.readLine());
			storeX = new int[N];
			storeY = new int[N];
			visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				storeX[i] = Integer.parseInt(st.nextToken());
				storeY[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			rockX = Integer.parseInt(st.nextToken());
			rockY = Integer.parseInt(st.nextToken());

			flag = false;
			DFS(homeY, homeX);
			sb.append(flag ? "happy" : "sad").append("\n");
		}
		System.out.println(sb);
	}
		

	static void DFS(int y, int x) {
		
		if(Math.abs(y - rockY) + Math.abs(x - rockX) <= 1000) {
			flag = true;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i] && Math.abs(y - storeY[i]) + Math.abs(x - storeX[i]) <= 1000) {
				visited[i] = true;
				DFS(storeY[i], storeX[i]);
			}
		}
		
		return;
		
	}

}