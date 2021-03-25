package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1. 아기 상어 보다 작은 물고기들의 위치를 리스트에 저장.
// 2. 리스트에 저장된 물고기들 중 가장 가까운 곳에 있는 물고기를 찾는다.
// 		2-1. 아기 상어와 물고기 사이의 거리를 구하는 알고리즘은 BFS를 이용한다.
//		2-2. 아기 상어에서 시작해서 탐색하는 BFS로 아기상어 와의 특정 좌표간 거리를 배열로 나타내고,
//			그 중 리스트에 저장된 좌표가 있는지 확인하여 가장 가까운 물고기를 잡으러 간다.			
// 3. 가서 먹는다. -> 1

public class Main_백준_16236_아기상어_조규홍{
	
	static int N;
	static int[][] map;
	static int ate = 0;
	static int size = 2;
	static int answer = 0;
	
	static int[] dirR = {0, 0, 1, -1};
	static int[] dirC = {1, -1, 0, 0};
	
	static LinkedList<Position> smallFish = new LinkedList<Position>();
	static Position babyPosition;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int startC = -1, startR = -1;
		
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					startR = r; startC = c;
					map[r][c] = 0;
				} else if(map[r][c] == 1) {
					smallFish.add(new Position(r,c));					
				}
			}
		}
		
		babyPosition = new Position(startR, startC);
		
		//==============================입력 끝===================================
		
		while(true) {
			
			if(size == ate) {
				ate = 0;
				updateSmallFish(++size);
			}
			
			int[][] shortcut = getShortCut(babyPosition);
			
			Position targetFish = new Position(-1, -1);
			int smallFishDistance = Integer.MAX_VALUE;
			
			for (Position fish : smallFish) {
				if(shortcut[fish.r][fish.c] == -1) continue;
				
				if(shortcut[fish.r][fish.c] < smallFishDistance) {
					targetFish = fish;
					smallFishDistance = shortcut[fish.r][fish.c];
				} else if(shortcut[fish.r][fish.c] == smallFishDistance) {
					
					if(targetFish.r > fish.r) {
						targetFish = fish;
					} else if(targetFish.r < fish.r) {
						continue;
					} else {
						if(targetFish.c > fish.c) {
							targetFish = fish;
						} else {
							continue;
						} 
					}
				}
			}
			ate++;
			if(smallFishDistance == Integer.MAX_VALUE) break;
			map[targetFish.r][targetFish.c] = 0;
			answer += shortcut[targetFish.r][targetFish.c];
			smallFish.remove(targetFish);
			babyPosition = targetFish;
		}
		
		System.out.println(answer);
	}
	
	
	static void updateSmallFish(int size) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] == size-1) {
					smallFish.add(new Position(r,c));
				}
			}
		}
	}
	
	static int[][] getShortCut(Position start) {
		
		int[][] shortcut = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				shortcut[r][c] = -1;
			}
		}
		
		shortcut[start.r][start.c] = 0;
		
		Queue<Position> queue = new LinkedList<Position>();
		
		queue.offer(start);
		Position cP; // currentPosition
		while(!queue.isEmpty()) {
			
			cP = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int newR = cP.r + dirR[i], newC = cP.c + dirC[i];
				
				if(newR < 0 || newR >= N || newC < 0 || newC >= N) {
					continue;
				} else {
					
					if(map[newR][newC] > size || shortcut[newR][newC] != -1) continue;
					queue.offer(new Position(newR, newC));
					shortcut[newR][newC] = shortcut[cP.r][cP.c] + 1;
				}
				
			}
			
			
		}
		return shortcut;
	}
	
	
	
}

class Position{
	int r;
	int c;
	public Position(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}


