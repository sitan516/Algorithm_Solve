package SOLVED;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098_외판원순회3 {
	
	static int N;
	static int[][] map;
	static double[][] route;
	static double[][] memo;
	static int arrive;
	static int INF = 16000001;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		route = new double[N][N];
		arrive = (int) Math.pow(2, N) - 1;
		memo = new double[N][arrive+1]; // pos, bitmask
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				route[i][j] = Math.sqrt(Math.pow(map[i][0] - map[j][0], 2) + Math.pow(map[i][1] - map[j][1], 2));
				route[j][i] = route[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			Arrays.fill(memo[i], INF);

		}

		System.out.println(move(0, 1));
	}

	public static double move(int pos, int bitmask) {

		if(bitmask == arrive) {
			if(route[pos][0] != 0){
				return route[pos][0];
			} return INF;
		}

		if(memo[pos][bitmask] != INF) return memo[pos][bitmask];

		for (int next = 1; next < N; next++) {
			if((bitmask & 1<<next) != 0 || route[pos][next] == 0) continue;
			memo[pos][bitmask] = Math.min(memo[pos][bitmask], move(next, bitmask | 1<<next) + route[pos][next]);
		}
		return memo[pos][bitmask];
	}

	//	public static void move(int pos, int cost, int bitmask) {
//		memo[pos][bitmask] = cost;
//
//		if(bitmask == arrive) {
//			if(route[pos][0] == 0) return;
//			answer = Math.min(answer, cost + route[pos][0]);
//			return;
//		}
//
//		for (int i = 1; i < N; i++) {
//			if((bitmask & 1<<i) != 0 || route[pos][i] == 0) continue;
//
//			if(cost + route[pos][i] >= answer) continue;
//			if(memo[i][bitmask | 1 << i] != 0 && memo[i][bitmask | 1 << i] <= cost + route[pos][i]) continue;
//			move(i, cost + route[pos][i], bitmask | 1 << i);
//		}
//	}



}


