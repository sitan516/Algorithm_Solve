package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_7576_토마토 {
	
	static int ripe = 0; // 익은 토마토의 수
	static int max = 0; // 최대로 익을 수 있는 토마토의 수
	static int day = -1; // 흐른 시간
	
	static int M;
	static int N;
	static int[][] map;
	static Queue<Tomato> q;
	
	static int[] dirR = {1,-1,0,0};
	static int[] dirC = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		q = new LinkedList<Tomato>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) {
					q.add(new Tomato(r,c,0));
					ripe++;
				}
				if(map[r][c] != -1) {
					max++;
				}
			}
		}
		
		
		
		while(!(q.isEmpty() || max == ripe)) {
			spread(q.poll());
		}
		
		if(ripe == max) {
			System.out.println(day+1);
		} else {
			System.out.println(-1);
		}
		
		
	}
	
	static void spread(Tomato tomato) {
		day = tomato.ripeDay;
		for (int dir = 0; dir < 4; dir++) {
			int newR = tomato.r + dirR[dir];
			int newC = tomato.c + dirC[dir];
			
			if(newR < 0 || newR >= N || newC < 0 || newC >= M) continue;
			
			if(map[newR][newC] == 0) {
				map[newR][newC] = 1;
				q.add(new Tomato(newR,newC,tomato.ripeDay+1));
				ripe++;
			}
		}
	}
	
	static class Tomato{
		int r;
		int c;
		int ripeDay;
		public Tomato(int r, int c, int ripeDay) {
			super();
			this.r = r;
			this.c = c;
			this.ripeDay = ripeDay;
		}
	}
	
}
