import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_18450_경쟁적전염 {
	
	static int N;
	static int K;
	static int[][] map;
	
	static int[] dirY = {1,-1,0,0};
	static int[] dirX = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // N*N의 map 1 <= N <= 200
		K = Integer.parseInt(st.nextToken()); // 바이러스 수    1 <= K <= 1000
		
		map = new int[N][N];
		
		List<Virus> list = new LinkedList<Virus>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] != 0) {
					list.add(new Virus(r, c, map[r][c], 0));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		int lastTime = Integer.parseInt(st.nextToken());
		int lastY = Integer.parseInt(st.nextToken()) - 1;
		int lastX = Integer.parseInt(st.nextToken()) - 1;
		
		list.sort((o1, o2) -> o1.value - o2.value); 
		
		Queue<Virus> q = new LinkedList<Virus>(list);
		
		while(!q.isEmpty()) {
			if(q.peek().time >= lastTime) break;
			
			Virus temp = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int newY = temp.y + dirY[i];
				int newX = temp.x + dirX[i];
				
				if(newY < 0 || newY >= N || newX < 0 || newX >= N) {
					continue;
				}
				
				if(map[newY][newX] == 0) {
					map[newY][newX] = temp.value;
					q.add(new Virus(newY, newX, temp.value, temp.time+1));
				}
				
			}
		}
		
		
		
		System.out.println(map[lastY][lastX]);
	}
		
	static class Virus{
		int y;
		int x;
		int value;
		int time;
		public Virus(int y, int x, int value, int time) {
			super();
			this.y = y;
			this.x = x;
			this.value = value;
			this.time = time;
		}
		
	}
	
}
