package SOLVED;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1562_계단수 {

	// 시작은 1~9 중에 하나
	// N = 10 -> 9876543210
	// N = 11 -> 10123456789 89876542310

	static long dp[][][]; //현재 위치, 남은 자릿수, bitmask
	static long divider = 1000000000;
	static long answer = 0;
	static int satisfy = (1 << 10) - 1;

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		dp = new long[10][N][1<<10];

		for (int i = 1; i <= 9 ; i++) {
			answer += dfs(i, N-1, 1<<i) % divider;
			answer %= divider;
		}

		System.out.println(answer);
	}

	public static long dfs(int cur, int left, int bitmask){
		if(dp[cur][left][bitmask] != 0) return dp[cur][left][bitmask];
		if(left == 0){
			if(bitmask == satisfy) return 1;
			else return 0;
		}
		long result = 0;
		if(cur != 0){
			result += dfs(cur-1, left-1, bitmask | 1<<cur-1) % divider ;
		}

		if(cur != 9){
			result += dfs(cur+1, left-1, bitmask | 1<<cur+1) % divider;
	}

		dp[cur][left][bitmask] = result;
		return result % divider;

	}

}


