package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2667_단자번호붙이기 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		List<Integer> answerList = new ArrayList<Integer>();
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if(!visited[y][x] && map[y][x] == 1) {
					answerList.add(DFS(y,x));
				}
			}
		}
		
		answerList.sort((o1,o2)->o1-o2);
		
		System.out.println(answerList.size());
		for (Integer integer : answerList) {
			System.out.println(integer);
		}
		
	}

	private static Integer DFS(int y, int x) {
		int ret = 1;
		visited[y][x] = true;
		for (int dir = 0; dir < 4; dir++) {
			
			int newY = y + dy[dir]; 
			int newX = x + dx[dir];
			
			if(newY < 0 || newY >= N || newX < 0 || newX >=N) {
				continue;
			}
			if(!visited[newY][newX] && map[newY][newX] == 1) {
				ret += DFS(newY,newX);				
			}
			
		}
		
		return ret;
	}
	
	
	
	
}
