import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	
	static int N;
	static int M;
	
	static int[] dirR = {1,-1,0,0};
	static int[] dirC = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // <= 100
		M = Integer.parseInt(st.nextToken()); // <= 100
		int K = Integer.parseInt(st.nextToken()); // <= 100
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int xm = Integer.parseInt(st.nextToken());
			int ym = Integer.parseInt(st.nextToken());
			int xM = Integer.parseInt(st.nextToken()) -1;
			int yM = Integer.parseInt(st.nextToken()) -1;
			
			for (int r = ym; r <= yM; r++) {
				for (int c = xm; c <= xM; c++) {
					 map[r][c] = 1;
				}
			}
		}
		
		List<Integer> list = new LinkedList<Integer>();
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 0 && !visited[r][c]) {
					list.add(DFS(r,c));
				}
			}
		}
		
		
		System.out.println(list.size());
		list.sort((o1,o2) -> o1-o2);
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
		
	}
	
	static int DFS(int r, int c) {
		
		visited[r][c] = true;
		int cnt = 1;
		
		for (int i = 0; i < 4; i++) {
			int newR = r + dirR[i];
			int newC = c + dirC[i];
			
			if(newR < 0 || newR >= N || newC < 0 || newC >= M) {
				continue;
			}
			
			if(!visited[newR][newC] && map[newR][newC] == 0) {
				cnt += DFS(newR, newC);
			}
			
		}
		return cnt;
	}
	
}
