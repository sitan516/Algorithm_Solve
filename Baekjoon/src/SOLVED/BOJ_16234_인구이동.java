package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234_인구이동{
	
	static int[][] map;
	static int[][] newMap;
	static boolean[][] getVisited;
	static boolean[][] setVisited;
	static int N;
	static int min;
	static int max;
	static int cnt;
	static int[] dirR = {0,0,1,-1}; 
	static int[] dirC = {1,-1,0,0}; 
	static boolean finishFlag = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		
		getVisited = new boolean[N][N];
		setVisited = new boolean[N][N];
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = -1;
		
		while(!finishFlag) {
			newMap = new int[N][N];
			clearVisited();
			answer++;
			finishFlag = true;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(!setVisited[r][c]) {
						cnt = 0;
						int sum = getSumByDFS(r,c);
						int aver = sum/cnt;
						setByDFS(r, c, aver);
					}
				}
			}
			map = newMap;
		}
		
		
		
		System.out.println(answer);
	}
	
	static int getSumByDFS(int r, int c) {
		getVisited[r][c] = true;
		cnt++;
		int sum = map[r][c];
		for (int dir = 0; dir < 4; dir++) {
			if(r + dirR[dir] < 0 || r + dirR[dir] >= N || c + dirC[dir] < 0 || c + dirC[dir] >= N) {
				continue;
			}
			if(!getVisited[r + dirR[dir]][c + dirC[dir]] && Math.abs(map[r][c] - map[r+ dirR[dir]][c+dirC[dir]]) >= min && Math.abs(map[r][c] - map[r+ dirR[dir]][c+dirC[dir]]) <= max) {
				finishFlag = false;
				sum += getSumByDFS(r + dirR[dir] , c+ dirC[dir]);
			}
		}
		return sum;
	}
	
	static void setByDFS(int r, int c, int aver) {
		setVisited[r][c] = true;
		newMap[r][c] = aver;
		for (int dir = 0; dir < 4; dir++) {
			if(r + dirR[dir] < 0 || r + dirR[dir] >= N || c + dirC[dir] < 0 || c + dirC[dir] >= N) {
				continue;
			}
			if(!setVisited[r + dirR[dir]][c + dirC[dir]] && Math.abs(map[r][c] - map[r+ dirR[dir]][c+dirC[dir]]) >= min && Math.abs(map[r][c] - map[r+ dirR[dir]][c+dirC[dir]]) <= max) {
				setByDFS(r + dirR[dir] , c+ dirC[dir], aver);
			}
		}
	}
	
	static void clearVisited() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				getVisited[r][c] = false;
				setVisited[r][c] = false;
			}
		}
	}
	
	
}

