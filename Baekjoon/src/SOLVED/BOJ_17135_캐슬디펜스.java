package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17135_캐슬디펜스 {
	
	static int N;
	static int M;
	static int D;
	static int[][] map;
	
	static int max = 0;
	static int mob;
	static int tempMob;
	static int kill;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken()) - 1;
		
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1)mob++;
			}
		}
		
		getMaxCnt(0,0,0);
		
		System.out.println(max);
		
	}

	private static void getMaxCnt(int cnt, int idx, int bitmask) {
		
		if(cnt == 3) {
			getKillCnt(bitmask);
			return;
		}
		
		for (int i = idx; i < M; i++) {
			getMaxCnt(cnt+1, i+1, bitmask | 1 << i);
		}
		
	}

	private static void getKillCnt(int bitmask) {
		tempMob = mob;
		kill = 0;
		int[][] tempMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			tempMap[r] = map[r].clone();
		}
		while(tempMob > 0) {
			shoot(tempMap, bitmask);
			move(tempMap);
		}
		max = Math.max(kill, max);
	}
	
	// r, c, d
	//   0
 	// 0 0 0
	// Math.abs(c-i) + ((N-1) - r) = d
	// r = Math.abs(c-i) + N-1 - d
	private static void shoot(int[][] map, int bitmask) {
		List<Integer> shootList = new ArrayList<Integer>();
		newArcher: for (int i = 0; i < M; i++) {
			if((bitmask & 1 << i) != 0) {
				for (int d = 0; d <= D; d++) {
					for (int c = i-d; c <= i+d; c++) {
						int r = Math.abs(c-i) + N-1 - d;
						if(r < 0 || r>= N || c<0 || c>=M) continue;
						if(map[r][c] == 1) {
							shootList.add(Math.abs(c-i) + N-1 - d);
							shootList.add(c);
							continue newArcher;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < shootList.size()/2; i++) {
			
			int r = shootList.get(i*2);
			int c = shootList.get(i*2 + 1);
			if(map[r][c] == 1) {
				map[r][c] = 0;
				kill++;
				tempMob--;
			}
		}
		
	}

	private static void move(int[][] map) {
		for (int r = N-1; r >= 0; r--) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 1) {
					if(r==N-1) {
						map[r][c] = 0;
						tempMob--;
					}
					else {
						map[r][c] = 0;
						map[r+1][c] = 1;
					}
				}
			}
		}
	}


	
	
	
}
