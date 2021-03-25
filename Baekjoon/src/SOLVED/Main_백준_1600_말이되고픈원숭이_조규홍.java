package SOLVED;

import java.io.*;
import java.util.*;

// (0,0) 으로부터 시작하여 DFS로 탐색.


public class Main_백준_1600_말이되고픈원숭이_조규홍 {
	
	static int K;
	static int N;
	static int M;
	static int[][] map;
	static int[][] visited;
	
	static int[] moveKnightR = {1,1,-1,-1,2,2,-2,-2};
	static int[] moveKnightC = {2,-2,2,-2,1,-1,1,-1};
	
	static int[] moveMonkeyR = {1,-1,0,0};
	static int[] moveMonkeyC = {0,0,1,-1};
	
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
				
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		
		
		
		for (int r = 0; r < N; r++) {
			String pos = br.readLine();
			for (int c = 0, index = 0; c < M; c++, index+=2) {
				map[r][c] = pos.charAt(index) - '0';
				visited[r][c] = -1;
			}
		}

		
		queue = new LinkedList<Integer>();
		addQueue(0,0,0,K);
		visited[0][0] = K;
		while(!queue.isEmpty()) {
			int r = queue.poll();
			int c = queue.poll();
			int cnt = queue.poll();
			int leftKnightMove = queue.poll();
			
			if(r == N-1 && c == M-1) {
				System.out.println(cnt);
				return;
			}
			
			
			move(r,c,cnt,leftKnightMove);
		}
		
		
		System.out.println(-1);
	}
	
	static void move(int r, int c, int cnt, int leftKnightMove) {
		
		if(leftKnightMove > 0) {
			for (int dir = 0; dir < 8; dir++) {
				int nextR = r + moveKnightR[dir];
				int nextC = c + moveKnightC[dir];
				if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
					continue;
				}
				if(visited[nextR][nextC] < leftKnightMove - 1 && map[nextR][nextC] == 0) {
					visited[nextR][nextC] = leftKnightMove - 1;
					addQueue(nextR , nextC, cnt + 1, leftKnightMove - 1);					
				}
			}
		}
		
		for (int dir = 0; dir < 4; dir++) {
			int nextR = r + moveMonkeyR[dir];
			int nextC = c + moveMonkeyC[dir];
			if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
				continue;
			}
			if(visited[nextR][nextC] < leftKnightMove && map[nextR][nextC] == 0) {
				visited[nextR][nextC] = leftKnightMove;
				addQueue(nextR , nextC, cnt + 1, leftKnightMove);					
			}
		}
	}
	
	static void addQueue(int r, int c, int cnt, int leftKnightMove) {
		queue.add(r);
		queue.add(c);
		queue.add(cnt);
		queue.add(leftKnightMove);
	}
	
}

